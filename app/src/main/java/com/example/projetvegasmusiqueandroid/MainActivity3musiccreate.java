package com.example.projetvegasmusiqueandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetvegasmusiqueandroid.DRO.BD;

import java.util.Date;

public class MainActivity3musiccreate extends AppCompatActivity {

    private EditText nameEditText, descEditText;
    private Button deleteButton;
    private Music selectedMusic;
    private Music getSelectedArtistId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3musiccreate);
        initWidgets();
        checkForEditMusic();
    }


    private void initWidgets() {
        nameEditText = findViewById(R.id.nameEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteMusicButton);
    }

    private void checkForEditMusic() {
        Intent previousIntent = getIntent();

        int passedMusicID = previousIntent.getIntExtra(Music.MUSIC_EDIT_EXTRA, -1);
        selectedMusic = Music.getMusicForID(passedMusicID);

        if (selectedMusic != null) {
            nameEditText.setText(selectedMusic.getName());
            descEditText.setText(selectedMusic.getDescription());
        } else {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveMusic(View view)
    {

        BD sqLiteManager = BD.instanceOfDatabase(this);
        String name = String.valueOf(nameEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedMusic == null) {
            int id = Music.musicArrayList.size();
            Music newMusic = new Music(id, name, desc);
            Music.musicArrayList.add(newMusic);
            sqLiteManager.addMusicToDataBase(newMusic);
        }
        else{
            selectedMusic.setName(name);
            selectedMusic.setDescription(desc);
            sqLiteManager.updateMusicInDB(selectedMusic);
        }

        finish();
    }

    public void deleteMusic(View view){
        selectedMusic.setDeleted(new Date());
        BD sqLiteManager = BD.instanceOfDatabase(this);
        sqLiteManager.updateMusicInDB(selectedMusic);
        finish();
    }

    public void home2(View view)
    {
        Intent intent = new Intent(this, MainActivity3Music.class);
        startActivity(intent);
    }
}