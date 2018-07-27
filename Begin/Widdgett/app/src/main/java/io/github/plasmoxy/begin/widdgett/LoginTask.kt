package io.github.plasmoxy.begin.widdgett

import android.os.AsyncTask
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import java.nio.charset.StandardCharsets
import java.security.MessageDigest


class LoginTask(val callback: (String) -> Unit) : AsyncTask<String, Void, String>() {

    fun ByteArray.toHex() = this.joinToString(separator = "") { it.toInt().and(0xff).toString(16).padStart(2, '0') }
    
    override fun doInBackground(vararg params: String): String {
        
        val name = params[0]
        val password = params[1]

        // create name-only request to get salt
        val (saltData, saltError) = "https://shardbytes.com/logintest/salt".httpPost().authenticate(name, "").responseString().third
        val salt = saltData ?: ""
        
        if (salt == "") {
            throw Exception("no salt !")
        }

        val toHash = "$password$salt"

        val digestor = MessageDigest.getInstance("SHA-256")
        val bytehash = digestor.digest(toHash.toByteArray(StandardCharsets.UTF_8))
        val stringhash = bytehash.toHex().toLowerCase()

        // create authenticated request with name and hashed password to access API
        val (loginResultData, loginError) = "https://shardbytes.com/logintest/login".httpPost().authenticate(name, stringhash).responseString().third
        val loginResult = loginResultData ?: ""

        if (loginResult == "") {
            throw Exception("no login result !")
        }
        
        return loginResult
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        callback(result)
    }
}