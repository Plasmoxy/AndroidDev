package com.shardbytes.plasmoxy.juncc.lifecyclerecover

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        buttonSave.setOnClickListener { 
            File(filesDir, "variable").writeText(editText.text.toString())
        }
        
        buttonLoad.setOnClickListener { 
            editText.setText( File(filesDir, "variable").run{ if(exists()) readText() else ""} )
        }
        
    }
}
