package com.example.jetpackcompose.note_app.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetpackcompose.note_app.model.Note
import com.example.jetpackcompose.note_app.util.DateConverter
import com.example.jetpackcompose.note_app.util.UUIDConverter

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDao
}