package io.github.plasmoxy.begin.notetoself

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    
    val notes = mutableListOf<Note>()
    private lateinit var noteAdapter : NoteRecyclerAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        noteRecycler.setHasFixedSize(true)
        noteRecycler.layoutManager = LinearLayoutManager(this)
        
        noteAdapter = NoteRecyclerAdapter(this, notes)
        noteRecycler.adapter = noteAdapter
        
        noteAdapter.onItemClick = {
            DialogShowNote().apply {
                note = notes[it]
                onDelete = {
                    notes.removeAt(it)
                    noteAdapter.notifyDataSetChanged()
                    dismiss()
                }
            }.show(supportFragmentManager, "shownote")
        }
        
        
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            
            R.id.action_add_note -> {
                DialogNewNote().show(supportFragmentManager, "newnote")
            }
            
        }
        return super.onOptionsItemSelected(item)
    }

    fun createNewNote(note: Note) {
        notes += note
        noteAdapter.notifyDataSetChanged()
    }

}
    
