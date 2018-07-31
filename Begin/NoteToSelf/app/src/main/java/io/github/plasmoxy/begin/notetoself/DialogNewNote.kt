package io.github.plasmoxy.begin.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.View

class DialogNewNote : DialogFragment() {
    
    private val coreView : View by lazy {
        View.inflate(activity, R.layout.dialog_new_note, null)
    }
    override fun getView() = coreView // for kotlin synthetic

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
                .setView(coreView)
                .setMessage("Add new note")
                .create()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        
    }
    
}