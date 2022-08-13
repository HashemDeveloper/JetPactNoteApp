package com.android.jetpacknoteapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.android.jetpacknoteapp.screen.NoteScreen
import com.android.jetpacknoteapp.ui.theme.JetPackNoteAppTheme
import com.android.jetpacknoteapp.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           AppEntryPoint()
        }
    }


    @Composable
    private fun AppEntryPoint() {
        JetPackNoteAppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
               NoteApp()
            }
        }
    }
    @Composable
    fun NoteApp(viewModel: AppViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
        val notes = viewModel.noteList.collectAsState().value
        NoteScreen(notes, onAddNote = {
            viewModel.addNote(it)
        }, onRemoveNote = {
            viewModel.removeNode(it)
        })
    }
}
