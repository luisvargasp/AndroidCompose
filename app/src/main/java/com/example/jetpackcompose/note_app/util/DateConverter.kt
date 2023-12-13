package com.example.jetpackcompose.note_app.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun timeStampFromDate(date: Date)=date.time

    @TypeConverter
    fun dateFromTimeStamp(timeStamp: Long)=Date(timeStamp)
}