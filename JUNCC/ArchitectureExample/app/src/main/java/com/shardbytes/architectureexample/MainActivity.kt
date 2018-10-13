package com.shardbytes.architectureexample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // destroy viewmodel on activity finish
        noteViewModel = ViewModelProviders.of(this)[NoteViewModel::class.java]
        
        // livedata wont update if activity is not visible
        noteViewModel.allNotes.observe(this, Observer<List<Note>> {
            toast("onChanged")
        })
        
    }
}
