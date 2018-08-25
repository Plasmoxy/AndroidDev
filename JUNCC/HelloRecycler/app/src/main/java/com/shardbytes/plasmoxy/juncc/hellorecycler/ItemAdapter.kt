package com.shardbytes.plasmoxy.juncc.hellorecycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ItemAdapter(private val data: MutableList<Item>,
                  private val buttonCallback: (Int) -> Unit) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val callbackButton: Button = itemView.findViewById(R.id.buttonMore)
    }
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.textViewTitle.text = item.title
        holder.textViewDescription.text = item.description
        holder.callbackButton.setOnClickListener { buttonCallback(position) }
    }

    override fun getItemCount() = data.size
    
}