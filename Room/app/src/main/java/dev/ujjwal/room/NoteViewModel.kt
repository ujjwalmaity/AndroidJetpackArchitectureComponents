package dev.ujjwal.room

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao: NoteDao

    init {
        val noteDB = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDB!!.noteDao()
    }

    fun insert(note: Note) {
        InsertAsyncTask(noteDao).execute(note)
    }

    companion object {
        private class InsertAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
            override fun doInBackground(vararg notes: Note): Void? {
                mNoteDao.insert(notes[0])
                return null
            }
        }
    }
}