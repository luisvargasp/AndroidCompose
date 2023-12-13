package com.example.jetpackcompose.note_app.repository

import com.example.jetpackcompose.note_app.data.NoteDao
import com.example.jetpackcompose.note_app.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotesRepository@Inject constructor( private val noteDao: NoteDao) {

    suspend  fun addNote(note: Note){
        noteDao.insertNote(note)
    }

    suspend  fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
    suspend  fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend  fun deleteAll(){
        noteDao.deleteAll()
    }

    fun getAllNotes():Flow<List<Note>>{
        return noteDao.getNotes()

    }



}