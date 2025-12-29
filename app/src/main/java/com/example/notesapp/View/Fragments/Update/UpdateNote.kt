package com.example.notesapp.View.Fragments.Update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.Model.Entity.Note
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NoteViewModel
import com.example.notesapp.databinding.FragmentShowNotesBinding
import com.example.notesapp.databinding.FragmentUpdateNoteBinding
import kotlin.getValue

class UpdateNote : Fragment() {
    lateinit var binding: FragmentUpdateNoteBinding
    lateinit var notesViewModel: NoteViewModel
    private val args by navArgs<UpdateNoteArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        notesViewModel = ViewModelProvider(this).get(NoteViewModel::class)
        binding.editNoteTitleEt.setText(args.currentNote.title)
        binding.editNoteContentEt.setText(args.currentNote.content)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()


        binding.editNoteToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.update_menu_update_icon -> {
                    updateNote()
                    true
                }
                R.id.update_menu_delete_icon -> {
                    deleteNote()
                    true
                }

                else -> false
            }


        }
    }



        private fun updateNote() {
            val title = binding.editNoteTitleEt.text.toString()
            val content = binding.editNoteContentEt.text.toString()

            val note = Note(id = args.currentNote.id, title = title, content = content)
            notesViewModel.updateNote(note)
            Toast.makeText(requireContext(), "Note Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateNote_to_showNotes)
        }

        private fun deleteNote() {
            val alertDialog = AlertDialog.Builder(requireContext()).apply {
                setTitle("Delete Current Note")
                setMessage("Are You Sure You want to delete this note?")
                setPositiveButton("Yes") { _, _ ->
                    notesViewModel.deleteNote(args.currentNote)
                    findNavController().navigate(R.id.action_updateNote_to_showNotes)
                    Toast.makeText(
                        requireContext(),
                        "Note Deleted Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                setNegativeButton("No") { _, _ -> }
            }.create().show()


        }


}
