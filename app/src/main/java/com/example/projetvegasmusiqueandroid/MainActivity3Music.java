package com.example.projetvegasmusiqueandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projetvegasmusiqueandroid.DRO.BD;

public class MainActivity3Music extends AppCompatActivity {

    private ListView musicListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_music);
        initWidgets();
        loadFromDBToMemory();
        setMusicAdapter();
        setOnClickListener();
    }


    private void initWidgets()
    {
        musicListView = findViewById(R.id.listmusic);
    }

    private void loadFromDBToMemory()
    {
        BD sqLiteManager = BD.instanceOfDatabase(this);
        sqLiteManager.populateMusicListArray();
    }

    private void setMusicAdapter()
    {
        MusicAdapter musicAdapter = new MusicAdapter(getApplicationContext(), Music.nonDeletedMusic());
        musicListView.setAdapter(musicAdapter);
    }

    private void setOnClickListener() {
        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Music selectedMusic = (Music) musicListView.getItemAtPosition(position);
                Intent editMusicIntent = new Intent(getApplicationContext(), MainActivity3musiccreate.class);
                editMusicIntent.putExtra(Music.MUSIC_EDIT_EXTRA, selectedMusic.getId());
                startActivity(editMusicIntent);
            }
        });
    }

    public void music(View view)
    {
        Intent newMusicIntent = new Intent(this, MainActivity3musiccreate.class);
        startActivity(newMusicIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setMusicAdapter();
    }

    public void home2(View view)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
