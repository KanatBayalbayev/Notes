package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddingNoteActivity extends AppCompatActivity {
    private EditText inputFromUser;
    private Button buttonToSaveNote;

    private AddingNoteViewModel addingNoteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_note);
        initViews();
        addingNoteViewModel = new ViewModelProvider(this).get(AddingNoteViewModel.class);

        addingNoteViewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldCloseScreen) {
                if (shouldCloseScreen){
                    finish();
                }

            }
        });



        buttonToSaveNote.setOnClickListener(v -> {
            String textNote = inputFromUser.getText().toString();
            if (textNote.isEmpty()){
                Toast.makeText(this, "Введите заметку!", Toast.LENGTH_SHORT).show();
                return;
            }
            Note note = new Note(textNote);
            addingNoteViewModel.addNote(note);
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