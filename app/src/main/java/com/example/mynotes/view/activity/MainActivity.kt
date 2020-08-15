package com.example.mynotes.view.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.R
import com.example.mynotes.model.`object`.Notes
import com.example.mynotes.view.adapter.NotesAdapter
import com.example.mynotes.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notes_list_rv.layoutManager = LinearLayoutManager(this)

        notesAdapter = NotesAdapter(this) {
            showDialog(it)
        }

        notes_list_rv.adapter = notesAdapter

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        notesViewModel.getNotes()?.observe(this, Observer {
            notesAdapter.setNotes(it)
        })

        //When Add FAB is clicked
        add_fab.setOnClickListener {
            val addIntent: Intent = Intent(applicationContext, AddNotes::class.java)
            startActivity(addIntent)
        }
    }

    fun showDialog(notes: Notes)
    {
        val items = arrayOf("Edit", "Delete")

        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setItems(items) { dialog, which ->
            when(which)
            {
                0 -> {
                    goToAdd(notes)
                }

                1 -> {
                    notesViewModel.deleteNotes(notes)
                }
            }
        }

        alertBuilder.show()
    }

    fun goToAdd(notes: Notes)
    {
        val addIntent: Intent = Intent(applicationContext, AddNotes::class.java)
        addIntent.putExtra("Notes", notes)
        startActivity(addIntent)
    }
}
