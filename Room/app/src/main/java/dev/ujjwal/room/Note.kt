package dev.ujjwal.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note(
    @field:PrimaryKey
    val id: String,

    @field:ColumnInfo(name = "note")
    val note: String
)