package com.example.jetpackcompose.note_app.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.jetpackcompose.note_app.data.NoteDao
import com.example.jetpackcompose.note_app.data.NoteDatabase
import com.example.jetpackcompose.note_app.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
@RequiresApi(Build.VERSION_CODES.O)
object AppModule {
    @Singleton
    @Provides
    fun providesNoteDatabase(@ApplicationContext   context:Context):NoteDatabase{
        return Room.databaseBuilder(context,NoteDatabase::class.java,"appDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesNoteDao(noteDatabase:NoteDatabase):NoteDao{
        return noteDatabase.noteDao()
    }
    @Singleton
    @Provides
    fun providesNoteRepository(noteDao: NoteDao):NotesRepository{
        return NotesRepository(noteDao)
    }
}
