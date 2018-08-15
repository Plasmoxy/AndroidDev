package com.shardbytes.plasmoxy.juncc.loginlifecycle

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shardbytes.plasmoxy.juncc.loginlifecycle.login.CheckLoginTask
import com.shardbytes.plasmoxy.juncc.loginlifecycle.login.LoginAssemblerTask
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        buttonLogIn.setOnClickListener { login() }
        
        buttonDelet.setOnClickListener {
            getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
                    .clear()
                    .apply()
        }
        
        buttonShowPref.setOnClickListener { 
            val name = getSharedPreferences("settings", Context.MODE_PRIVATE).getString("name", "none")
            val hash = getSharedPreferences("settings", Context.MODE_PRIVATE).getString("passwordHash", "none")
            alert("$name : $hash") { okButton {} }.show()
        }
        
    }

    private fun login() {
        
        if (editTextName.text.isEmpty() || editTextPassword.text.isEmpty()) {
            alert("Please enter your name and password !") { okButton {} }.show()
            return
        }
        
        val credentials = Pair(editTextName.text.toString(), editTextPassword.text.toString())
        
        LoginAssemblerTask(credentials) { data -> runOnUiThread {
            if (data == null) {
                toast("Error with hashing password !")
                return@runOnUiThread
            }

            CheckLoginTask(data) { runOnUiThread { when (it) {
                
                "OK_LOGIN" -> {
                    getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
                            .putString("name", data.name)
                            .putString("passwordHash", data.passwordHash)
                            .apply()
                    alert("Ok login. Saved !") { okButton {} }.show()
                }

                "@ERROR:WRONG_HASH" -> {
                    alert("Wrong password !") { okButton {} }.show()
                }

                "@ERROR:NO_USER" -> {
                    alert("This user does not exist!") { okButton {} }.show()
                }

            }}}.execute()
        }}.execute()
    }
    
}
