package com.android.jetpacknoteapp.utils

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date: Date): Long {
        return date.time
    }
    @TypeConverter
    fun dateTimeStamp(timeStamp: Long): Date {
        return Date(timeStamp)
    }
}