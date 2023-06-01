package com.example.projetvegasmusiqueandroid.DRO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.projetvegasmusiqueandroid.Artiste;
import com.example.projetvegasmusiqueandroid.Music;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BD extends SQLiteOpenHelper {

    private static BD sqLiteManager;

    private static final String DATABASE_NAME = "vegasmusic";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "artiste";
    private static final String TABLE_NAME_2 = "music";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String DESC_FIELD = "desc";
    private static final String DELETED_FIELD = "deleted";

    private static final String ID_FIELD_2 = "id";
    private static final String NAME_FIELD_2 = "name";
    private static final String DESC_FIELD_2 = "desc";
    private static final String DELETED_FIELD_2 = "deleted";


    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public BD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static BD instanceOfDatabase(Context context){
        if(sqLiteManager == null)
            sqLiteManager = new BD(context);

        return sqLiteManager;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        StringBuilder sql;
        StringBuilder sql2;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(NAME_FIELD)
                .append(" TEXT, ")
                .append(DESC_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");

        sqLiteDatabase.execSQL(sql.toString());

        sql2 = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME_2)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD_2)
                .append(" INT, ")
                .append(NAME_FIELD_2)
                .append(" TEXT, ")
                .append(DESC_FIELD_2)
                .append(" TEXT, ")
                .append(DELETED_FIELD_2)
                .append(" TEXT)");

        sqLiteDatabase.execSQL(sql2.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addArtisteToDataBase(Artiste artiste){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, artiste.getId());
        contentValues.put(NAME_FIELD, artiste.getName());
        contentValues.put(DESC_FIELD, artiste.getDescription());
        contentValues.put(DELETED_FIELD, getStringFromDate(artiste.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void addMusicToDataBase(Music music){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD_2, music.getId());
        contentValues.put(NAME_FIELD_2, music.getName());
        contentValues.put(DESC_FIELD_2, music.getDescription());
        contentValues.put(DELETED_FIELD_2, getStringFromDate(music.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME_2, null, contentValues);
    }

    public void populateArtisteListArray(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try(Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null)){
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    String desc = result.getString(3);
                    String stringDeleted = result.getString(4);
                    Date deleted = getDateFromString(stringDeleted);
                    Artiste artiste = new Artiste(id, name, desc, deleted);
                    Artiste.artisteArrayList.add(artiste);
                }
            }
        }
    }

    public void populateMusicListArray(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try(Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_2, null)){
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    String desc = result.getString(3);
                    String stringDeleted = result.getString(4);
                    int artisteid = result.getInt(5);
                    Date deleted = getDateFromString(stringDeleted);
                    Music music = new Music(id, name, desc, deleted);
                    Music.musicArrayList.add(music);
                }
            }
        }
    }

    public void updateArtisteInDB(Artiste artiste)
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, artiste.getId());
        contentValues.put(NAME_FIELD, artiste.getName());
        contentValues.put(DESC_FIELD, artiste.getDescription());
        contentValues.put(DELETED_FIELD, getStringFromDate(artiste.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(artiste.getId())});
    }

    public void updateMusicInDB(Music music)
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD_2, music.getId());
        contentValues.put(NAME_FIELD_2, music.getName());
        contentValues.put(DESC_FIELD_2, music.getDescription());
        contentValues.put(DELETED_FIELD_2, getStringFromDate(music.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME_2, contentValues, ID_FIELD_2 + " =? ", new String[]{String.valueOf(music.getId())});
    }

    private String getStringFromDate(Date date) {
        if(date == null)
            return null;
        return dateFormat.format(date);
    }

    private Date getDateFromString(String string){
        try {
            return dateFormat.parse(string);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }
}


