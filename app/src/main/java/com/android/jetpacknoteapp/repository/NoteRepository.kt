package com.android.jetpacknoteapp.repository

import com.android.jetpacknoteapp.data.NoteDatabaseDao
import com.android.jetpacknoteapp.entities.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDatabaseDao) {
    suspend fun addNote(note: Note) = noteDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNoteById(id: String) = noteDao.deleteNote(id)
    suspend fun deleteAllNote(note: Note) = noteDao.deleteNote(note)
    fun getAllNotes(): Flow<MutableList<Note>> = noteDao.getAllNotes().flowOn(Dispatchers.IO).conflate()
}