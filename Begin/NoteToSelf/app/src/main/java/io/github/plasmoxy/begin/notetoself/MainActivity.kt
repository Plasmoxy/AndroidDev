package io.github.plasmoxy.begin.notetoself

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTestDialog.setOnClickListener {
            DialogNewNote().show(fragmentManager, "newnote")
        }
    }

    fun createNewNote(note: Note) {
        alert(note.toString()) { okButton {} }.show()
    }

}
    
