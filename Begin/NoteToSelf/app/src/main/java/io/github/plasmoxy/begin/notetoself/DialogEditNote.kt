package io.github.plasmoxy.begin.notetoself

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.dialog_edit_note.*

// note: USE v7 support AlertDialog or stuff will crash on kitkat ! always check if you're using support libraries

class DialogEditNote : DialogFragment() {
    
    private val coreView : View by lazy {
        View.inflate(activity, R.layout.dialog_edit_note, null)
    }
    override fun getView() = coreView // reroute so kotlin synthetic works
    
    // init outside !
    var note = Note()
    var onDelete: () -> Unit = {}
    var onOk: (Note) -> Unit = {}
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        
        buttonDelete.setOnClickListener {
            onDelete()
            dismiss()
        }
        
        buttonOk.setOnClickListener {
            onOk(Note(
                    editTextNoteTitle.text.toString(),
                    editTextNoteDescription.text.toString(),
                    checkBoxIdea.isChecked,
                    checkBoxTodo.isChecked,
                    checkBoxImportant.isChecked
            ))
            dismiss()
        }
        
        editTextNoteTitle.setText(note.title)
        editTextNoteDescription.setText(note.description)
        
        checkBoxImportant.isChecked = note.important
        checkBoxIdea.isChecked = note.idea
        checkBoxTodo.isChecked = note.todo
        
        return AlertDialog.Builder(activity!!)
                .setMessage("Edit note")
                .setView(view)
                .create()
        
    }
}