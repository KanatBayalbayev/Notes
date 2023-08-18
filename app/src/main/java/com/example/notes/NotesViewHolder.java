package com.example.notes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewNote;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewNote = itemView.findViewById(R.id.textViewNote);
    }
}
