package com.android.jetpacknoteapp.utils

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String {
        return uuid.toString()
    }
    @TypeConverter
    fun stringToUUID(value: String): UUID {
        return UUID.fromString(value)
    }
}