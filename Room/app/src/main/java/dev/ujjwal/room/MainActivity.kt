package dev.ujjwal.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val noteId = UUID.randomUUID().toString()
            val note = Note(noteId, data!!.getStringExtra(NewActivity.NOTE_ADDED)!!)
            noteViewModel.insert(note)
            Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, R.string.not_saved, Toast.LENGTH_LONG).show()
        }
    }
}
