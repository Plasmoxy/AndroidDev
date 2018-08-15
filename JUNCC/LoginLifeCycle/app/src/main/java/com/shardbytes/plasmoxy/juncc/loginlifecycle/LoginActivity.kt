package com.shardbytes.plasmoxy.juncc.loginlifecycle

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shardbytes.plasmoxy.juncc.loginlifecycle.login.CheckLoginTask
import com.shardbytes.plasmoxy.juncc.loginlifecycle.login.LoginAssemblerTask
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast
import java.io.Serializable

class LoginActivity : AppCompatActivity() {

    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        settings = getSharedPreferences("settings", Context.MODE_PRIVATE)
        
        buttonLogIn.setOnClickListener { login() }
        
        buttonDelet.setOnClickListener {
            settings.edit()
                    .clear()
                    .apply()
        }
        
        buttonShowPref.setOnClickListener { 
            val name = settings.getString("name", "none")
            val hash = settings.getString("passwordHash", "none")
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
                alert("error logging in, are you connected to internet ?") { okButton {} }.show()
                return@runOnUiThread
            }

            CheckLoginTask(data) { result -> runOnUiThread { when (result) {
                
                "OK_LOGIN" -> {
                    settings.edit()
                            .putString("name", data.name)
                            .putString("passwordHash", data.passwordHash)
                            .apply()
                    toast("login saved, welcome")
                    startActivity(intentFor<MainActivity>("credentialsData" to data as Serializable))
                    finish()
                }

                "@ERROR:WRONG_HASH" -> {
                    alert("Wrong password !") { okButton {} }.show()
                }

                "@ERROR:NO_USER" -> {
                    alert("This user does not exist!") { okButton {} }.show()
                }
                
                else -> alert("Error : $result") { okButton {} }.show()

            }}}.execute()
        }}.execute()
    }
    
}
