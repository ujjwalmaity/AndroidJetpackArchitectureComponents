package dev.ujjwal.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @get:Query("SELECT * FROM notes")
    val allNotes: LiveData<List<Note>>

    @Update
    fun update(note: Note)
}