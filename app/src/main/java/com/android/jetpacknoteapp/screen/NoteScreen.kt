package com.android.jetpacknoteapp.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.jetpacknoteapp.R
import com.android.jetpacknoteapp.components.NoteButton
import com.android.jetpacknoteapp.components.NoteInputText
import com.android.jetpacknoteapp.data.NoteDataSource
import com.android.jetpacknoteapp.entities.Note
import com.android.jetpacknoteapp.utils.formatDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(notes: MutableList<Note>,
               onAddNote: (Note) -> Unit ={},
               onRemoveNote: (Note) -> Unit = {}) {
    var titleState by remember {
        mutableStateOf("")
    }
    var descriptionState by remember {
        mutableStateOf("")
    }
    var buttonSaveState by remember {
        mutableStateOf(true)
    }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
        //TOP BAR
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification icon"
            )
        }, backgroundColor = Color(0xFFDADFE3))
        // Bottom Structure or content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = titleState,
                label = "Title",
                onTextChanged = {
                    if (it.all { chars -> chars.isLetter() || chars.isWhitespace() }) {
                        titleState = it
                    }
                },
            )
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = descriptionState,
                label = "Add a note",
                onTextChanged = {
                    if (it.all { chars -> chars.isLetter() || chars.isWhitespace() }) {
                        descriptionState = it
                    }
                },
            )
            NoteButton(modifier = Modifier, text = "Save", enabled = buttonSaveState) {
                if (titleState.isNotEmpty() && descriptionState.isNotEmpty()) {
                    // save or add to list
                    onAddNote(Note(title = titleState, description = descriptionState))
                    titleState = ""
                    descriptionState = ""
                    Toast.makeText(context, "$titleState is created", Toast.LENGTH_SHORT).show()
                }
            }
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) {item ->
                NoteRow(modifier = Modifier, note = item, onNoteClicked = {
                    onRemoveNote(item)
                })
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(modifier: Modifier, note: Note, onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(), color = Color(0xFFDFE6EB), elevation = 6.dp) {
        Column(
            modifier
                .clickable { onNoteClicked(note)}
                .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = formatDate(note.entryDate.time),
            style = MaterialTheme.typography.caption)
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(NoteDataSource().loadNotes())
}