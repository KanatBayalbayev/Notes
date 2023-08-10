package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton buttonToAddNote;
    private NotesDatabase notesDatabase;
    private NotesAdapter notesAdapter;
    private NotesDao notesDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        openAddingNoteActivity();
        notesDatabase = NotesDatabase.getInstance(getApplication());
        notesDao = notesDatabase.notesDao();
        notesAdapter = new NotesAdapter();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                int position = viewHolder.getAdapterPosition();
                Note note = notesAdapter.getNotes().get(position);
                notesDao.removeNote(note.getId());
                displayNotes();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                Log.d("MainActivity", String.valueOf(note.getId()));
//                displayNotes();
            }
        });
        recyclerView.setAdapter(notesAdapter);

        displayNotes();

    }

    @Override
    protected void onResume() {
        super.onResume();
        displayNotes();
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

    private void displayNotes() {
        notesAdapter.setNotes(notesDao.getAllNotes());

    }


}