package com.example.notesapp.View.Fragments.Show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigationevent.NavigationEventTransitionState
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Model.Entity.Note
import com.example.notesapp.R
import com.example.notesapp.databinding.NoteItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ShowNotesAdapter: RecyclerView.Adapter<ShowNotesAdapter.NoteViewHolder>() {
    private val notesList =  mutableListOf<Note>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {
       val currentItem = notesList[position]
        holder.noteTitle.text = currentItem.title
        holder.noteContent.text = currentItem.content
        holder.noteDate.text =  "Updated At: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(currentItem.updatedAt)}"
        holder.noteCard.setOnClickListener {
            val action= ShowNotesDirections.actionShowNotesToUpdateNote(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun updateList(newList: List<Note>) {
        val diffCallback = NotesDiffUtil(notesList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        notesList.clear()
        notesList.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemCount(): Int  = notesList.size

    class NoteViewHolder(val item: View): RecyclerView.ViewHolder(item){
        val noteCard: CardView = item.findViewById(R.id.note_item_card)
        val noteTitle: TextView = item.findViewById(R.id.note_item_title)
        val noteContent: TextView = item.findViewById(R.id.note_item_content)
        val noteDate :TextView = item.findViewById(R.id.note_item_date)

    }
}


