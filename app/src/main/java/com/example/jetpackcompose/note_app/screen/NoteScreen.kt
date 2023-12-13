package com.example.jetpackcompose.note_app.screen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.note_app.components.NoteButton
import com.example.jetpackcompose.note_app.components.NoteInputText
import com.example.jetpackcompose.note_app.data.NotesDataSource
import com.example.jetpackcompose.note_app.model.Note
import com.example.jetpackcompose.note_app.util.formatDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(notes:List<Note>,onAddNote:(Note)->Unit,
               onRemoveNote:(Note)->Unit

){
    val title  = remember {
        mutableStateOf("")
    }
    val description= remember {
        mutableStateOf("")
    }
    val context= LocalContext.current
    Column (modifier=Modifier.padding(6.dp)){
        CenterAlignedTopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name))
        }


            , actions = {
                Icon(imageVector= Icons.Rounded.Notifications,contentDescription = "Icon")


            }, navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFFDADFE3)))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                NoteInputText(modifier=Modifier.padding(vertical =8.dp),text = title.value, label = "Title", onTextChange = { title.value=it})
                NoteInputText(modifier=Modifier.padding(vertical =8.dp),text = description.value, label = "Add a note", onTextChange = {description.value=it})

                NoteButton(text = "Save", onClick = {
                    if(title.value.isNotEmpty()&&description.value.isNotEmpty()){
                        //Save
                        onAddNote(Note(title=title.value, description = description.value))
                        title.value=""
                        description.value=""
                        Toast.makeText(context,"Note added",Toast.LENGTH_SHORT).show()

                    }

                })

            }
            Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){note->
                NoteRow(note = note, onNoteClick = {
                    onRemoveNote(it)
                })

            }
        }

    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(NotesDataSource().loadNotes(),{

    },{

    })
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(modifier: Modifier=Modifier,note: Note,onNoteClick:(Note)->Unit){

    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB), shadowElevation = 6.dp

    ) {
        Column(
            modifier
                .clickable { onNoteClick(note)}
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start

        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.titleSmall)
            Text(text = formatDate(note.entryDate) , style = MaterialTheme.typography.titleSmall)

        }

    }
}
