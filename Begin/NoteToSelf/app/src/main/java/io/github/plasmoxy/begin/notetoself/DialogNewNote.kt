package io.github.plasmoxy.begin.notetoself

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.dialog_new_note.*

// note: USE v7 support AlertDialog or stuff will crash on kitkat ! always check if you're using support libraries

class DialogNewNote : DialogFragment() {
    
    private val coreView : View by lazy {
        View.inflate(activity, R.layout.dialog_new_note, null)
    }
    override fun getView() = coreView // reroute so kotlin synthetic works

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        
        buttonCancel.setOnClickListener { dismiss() }
        buttonOk.setOnClickListener {

            (activity as MainActivity).createNewNote(Note(
                    editTextNoteTitle.text.toString(),
                    editTextNoteDescription.text.toString(),
                    checkBoxIdea.isChecked,
                    checkBoxTodo.isChecked,
                    checkBoxImportant.isChecked
            ))

            dismiss()
            
        }
        
        return AlertDialog.Builder(activity!!)
                .setMessage("Add new notee")
                .setView(view)
                .create()
        
    }
}