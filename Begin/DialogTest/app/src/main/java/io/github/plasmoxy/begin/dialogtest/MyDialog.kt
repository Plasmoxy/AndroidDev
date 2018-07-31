package io.github.plasmoxy.begin.dialogtest

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle

class MyDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        
        return AlertDialog.Builder(activity)
                .setMessage("Make a selection")
                .setPositiveButton("OK") { dialog, id ->
                    
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    
                }
                .create()
    }
}