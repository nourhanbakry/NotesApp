package com.example.notesapp.Model.LocalData

import androidx.lifecycle.LiveData
import com.example.notesapp.Model.Entity.Note

class NoteReposotary(val noteDAO: NoteDAO) {

    val readAllNote: LiveData<List<Note>> = noteDAO.getAllNotes()

    suspend fun addNote(note: Note){
        noteDAO.addNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDAO.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDAO.deleteNote(note)
    }

    fun searchForNote(searchQuery: String?) : LiveData<List<Note>> {
        return noteDAO.searchForNotes("%$searchQuery%")
    }

}