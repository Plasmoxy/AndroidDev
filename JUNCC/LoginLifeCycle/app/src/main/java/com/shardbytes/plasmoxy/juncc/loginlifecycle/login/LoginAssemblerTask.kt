package com.shardbytes.plasmoxy.juncc.loginlifecycle.login

import android.os.AsyncTask
import com.github.kittinunf.fuel.httpPost
import com.shardbytes.plasmoxy.juncc.loginlifecycle.model.CredentialsData
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

// takes name and password Pair, puts salt in it, returns credentialdata

class LoginAssemblerTask(private val credentials: Pair<String, String>, private val callback: (CredentialsData?) -> Unit) : AsyncTask<Void, Void, Void>() {
    
    private val SALT_HOST = "https://shardbytes.com/logintest/salt"

    fun ByteArray.toHex() = this.joinToString(separator = "") { it.toInt().and(0xff).toString(16).padStart(2, '0') }
    
    override fun doInBackground(vararg params: Void?): Void? {
        
        // create name-only request to get salt
        val (saltData, _) = SALT_HOST.httpPost()
                .authenticate(credentials.first, "")
                .responseString().third
        
        val salt = saltData ?: ""

        if (salt == "") {
            callback(null)
            return null
        }

        val toHash = "${credentials.second}$salt"

        val digestor = MessageDigest.getInstance("SHA-256")
        val bytehash = digestor.digest(toHash.toByteArray(StandardCharsets.UTF_8))
        val stringhash = bytehash.toHex().toLowerCase()
        
        callback(CredentialsData(credentials.first, stringhash))
        
        return null
    }
    
}