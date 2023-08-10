package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton buttonToAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        openAddingNoteActivity();

    }

    private void initViews(){
        recyclerView = findViewById(R.id.recyclerView);
        buttonToAddNote = findViewById(R.id.buttonToAddNote);
    }
    private void openAddingNoteActivity(){
        buttonToAddNote.setOnClickListener(v -> {
            Intent intent = AddingNoteActivity.newIntent(this);
            startActivity(intent);
        });
    }
}