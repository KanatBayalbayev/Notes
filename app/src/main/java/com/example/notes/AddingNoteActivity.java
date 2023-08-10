package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddingNoteActivity extends AppCompatActivity {
    private EditText inputFromUser;
    private Button buttonToSaveNote;
    private NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_note);
        initViews();

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddingNoteActivity.class);
    }

    private void initViews(){
        inputFromUser = findViewById(R.id.inputFromUser);
        buttonToSaveNote = findViewById(R.id.buttonToSaveNote);
    }

}