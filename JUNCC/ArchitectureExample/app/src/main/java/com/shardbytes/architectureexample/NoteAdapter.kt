package com.shardbytes.architectureexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    
    private var notes = listOf<Note>()
    
    class NoteHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }
    
    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = notes[position]
        
        holder.view.text_view_title.setText(note.title)
        holder.view.text_view_description.setText(note.description)
        holder.view.text_view_priority.setText(note.priority.toString())
    }

    override fun getItemCount() = notes.size
    
    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged() // notify recycler on data change
    }

}