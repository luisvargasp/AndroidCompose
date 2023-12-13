package com.example.jetpackcompose.note_app.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.note_app.data.NotesDataSource
import com.example.jetpackcompose.note_app.model.Note
import com.example.jetpackcompose.note_app.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NoteViewModel @Inject constructor(private val notesRepository: NotesRepository):ViewModel() {
    //private var  noteList= mutableStateListOf<Note>()
    private val  _noteList= MutableStateFlow<List<Note>>(emptyList())
    val noteList=_noteList.asStateFlow()


    init {
       viewModelScope.launch (Dispatchers.IO){
           notesRepository.getAllNotes().distinctUntilChanged().collect{list->

              // if(list.isNotEmpty()){
                   _noteList.value=list
             //  }

           }

       }
    }


    fun addNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.addNote(note)
        }

    }
    fun updateNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNote(note)
        }

    }

    fun removeNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(note)
        }
    }
    fun getAllNotes()=noteList
}