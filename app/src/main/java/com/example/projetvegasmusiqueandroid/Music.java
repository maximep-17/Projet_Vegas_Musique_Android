package com.example.projetvegasmusiqueandroid;

import java.util.ArrayList;
import java.util.Date;

public class Music
{
    public static ArrayList<Music> musicArrayList = new ArrayList<>();
    public static String MUSIC_EDIT_EXTRA = "musicEdit";

    private int id;
    private String name;
    private String description;
    private Date deleted;


    public Music(int id, String name, String description, Date deleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
    }

    public Music(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        deleted = null;

    }

    public static Music getMusicForID(int passedMusicID) {
        for (Music music : musicArrayList){
            if(music.getId() == passedMusicID)
                return music;
        }
        return null;
    }

    public static ArrayList<Music> nonDeletedMusic(){
        ArrayList<Music> nonDeleted = new ArrayList<>();
        for(Music music : musicArrayList){
            if(music.getDeleted() == null)
                nonDeleted.add(music);
        }
        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
