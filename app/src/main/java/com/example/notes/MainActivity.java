package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton buttonToAddNote;
    private LinearLayout linearLayout;

    private NotesAdapter notesAdapter;
    private MainViewModel mainViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        openAddingNoteActivity();
        notesAdapter = new NotesAdapter();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if(!notes.isEmpty()){
                    linearLayout.setVisibility(View.GONE);
                }
                notesAdapter.setNotes(notes);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
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
                        mainViewModel.removeNote(note);
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


    }


    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        buttonToAddNote = findViewById(R.id.buttonToAddNote);
        linearLayout = findViewById(R.id.linearLayout);

    }

    private void openAddingNoteActivity() {
        buttonToAddNote.setOnClickListener(v -> {
            Intent intent = AddingNoteActivity.newIntent(this);
            startActivity(intent);
        });
    }




}