package com.example.mynotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mynotes.model.`object`.Notes
import com.example.mynotes.model.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private var notesRepository: NotesRepository = NotesRepository(application)
    private var notesLiveData: LiveData<List<Notes>>? = notesRepository.getNotes()

    //Insert notes
    fun insertNotes(notes: Notes) {
        notesRepository.insertNotes(notes)
    }

    //Read notes
    fun getNotes(): LiveData<List<Notes>>? {
        return notesLiveData
    }

    //Update notes
    fun updateNotes(notes: Notes) {
        notesRepository.updateNotes(notes)
    }

    //Delete notes
    fun deleteNotes(notes: Notes) {
        notesRepository.deleteNotes(notes)
    }
}