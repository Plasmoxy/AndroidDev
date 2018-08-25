package io.github.plasmoxy.begin.notetoself

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class NoteRecyclerAdapter(private val activity: MainActivity,
                          private val data: MutableList<Note>) : RecyclerView.Adapter<NoteRecyclerAdapter.ItemViewHolder>() {
    
    var onItemClick : (position: Int) -> Unit = {}
    
    class ItemViewHolder(itemView: View, onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.textViewTitle)
        val description = itemView.findViewById<TextView>(R.id.textViewDescription)
        
        val todo = itemView.findViewById<ImageView>(R.id.imageViewTodo)
        val idea = itemView.findViewById<ImageView>(R.id.imageViewIdea)
        val important = itemView.findViewById<ImageView>(R.id.imageViewImportant)
        
        init {
            itemView.findViewById<CardView>(R.id.noteCard).setOnClickListener {
                // if position is actually valid
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick(adapterPosition)
                }
            }
        }
        
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false),
                onItemClick
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.title.text = item.title
        holder.description.text = item.description
        
        if (!item.important) holder.important.visibility = View.GONE
        if (!item.idea) holder.idea.visibility = View.GONE
        if (!item.todo) holder.todo.visibility = View.GONE
        
    }

    override fun getItemCount() = data.size

}