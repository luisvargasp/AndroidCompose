package com.example.jetpackcompose.note_app.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID
@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "notes")
data class Note (
     @PrimaryKey
     val id:UUID=UUID.randomUUID(),
     @ColumnInfo("title")
     val title:String,
     @ColumnInfo("description")
 val description:String,
     @ColumnInfo("entryDate")
     val entryDate: Date =Date.from(Instant.now())) {


}