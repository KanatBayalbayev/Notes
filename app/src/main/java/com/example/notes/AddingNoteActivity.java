package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class AddingNoteActivity extends AppCompatActivity {
    private EditText inputFromUser;
    private Button buttonToSaveNote;
    private NotesDao notesDao;
    private NotesDatabase notesDatabase;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_note);
        initViews();
        notesDatabase = NotesDatabase.getInstance(getApplication());
        notesDao = notesDatabase.notesDao();


        buttonToSaveNote.setOnClickListener(v -> {
            String textNote = inputFromUser.getText().toString();
            Note note = new Note(textNote);
            notesDao.addNote(note);
            finish();
        });


    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddingNoteActivity.class);
    }

    private void initViews() {
        inputFromUser = findViewById(R.id.inputFromUser);
        buttonToSaveNote = findViewById(R.id.buttonToSaveNote);
    }


}