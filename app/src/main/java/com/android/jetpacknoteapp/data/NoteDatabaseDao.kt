package com.android.jetpacknoteapp.data

import androidx.room.*
import com.android.jetpacknoteapp.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query("select * from notes_tbl")
    fun getAllNotes(): Flow<MutableList<Note>>

    @Query("select * from notes_tbl where id=:id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("delete from notes_tbl")
    suspend fun deleteAll()

    @Query("delete from notes_tbl where id=:id")
    suspend fun deleteNote(id: String)

    @Delete
    suspend fun deleteNote(note: Note)
}