package com.shardbytes.architectureexample

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_add_note.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class AddNoteActivity : AppCompatActivity() {
    
    companion object {
        const val EXTRA_TITLE = "com.shardbytes.architectureexample.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.shardbytes.architectureexample.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.shardbytes.architectureexample.EXTRA_PRIORITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        
        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 10
        
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"
    }
    
    private fun saveNote() {
        val title = edit_text_title.text.toString()
        val description = edit_text_description.text.toString()
        val priority = number_picker_priority.value
        
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            toast("Please enter a title and description.")
            return
        }
        
        setResult(Activity.RESULT_OK, intentFor<Any>(
                EXTRA_TITLE to title,
                EXTRA_DESCRIPTION to description,
                EXTRA_PRIORITY to priority
        ))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    
}
