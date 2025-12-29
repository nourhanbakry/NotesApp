package com.example.notesapp.View.Fragments.Add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.Model.Entity.Note
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NoteViewModel
import com.example.notesapp.databinding.FragmentAddNoteBinding

class AddNote : Fragment() {
    lateinit var binding: FragmentAddNoteBinding
    lateinit var notesViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        notesViewModel = ViewModelProvider(this).get(NoteViewModel::class)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNoteToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_note_save_icon -> {
                    addNote()
                    true
                }
                else -> false
            }
        }

    }



    private fun addNote() {
        val title = binding.addNoteTitleEt.text.toString()
        val content = binding.addNoteContentEt.text.toString()
        if (title.isEmpty() && content.isEmpty()){
            binding.addNoteTitleEt.error = "Title is required"
            binding.addNoteContentEt.error = "Content is required"
            return
        }

        if (title.isEmpty()){
            binding.addNoteTitleEt.error = "Title is required"
            return
        }

        if (content.isEmpty()){
            binding.addNoteContentEt.error = "Content is required"
            return
        }
        val note = Note(title = title, content =  content)
        notesViewModel.addNote(note)
        Toast.makeText(requireContext(),"Note Added Successfully", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addNote_to_showNotes)
    }

}