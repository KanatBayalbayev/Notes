package com.example.notes;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1,exportSchema = false)
abstract class NotesDatabase extends RoomDatabase {

    static NotesDao notesDao;

    private static NotesDatabase instance = null;
    private static final String DATABASE_NAME = "notes_db";

    static NotesDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    NotesDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

}
