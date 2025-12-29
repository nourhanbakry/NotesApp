package com.example.notesapp.Model.LocalData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.Model.Entity.Note

@Dao
interface NoteDAO {

    @Query("Select * from notes order by updatedAt desc")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = REPLACE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("Select * from notes Where title like :searchQuery or content like :searchQuery order by updatedAt desc")
    fun searchForNotes(searchQuery:String?): LiveData<List<Note>>


}