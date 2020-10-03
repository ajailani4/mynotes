package com.example.mynotes.model.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mynotes.model.`object`.Notes
import com.example.mynotes.model.database.AppDatabase
import com.example.mynotes.model.database.NotesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesRepository(application: Application) {
    private val notesDao: NotesDao?
    private var notesLiveData: LiveData<List<Notes>>? = null

    init {
        val db = AppDatabase.getInstance(application.applicationContext)
        notesDao = db?.notesDao()
        notesLiveData = notesDao?.getNotes()
    }

    //Insert notes
    fun insertNotes(notes: Notes) = CoroutineScope(Dispatchers.IO).launch {
        notesDao?.insertNotes(notes)
    }

    //Read notes
    fun getNotes(): LiveData<List<Notes>>? = notesLiveData

    //Update notes
    fun updateNotes(notes: Notes) = CoroutineScope(Dispatchers.IO).launch {
        notesDao?.updateNotes(notes)
        Log.d("updating", "true")
    }

    //Delete notes
    fun deleteNotes(notes: Notes) = CoroutineScope(Dispatchers.IO).launch {
        notesDao?.deleteNotes(notes)
    }
}