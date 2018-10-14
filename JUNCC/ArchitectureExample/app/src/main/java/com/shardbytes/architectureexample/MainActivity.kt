package com.shardbytes.architectureexample

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    
    companion object {
        const val ADD_NOTE_REQUEST = 1
    }
    
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        button_add_note.setOnClickListener { 
            startActivityForResult(intentFor<AddNoteActivity>(), ADD_NOTE_REQUEST)
        }
        
        // setup recycler
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        
        // setup adapter for recycler
        val adapter = NoteAdapter()
        recycler_view.adapter = adapter
        
        // destroy viewmodel on activity finish
        noteViewModel = ViewModelProviders.of(this)[NoteViewModel::class.java]
        
        // livedata wont update if activity is not visible
        // setup observer for data from notes viewmodel
        noteViewModel.allNotes.observe(this, Observer<List<Note>> {
            if (it != null) {
                adapter.setNotes(it)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE)
            val description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            val priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1)
            
            val note = Note(title, description, priority)
            noteViewModel.insert(note)
            
            toast("Note saved.")
        } else {
            toast("Canceled.")
        }
    }
    
}
