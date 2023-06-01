package com.example.projetvegasmusiqueandroid.DRO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetvegasmusiqueandroid.metier.artiste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionBD {

    private SQLiteDatabase maBase;
    private BD bdHelper;

    public GestionBD(Context context) {
        this.bdHelper = new BD(context, "projetvegasmusique", null, 1);
    }

    // méthodes de gestion des données (CRUD)
    public void open() {
        maBase = bdHelper.getWritableDatabase();
    }

    public void close() {
        maBase.close();
    }

    public ArrayList<String> donneLesNomsDesArtistes() {
        ArrayList<String> lesNoms = new ArrayList<String>();
        String req = "select name from artiste"; // Changé 'nom' à 'name'
        Cursor cursor = maBase.rawQuery(req, null);
        while (cursor.moveToNext()) {
            lesNoms.add(cursor.getString(0));
        }
        cursor.close();
        return lesNoms;
    }

    public List<artiste> donneLesArtistes() {
        List<artiste> lesArtistes = new ArrayList<>();
        String req = "select * from artiste";
        Cursor cursor = maBase.rawQuery(req, null);
        while (cursor.moveToNext()) {
            Number id = cursor.getLong(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2); // Ajout de la description
            Date dateCrea = new Date(cursor.getLong(3));
            Date dateUpdate = new Date(cursor.getLong(4));

            lesArtistes.add(new artiste(id, name, description, dateCrea, dateUpdate)); // Ajout de la description
        }
        cursor.close();
        return lesArtistes;
    }

    public List<String> donneLesMusiquesParArtiste(Number artisteId) {
        List<String> lesMusiques = new ArrayList<>();
        String req = "select name from musique where artiste_id = ?";
        Cursor cursor = maBase.rawQuery(req, new String[]{artisteId.toString()});
        while (cursor.moveToNext()) {
            lesMusiques.add(cursor.getString(0));
        }
        cursor.close();
        return lesMusiques;
    }
}
