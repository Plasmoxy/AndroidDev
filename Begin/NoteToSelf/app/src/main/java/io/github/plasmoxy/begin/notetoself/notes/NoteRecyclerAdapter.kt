package io.github.plasmoxy.begin.notetoself.notes

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.plasmoxy.begin.notetoself.MainActivity
import io.github.plasmoxy.begin.notetoself.R

class NoteRecyclerAdapter(private val activity: MainActivity,
                          private val data: MutableList<Note>) : RecyclerView.Adapter<NoteRecyclerAdapter.ItemViewHolder>() {
    
    var onItemClick : (position: Int) -> Unit = {}
    
    class ItemViewHolder(itemView: View, onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        
        val card = itemView.findViewById<CardView>(R.id.noteCard)
        val title = itemView.findViewById<TextView>(R.id.textViewTitle)
        val description = itemView.findViewById<TextView>(R.id.textViewDescription)
        
        val todo = itemView.findViewById<ImageView>(R.id.imageViewTodo)
        val idea = itemView.findViewById<ImageView>(R.id.imageViewIdea)
        val important = itemView.findViewById<ImageView>(R.id.imageViewImportant)
        
        init {
            // set styling
            
            
            
            card.setOnClickListener {
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
        
        // update text in recycled view
        holder.title.text = item.title
        holder.description.text = item.description
        
        // update image visibilities in recycled view
        holder.important.visibility = if (item.important) View.VISIBLE else View.GONE
        holder.idea.visibility = if (item.idea) View.VISIBLE else View.GONE
        holder.todo.visibility = if (item.todo) View.VISIBLE else View.GONE
        
    }
    
    override fun getItemCount() = data.size

}