package com.example.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.Model.Entity.Note
import com.example.notesapp.Model.LocalData.NoteDAO
import com.example.notesapp.Model.LocalData.NoteDataBase
import com.example.notesapp.Model.LocalData.NoteReposotary
import com.example.notesapp.View.NotesViewState
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
   private val noteReposotary: NoteReposotary
    val notesState = MutableLiveData<NotesViewState>()

    private fun readAllNotesFromDb() {
        noteReposotary.readAllNote.observeForever { notes ->
            notesState.value = when {
                notes.isEmpty() -> NotesViewState.Empty
                else -> NotesViewState.Success(notes)
            }
        }
    }

    fun addNote(note: Note){
      viewModelScope.launch {
         noteReposotary.addNote(note)
      }
   }

   fun updateNote(note: Note){
      viewModelScope.launch {
         noteReposotary.updateNote(note)
      }
   }

   fun deleteNote(note: Note){
      viewModelScope.launch {
         noteReposotary.deleteNote(note)
      }
   }

    fun searchForNote(searchQuery: String?): LiveData<NotesViewState> {

        if (searchQuery.isNullOrBlank()) {
            return notesState
        }

        val resultState = MutableLiveData<NotesViewState>()
        resultState.value = NotesViewState.Loading

        noteReposotary.searchForNote(searchQuery)
            .observeForever { notes ->

                resultState.value = when {
                    notes.isEmpty() -> NotesViewState.Empty
                    else -> NotesViewState.Success(notes)
                }
            }

        return resultState
    }

    init {
       val noteDAO: NoteDAO = NoteDataBase.getNoteDataBaseInstance(application).noteDAO()
       noteReposotary = NoteReposotary(noteDAO)
       notesState.value = NotesViewState.Loading
       readAllNotesFromDb()
   }
}