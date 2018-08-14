package com.shardbytes.plasmoxy.juncc.loginlifecycle.login

import android.os.AsyncTask
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.shardbytes.plasmoxy.juncc.loginlifecycle.model.Credentials

class CheckLoginTask(private val callback: (String?) -> Unit) : AsyncTask<Credentials, Void, String>() {
    
    private val LOGIN_HOST = "https://shardbytes.com/logintest/login"

    override fun doInBackground(vararg params: Credentials?): String {
        if (params.size < 1) throw Exception("bad task param")
        val credentials = params[0]!!
        
        val (resultData, error) = LOGIN_HOST.httpPost().authenticate(credentials.name, credentials.passwordHash).responseString().third
        
        return resultData ?: ""
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        callback(result)
    }
    
}