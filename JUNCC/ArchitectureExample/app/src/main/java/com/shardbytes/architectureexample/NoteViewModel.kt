package com.shardbytes.architectureexample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

// AndroidViewModel adds application context
class NoteViewModel(app: Application) : AndroidViewModel(app) {
    
    private val repository = NoteRepository(app)
    
    // delegate allNotes property
    val allNotes get() = repository.allNotes
    
    // delegate repository operations
    fun insert(note: Note) = repository.insert(note)
    fun update(note: Note) = repository.update(note)
    fun delete(note: Note) = repository.delete(note)
    fun deleteAllNotes() = repository.deleteAllNotes()
    
}