package com.example.projetvegasmusiqueandroid;

import java.util.ArrayList;
import java.util.Date;

public class Artiste
{
    public static ArrayList<Artiste> artisteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA = "artisteEdit";

    private int id;
    private String name;
    private String description;
    private Date deleted;

    public Artiste(int id, String name, String description, Date deleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
    }

    public Artiste(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        deleted = null;
    }

    public static Artiste getArtisteForID(int passedArtisteID) {
        for (Artiste artiste : artisteArrayList){
            if(artiste.getId() == passedArtisteID)
                return artiste;
        }
        return null;
    }

    public static ArrayList<Artiste> nonDeletedArtistes(){
        ArrayList<Artiste> nonDeleted = new ArrayList<>();
        for(Artiste artiste : artisteArrayList){
            if(artiste.getDeleted() == null)
                nonDeleted.add(artiste);
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
