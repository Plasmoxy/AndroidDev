package io.github.plasmoxy.begin.widdgett

import android.os.AsyncTask
import android.util.Log
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.nio.charset.StandardCharsets
import java.security.MessageDigest


class LoginTask(val callback: (String) -> Unit) : AsyncTask<String, Void, String>() {

    fun ByteArray.toHex() = this.joinToString(separator = "") { it.toInt().and(0xff).toString(16).padStart(2, '0') }
    
    override fun doInBackground(vararg params: String): String {
        
        val name = params[0]
        val password = params[1]
        
        Log.i("LoginTask", "name=$name password=$password")

        val client = OkHttpClient()
        val saltRequest = Request.Builder()
                .url("https://shardbytes.com/user/$name/salt")
                .build()
        val saltResponse = client.newCall(saltRequest).execute()

        val salt: String = saltResponse.body()?.string() ?: ""

        if (salt == "") {
            throw Exception("no salt !")
        }

        val toHash = "$password$salt"

        val digestor = MessageDigest.getInstance("SHA-256")
        val bytehash = digestor.digest(toHash.toByteArray(StandardCharsets.UTF_8))
        val stringhash = bytehash.toHex().toLowerCase()


        val loginRequest = Request.Builder()
                .url("https://shardbytes.com/user/$name/login")
                .method("POST", RequestBody.create(MediaType.get("text/plain"), stringhash))
                .build()

        val loginResponse = client.newCall(loginRequest).execute()

        val loginResult = loginResponse.body()?.string() ?: ""

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