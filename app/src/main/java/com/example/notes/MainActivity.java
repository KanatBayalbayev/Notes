package com.example.notes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton buttonToAddNote;
    private NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        openAddingNoteActivity();
        attachAdapterToRecyclerView();


    }


    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        buttonToAddNote = findViewById(R.id.buttonToAddNote);
    }

    private void openAddingNoteActivity() {
        buttonToAddNote.setOnClickListener(v -> {
            Intent intent = AddingNoteActivity.newIntent(this);
            startActivity(intent);
        });
    }

    private void attachAdapterToRecyclerView() {
        NotesAdapter notesAdapter = new NotesAdapter();
        recyclerView.setAdapter(notesAdapter);
    }
}