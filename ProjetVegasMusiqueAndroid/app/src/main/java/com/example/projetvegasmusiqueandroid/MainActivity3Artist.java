package com.example.projetvegasmusiqueandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projetvegasmusiqueandroid.DRO.GestionBD;
import com.example.projetvegasmusiqueandroid.metier.artiste;

import java.util.List;

public class MainActivity3Artist extends AppCompatActivity {

    private TextView mDataTextView;
    private GestionBD mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_artist);

        mDataTextView = findViewById(R.id.tvlartist);
        mDbHelper = new GestionBD(this);
        mDbHelper.open();

        List<artiste> lesArtistes = mDbHelper.donneLesArtistes();
        StringBuilder data = new StringBuilder();
        for (artiste art : lesArtistes) {
            data.append(art.toString()).append("\n");
            List<String> lesMusiques = mDbHelper.donneLesMusiquesParArtiste(art.getId()); // Ajout des musiques de l'artiste
            for (String musique : lesMusiques) {
                data.append("\t").append(musique).append("\n"); // Ajout des noms des musiques à l'affichage
            }
            data.append("\n"); // Ajout d'un saut de ligne entre chaque artiste
        }
        mDataTextView.setText(data.toString());

        mDbHelper.close();
    }
}
