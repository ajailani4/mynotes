package com.example.mynotes.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotes.model.`object`.Notes

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotes(notes: Notes)

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Notes>>

    @Update
    suspend fun updateNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)
}