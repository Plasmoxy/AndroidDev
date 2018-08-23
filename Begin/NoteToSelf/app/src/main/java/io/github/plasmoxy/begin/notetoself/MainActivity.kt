package io.github.plasmoxy.begin.notetoself

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        
        
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
        
    }

}
    
