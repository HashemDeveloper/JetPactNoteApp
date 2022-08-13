package com.android.jetpacknoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.jetpacknoteapp.entities.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}