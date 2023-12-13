package com.example.jetpackcompose.note_app.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID)=uuid.toString()
    @TypeConverter
    fun toUUID(stringUUID: String)=UUID.fromString(stringUUID)
}