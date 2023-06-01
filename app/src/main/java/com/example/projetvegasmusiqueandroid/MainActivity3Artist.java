package com.example.projetvegasmusiqueandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projetvegasmusiqueandroid.DRO.BD;

public class MainActivity3Artist extends AppCompatActivity {

    private ListView artisteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_artist);
        initWidgets();
        loadFromDBToMemory();
        setArtisteAdapter();
        setOnClickListener();
    }


    private void initWidgets()
    {
        artisteListView = findViewById(R.id.listartiste);
    }

    private void loadFromDBToMemory()
    {
        BD sqLiteManager = BD.instanceOfDatabase(this);
        sqLiteManager.populateArtisteListArray();
    }

    private void setArtisteAdapter()
    {
        ArtisteAdapter artisteAdapter = new ArtisteAdapter(getApplicationContext(), Artiste.nonDeletedArtistes());
        artisteListView.setAdapter(artisteAdapter);
    }

    private void setOnClickListener() {
        artisteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Artiste selectedArtiste = (Artiste) artisteListView.getItemAtPosition(position);
                Intent editArtisteIntent = new Intent(getApplicationContext(), MainActivity3artistcreate.class);
                editArtisteIntent.putExtra(Artiste.NOTE_EDIT_EXTRA, selectedArtiste.getId());
                startActivity(editArtisteIntent);
            }
        });
    }

    public void artiste(View view)
    {
        Intent newArtisteIntent = new Intent(this, MainActivity3artistcreate.class);
        startActivity(newArtisteIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setArtisteAdapter();
    }

    public void home(View view)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
