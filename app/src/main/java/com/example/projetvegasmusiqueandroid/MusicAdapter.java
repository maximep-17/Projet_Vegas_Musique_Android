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

public class MusicAdapter extends ArrayAdapter<Music> {

    public MusicAdapter(Context context, List<Music> music){

        super(context, 0, music);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        Music music = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.name_cell, parent, false);

        TextView name = convertView.findViewById(R.id.cellName);
        TextView desc = convertView.findViewById(R.id.cellDesc);

        name.setText(music.getName());
        desc.setText(music.getDescription());

        return convertView;
    }
}
