package com.shardbytes.plasmoxy.juncc.loginlifecycle.login

import android.os.AsyncTask
import com.github.kittinunf.fuel.httpPost
import com.shardbytes.plasmoxy.juncc.loginlifecycle.model.CredentialsData

class CheckLoginTask(private val credentialsData: CredentialsData, private val callback: (String) -> Unit) : AsyncTask<Void, Void, Void>() {
    
    private val LOGIN_HOST = "https://shardbytes.com/logintest/login"

    override fun doInBackground(vararg params: Void?): Void? {

        val (resultData, _) = LOGIN_HOST.httpPost().authenticate(credentialsData.name, credentialsData.passwordHash).responseString().third
        callback(resultData ?: "")
        
        return null
    }
}