package com.android.jetpacknoteapp.entities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import com.android.jetpacknoteapp.utils.DateConverter
import com.android.jetpacknoteapp.utils.UUIDConverter
import java.time.Instant
import java.util.*
@Entity(tableName = "notes_tbl")
@RequiresApi(Build.VERSION_CODES.O)
@TypeConverters(DateConverter::class,UUIDConverter::class)
data class Note constructor(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo
    val title: String,
    @ColumnInfo(name = "desc")
    val description: String,
    @ColumnInfo(name = "date")
    val entryDate: Date = Date.from(Instant.now())
)
