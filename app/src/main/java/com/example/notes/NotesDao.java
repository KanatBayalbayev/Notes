package com.example.notes;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("Select * from notes")
    List<Note> getAllNotes();

    @Insert
    void addNote(Note note);

    @Query("Delete from notes where id = :noteId")
    void removeNote(int noteId);

}
