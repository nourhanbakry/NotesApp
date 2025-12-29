package com.example.notesapp.View

import com.example.notesapp.Model.Entity.Note

sealed class NotesViewState {
    object Loading : NotesViewState()
    object Empty :NotesViewState()
    data class Success(val notes: List<Note>) : NotesViewState()
}