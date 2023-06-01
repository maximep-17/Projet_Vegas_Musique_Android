package com.example.projetvegasmusiqueandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ArtisteAdapter extends ArrayAdapter<Artiste> {

    public ArtisteAdapter(Context context, List<Artiste> artistes){

        super(context, 0, artistes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        Artiste artiste = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.name_cell, parent, false);

        TextView name = convertView.findViewById(R.id.cellName);
        TextView desc = convertView.findViewById(R.id.cellDesc);

        name.setText(artiste.getName());
        desc.setText(artiste.getDescription());

        return convertView;
    }
}
