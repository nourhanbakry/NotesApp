package com.example.notesapp.View.Fragments.Show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.View.NotesViewState
import com.example.notesapp.ViewModel.NoteViewModel
import com.example.notesapp.databinding.FragmentShowNotesBinding
class ShowNotes : Fragment() {
    lateinit var binding: FragmentShowNotesBinding
    lateinit var notesViewModel: NoteViewModel
    lateinit var notesAdapter: ShowNotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowNotesBinding.inflate(inflater,container,false)
        notesAdapter = ShowNotesAdapter()
        notesViewModel = ViewModelProvider(this).get(NoteViewModel::class)
        notesViewModel.notesState.observe(viewLifecycleOwner){ state ->
            render(state)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notesSearchBar.clearFocus()
        binding.notesSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                notesViewModel.searchForNote(query).observe(viewLifecycleOwner){
                    state -> render(state)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesViewModel.searchForNote(newText).observe(viewLifecycleOwner){
                        state -> render(state)
                }

                return false
            }
        })
        binding.addFabBtn.setOnClickListener {
            findNavController().navigate(R.id.action_showNotes_to_addNote)
        }
        binding.notesRecyclerView.adapter = notesAdapter
    }

    private fun render (state: NotesViewState){
        when (state) {
            is NotesViewState.Loading -> {
                binding.loadingPrograssBar.visibility= View.VISIBLE
                binding.notesRecyclerView.visibility = View.GONE
                binding.emptyNoteImage.visibility = View.GONE
            }

            is NotesViewState.Empty -> {
                binding.loadingPrograssBar.visibility = View.GONE
                binding.notesRecyclerView.visibility = View.GONE
                binding.emptyNoteImage.visibility = View.VISIBLE
            }

            is NotesViewState.Success -> {
                binding.loadingPrograssBar.visibility = View.GONE
                binding.emptyNoteImage.visibility = View.GONE
                binding.notesRecyclerView.visibility = View.VISIBLE

                notesAdapter.updateList(state.notes)
            }
        }
    }

}