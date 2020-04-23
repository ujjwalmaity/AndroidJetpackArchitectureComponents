package dev.ujjwal.room

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class NoteListAdapter(private val context: Context, private val onDeleteClickListener: OnDeleteClickListener) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private var noteList: List<Note> = mutableListOf()

    interface OnDeleteClickListener {
        fun onDeleteClickListener(myNote: Note)
    }

    fun setNotes(notes: List<Note>) {
        noteList = notes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.setData(note.note, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int = noteList.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var pos: Int = 0

        fun setData(note: String, position: Int) {
            itemView.txvNote.text = note
            pos = position
        }

        fun setListeners() {
            itemView.setOnClickListener {
                val intent = Intent(context, EditNoteActivity::class.java)
                intent.putExtra("note_id", noteList[pos].id)
                intent.putExtra("note", noteList[pos].note)
                (context as Activity).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
            }

            itemView.ivDelete.setOnClickListener {
                onDeleteClickListener.onDeleteClickListener(noteList[pos])
            }
        }
    }
}