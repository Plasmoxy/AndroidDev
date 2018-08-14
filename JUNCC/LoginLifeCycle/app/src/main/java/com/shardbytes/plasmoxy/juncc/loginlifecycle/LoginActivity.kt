package com.shardbytes.plasmoxy.juncc.loginlifecycle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonException
import com.shardbytes.plasmoxy.juncc.loginlifecycle.model.Credentials
import org.jetbrains.anko.toast
import java.io.File

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    
    override fun onResume() {
        super.onResume()
        val credentialsFile = File(filesDir, "credentials.json")
        val credentialsString = credentialsFile.run { if (exists()) readText() else ""}

        if (credentialsString == "") {
            launchLogin()
            return
        }
        
        val credentials: Credentials? = try {
            Klaxon().parse<Credentials>(credentialsString)
        } catch(ex: KlaxonException) { null }
        
        if (credentials == null) { // autocast
            launchLogin()
            return
        }
        
        // here we should have credentials available, we try login with asynctask
        
        
    }
    
    
    private fun launchLogin() {
        toast("launch login")
    }
    
}
