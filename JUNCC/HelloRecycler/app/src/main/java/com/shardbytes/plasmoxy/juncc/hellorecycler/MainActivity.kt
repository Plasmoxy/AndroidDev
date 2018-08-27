package com.shardbytes.plasmoxy.juncc.hellorecycler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    private val items = mutableListOf<Item>()
    private lateinit var itemAdapter : ItemAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        itemRecycler.setHasFixedSize(true)
        itemRecycler.layoutManager = LinearLayoutManager(this)
        itemAdapter = ItemAdapter(items) {
            Toast.makeText(this, "item $it pressed!", Toast.LENGTH_SHORT).show()
            moreItems()
        }
        itemRecycler.adapter = itemAdapter
        
        moreItems()
        
    }
    
    fun moreItems() {
        items.add(Item("Hello", "hello world"))
        itemAdapter.notifyDataSetChanged()
    }
    
}
