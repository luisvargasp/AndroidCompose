package com.example.jetpackcompose.note_app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcompose.note_app.data.NotesDataSource
import com.example.jetpackcompose.note_app.model.Note
import com.example.jetpackcompose.note_app.screen.NoteScreen
import com.example.jetpackcompose.note_app.screen.NoteViewModel
import com.example.jetpackcompose.note_app.ui.theme.JetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.toList

@AndroidEntryPoint
class NoteAppActivity : ComponentActivity() {


    private val noteViewModel:NoteViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // val noteViewMoldel= viewModel<NoteViewModel>()

                    NotesApp(noteViewModel = noteViewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTheme {
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesApp(noteViewModel: NoteViewModel){

    NoteScreen(noteViewModel.noteList.collectAsState().value,{
        noteViewModel.addNote(it)
    },{
        noteViewModel.removeNote(it)
    })


}