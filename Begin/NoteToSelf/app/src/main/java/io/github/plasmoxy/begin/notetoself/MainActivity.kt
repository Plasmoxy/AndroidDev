package io.github.plasmoxy.begin.notetoself

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {
    
    private var notes = mutableListOf<Note>()
    private lateinit var noteAdapter : NoteRecyclerAdapter
    
    private lateinit var jsonMapper: ObjectMapper
    private lateinit var notesJsonFile: File
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // setup
        notesJsonFile = File(filesDir, "notes.json")
        jsonMapper = ObjectMapper()
        
        // load notes
        loadNotes()
        
        // setup recycler
        noteRecycler.setHasFixedSize(true)
        noteRecycler.layoutManager = LinearLayoutManager(this)
        
        // setup adapter with loaded notes
        noteAdapter = NoteRecyclerAdapter(this, notes)
        noteRecycler.adapter = noteAdapter
        
        // setup adapter listeners
        noteAdapter.onItemClick = {
            DialogEditNote().apply {
                note = notes[it]
                onDelete = {
                    notes.removeAt(it)
                    noteAdapter.notifyDataSetChanged()
                }
                onOk = { note ->
                    notes[it] = note
                    noteAdapter.notifyDataSetChanged()
                }
            }.show(supportFragmentManager, "shownote")
        }
        
        // fab
        fabAddNote.setOnClickListener {
            DialogEditNote().apply {
                onOk = { note ->
                    createNewNote(note)
                }
            }.show(supportFragmentManager, "newnote")
        }
        
    }

    override fun onPause() {
        saveNotes() // save notes on every pause
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            
            R.id.action_settings -> {
                startActivity(intentFor<SettingsActivity>(
                        
                ))
            }
            
        }
        return super.onOptionsItemSelected(item)
    }

    fun createNewNote(note: Note) {
        notes.add(note)
        noteAdapter.notifyDataSetChanged()
    }
    
    fun loadNotes() {
        try {
            notes = jsonMapper.readValue(notesJsonFile, Array<Note>::class.java).toMutableList()
        } catch(ex: Exception) { when(ex) {
            is JsonParseException, is IOException, is JsonMappingException -> {
                if (ex is IOException) toast("created new notes file")
                else toast("corrupted data, created new notes file")
                
                notesJsonFile.writeText("[]") // empty notes list
            }
            else -> {
                toast("Fatal error loading notes!")
                notesJsonFile.writeText("[]")
            }
        }}
    }
    
    fun saveNotes() {
        jsonMapper.writeValue(notesJsonFile, notes)
    }

}
    
