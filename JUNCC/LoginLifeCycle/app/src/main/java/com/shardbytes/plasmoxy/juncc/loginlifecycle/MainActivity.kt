package com.shardbytes.plasmoxy.juncc.loginlifecycle

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shardbytes.plasmoxy.juncc.loginlifecycle.model.CredentialsData
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    
    lateinit var credentialsData: CredentialsData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        credentialsData = intent.extras["credentialsData"] as CredentialsData
        textViewReport.text = credentialsData.toString()
        
        buttonDeleteSettings.setOnClickListener { 
            getSharedPreferences("settings", Context.MODE_PRIVATE).edit().clear().apply()
            toast("deleted settings")
        }
    }


    override fun onResume() {
        super.onResume()
        
    }
}
