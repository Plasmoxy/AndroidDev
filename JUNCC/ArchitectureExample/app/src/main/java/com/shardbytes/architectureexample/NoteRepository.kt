package com.shardbytes.architectureexample

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.AsyncTask
import org.jetbrains.anko.doAsync

class NoteRepository(application: Application) {
    
    private val noteDao: NoteDao
    val allNotes: LiveData<List<Note>>
    
    init {
        val database = NoteDatabase.getInstance(application)
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }
    
    // implement the operations on data ( here I add async )
    
    fun insert(note: Note) {
        doAsync { 
            noteDao.insert(note)
        }
    }
    
    fun update(note: Note) {
        doAsync {
            noteDao.update(note)
        }
    }
    
    fun delete(note: Note) {
        doAsync { 
            noteDao.delete(note)
        }
    }
    
    fun deleteAllNotes() {
        doAsync { 
            noteDao.deleteAllNotes()
        }
    }

    // TODO: use tasks if doAsync doesn't work
    companion object {
        
        private class InsertNoteAsyncTask(private val noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {
            override fun doInBackground(vararg params: Note): Void? {
                noteDao.insert(params[0])
                return null
            }
        }
        
    }
    
}