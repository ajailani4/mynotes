package com.example.mynotes.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotes.model.`object`.Notes

@Database(entities = [Notes::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object
    {
        private const val DB_NAME = "NOTES_DB"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?
        {
            synchronized(AppDatabase::class)
            {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
            }

            return instance
        }
    }
}