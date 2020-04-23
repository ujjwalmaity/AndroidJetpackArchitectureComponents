package dev.ujjwal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private var noteRoomDatabase: NoteRoomDatabase? = null

        internal fun getDatabase(context: Context): NoteRoomDatabase? {
            if (noteRoomDatabase == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    if (noteRoomDatabase == null) {
                        noteRoomDatabase = Room.databaseBuilder(
                            context.applicationContext,
                            NoteRoomDatabase::class.java,
                            "note_db"
                        ).build()
                    }
                }
            }
            return noteRoomDatabase
        }
    }
}