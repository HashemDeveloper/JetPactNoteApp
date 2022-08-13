package com.android.jetpacknoteapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.jetpacknoteapp.data.NoteDataSource
import com.android.jetpacknoteapp.entities.Note
import com.android.jetpacknoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AppViewModel @Inject constructor(private val repo: NoteRepository): ViewModel() {
    private val _noteList: MutableStateFlow<MutableList<Note>> = MutableStateFlow(mutableListOf())
    val noteList: StateFlow<MutableList<Note>> = _noteList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllNotes().distinctUntilChanged().collect {noteList ->
                if (noteList.isNotEmpty()) {
                    _noteList.value = noteList
                }
            }
        }
    }
    fun addNote(note: Note) = viewModelScope.launch { repo.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repo.updateNote(note) }
    fun removeNode(note: Note) = viewModelScope.launch { repo.deleteAllNote(note) }
}