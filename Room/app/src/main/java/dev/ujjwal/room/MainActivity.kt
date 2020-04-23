package dev.ujjwal.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel: NoteViewModel

    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        const val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        val noteListAdapter = NoteListAdapter(this)
        recyclerview.apply {
            adapter = noteListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this, androidx.lifecycle.Observer { notes ->
            notes?.let {
                noteListAdapter.setNotes(it)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val noteId = UUID.randomUUID().toString()
            val note = Note(noteId, data!!.getStringExtra(NewNoteActivity.NOTE_ADDED)!!)
            noteViewModel.insert(note)
            Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show()
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val note = Note(
                data!!.getStringExtra(EditNoteActivity.NOTE_ID)!!,
                data.getStringExtra(EditNoteActivity.UPDATED_NOTE)!!
            )
            noteViewModel.update(note)
            Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, R.string.not_saved, Toast.LENGTH_LONG).show()
        }
    }
}
