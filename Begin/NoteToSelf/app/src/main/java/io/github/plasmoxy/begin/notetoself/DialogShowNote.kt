package io.github.plasmoxy.begin.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View

import kotlinx.android.synthetic.main.dialog_show_note.*

class DialogShowNote : DialogFragment() {
    
    private val coreView : View by lazy {
        View.inflate(activity, R.layout.dialog_show_note, null)
    }
    override fun getView() = coreView
    
    // note to show, assign externally !!
    lateinit var note: Note

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        
        buttonOk.setOnClickListener { dismiss() }
        
        textViewNoteTitle.text = note.title
        textViewNoteDescription.text = note.description
        
        if (!note.important) iconImportant.visibility = View.GONE
        if (!note.idea) iconIdea.visibility = View.GONE
        if (!note.todo) iconTodo.visibility = View.GONE
        
        return AlertDialog.Builder(activity)
                .setView(view)
                .create()
    }
    
    
    
}