package com.example.mynotes.view.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.model.`object`.Notes
import com.example.mynotes.view.activity.AddNotes
import kotlinx.android.synthetic.main.notes_list.view.*

class NotesAdapter(private val context: Context?, private val listener: (Notes) -> Unit) : RecyclerView.Adapter<NoteViewHolder>() {
    private var notesList = listOf<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.notes_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if(context != null)
        {
            holder.bindItem(notesList[position], listener)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setNotes(notesList: List<Notes>)
    {
        this.notesList = notesList
        notifyDataSetChanged()
    }
}

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(notes: Notes, listener: (Notes) -> Unit)
    {
        itemView.notes_title.text = notes.title
        itemView.notes_content.text = notes.desc

        itemView.notes_card.setOnClickListener {
            listener(notes)
        }
    }
}
