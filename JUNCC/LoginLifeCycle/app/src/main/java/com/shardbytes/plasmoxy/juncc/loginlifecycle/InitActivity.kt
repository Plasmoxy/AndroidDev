package com.shardbytes.plasmoxy.juncc.loginlifecycle

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.shardbytes.plasmoxy.juncc.loginlifecycle.login.CheckLoginTask
import com.shardbytes.plasmoxy.juncc.loginlifecycle.model.CredentialsData
import org.jetbrains.anko.*
import java.io.Serializable

class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
    }
    
    override fun onResume() {
        super.onResume()

        // allow loading to render, do async ->
        doAsync {
            
            // json ? too slow for ez usage, serialization ? rather not, default android ? yes
            val pref = getSharedPreferences("settings", Context.MODE_PRIVATE)
            
            val credentialsData = CredentialsData(
                    pref.getString("name", ""),
                    pref.getString("passwordHash", "")
            )
            
            if (credentialsData.name == "") {
                Log.i("core", "no name in preferences, launching login")
                uiThread { launchLogin() }
                return@doAsync
            }
            
            // here we should have credentials available, we try login with asynctask
            CheckLoginTask(credentialsData) { result -> uiThread { when (result) {
                
                "OK_LOGIN" -> launchMain(credentialsData)
                else -> launchLogin()
                
            }}}.execute()
            
            
        }
    }
    
    
    // if something goes wrong, just ask for login again
    private fun launchLogin() {
        Log.i("core", "launch login activity")
        startActivity(intentFor<LoginActivity>("name" to ""))
        finish()
    }
    
    private fun launchMain(credentialsData: CredentialsData) {
        Log.i("core", "launch main activity")
        startActivity(intentFor<MainActivity>("credentialsData" to credentialsData as Serializable))
        finish()
    }
    
}
