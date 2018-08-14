package com.shardbytes.plasmoxy.juncc.loginlifecycle

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.shardbytes.plasmoxy.juncc.loginlifecycle.login.CheckLoginTask
import com.shardbytes.plasmoxy.juncc.loginlifecycle.model.CredentialsData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

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
            val pref = getPreferences(Context.MODE_PRIVATE)
            
            val credentials = CredentialsData(
                    pref.getString("name", ""),
                    pref.getString("passwordHash", "")
            )
            
            if (credentials.name == "") {
                Log.i("core", "empty name in pref")
                uiThread { launchLogin() }
                return@doAsync
            }
            
            // here we should have credentials available, we try login with asynctask
            CheckLoginTask(credentials) { result -> when (result) {
                
                "OK_LOGIN" -> toast("ok")
                else -> launchLogin()
                
            }}.execute()
            
            
        }
    }
    
    
    // if something goes wrong, just ask for login again
    private fun launchLogin() {
        Log.i("core", "launch login activity")
        toast("launch login")
        startActivity(intentFor<LoginActivity>("name" to ""))
        finish()
    }
    
}
