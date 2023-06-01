package com.example.projetvegasmusiqueandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetvegasmusiqueandroid.DRO.BD;

import java.util.Date;

public class MainActivity3artistcreate extends AppCompatActivity {

    private EditText nameEditText, descEditText;
    private Button deleteButton;
    private Artiste selectedArtiste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3artistacreate);
        initWidgets();
        checkForEditArtiste();
    }


    private void initWidgets() {
        nameEditText = findViewById(R.id.nameEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteArtisteButton);
    }

    private void checkForEditArtiste() {
        Intent previousIntent = getIntent();

        int passedArtisteID = previousIntent.getIntExtra(Artiste.NOTE_EDIT_EXTRA, -1);
        selectedArtiste = Artiste.getArtisteForID(passedArtisteID);

        if (selectedArtiste != null){
            nameEditText.setText(selectedArtiste.getName());
            descEditText.setText(selectedArtiste.getDescription());
        }
        else{
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveArtiste(View view)
    {

        BD sqLiteManager = BD.instanceOfDatabase(this);
        String name = String.valueOf(nameEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedArtiste == null) {
            int id = Artiste.artisteArrayList.size();
            Artiste newArtiste = new Artiste(id, name, desc);
            Artiste.artisteArrayList.add(newArtiste);
            sqLiteManager.addArtisteToDataBase(newArtiste);
        }
        else{
            selectedArtiste.setName(name);
            selectedArtiste.setDescription(desc);
            sqLiteManager.updateArtisteInDB(selectedArtiste);
        }

        finish();
    }

    public void deleteArtiste(View view){
        selectedArtiste.setDeleted(new Date());
        BD sqLiteManager = BD.instanceOfDatabase(this);
        sqLiteManager.updateArtisteInDB(selectedArtiste);
        finish();
    }

    public void home(View view)
    {
        Intent intent = new Intent(this, MainActivity3Artist.class);
        startActivity(intent);
    }
}