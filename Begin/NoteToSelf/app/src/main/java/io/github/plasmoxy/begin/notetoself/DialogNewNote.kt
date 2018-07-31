package io.github.plasmoxy.begin.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.dialog_new_note.*

class DialogNewNote : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = View.inflate(activity, R.layout.dialog_new_note, null)
        
        buttonCancel.setOnClickListener { dismiss() }
        buttonOk.setOnClickListener { 
            val note = Note().apply {
                
            }
            
        }
        
        return AlertDialog.Builder(activity)
                .setView(dialogView)
                .setMessage("Add new note")
                .create()
    }
    
}