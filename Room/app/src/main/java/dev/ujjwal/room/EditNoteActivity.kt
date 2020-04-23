package dev.ujjwal.room

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new.*

class EditNoteActivity : AppCompatActivity() {

    private var id: String? = null

    companion object {
        const val NOTE_ID = "note_id"
        internal const val UPDATED_NOTE = "new_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val bundle: Bundle? = intent.extras
        bundle?.let {
            id = bundle.getString("note_id")
            val note = bundle.getString("note")
            etNote.setText(note)
        }

        bSave.setOnClickListener {
            val updatedNote = etNote!!.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra(NOTE_ID, id)
            resultIntent.putExtra(UPDATED_NOTE, updatedNote)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }

        bCancel.setOnClickListener {
            finish()
        }
    }
}
