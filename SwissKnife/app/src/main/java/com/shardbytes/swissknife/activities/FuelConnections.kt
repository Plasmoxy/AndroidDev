package com.shardbytes.swissknife.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.kittinunf.fuel.httpGet
import com.shardbytes.swissknife.R
import kotlinx.android.synthetic.main.activity_fuel_connections.*
import okhttp3.*
import org.jetbrains.anko.getStackTraceString
import org.jetbrains.anko.toast
import java.io.IOException

class FuelConnections : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_connections)
        
        val host = "https://shardbytes.com:10101"
        
        val client = OkHttpClient.Builder()
                .build()
        
        buttonFuelGet.setOnClickListener { view -> 
            
            "$host/api/shop/getrecord/root".httpGet().responseString { _, _, result ->
                result.fold({
                    textViewInfo.setText(it)
                },{
                    textViewInfo.setText(it.getStackTraceString())
                })
            }
            
        }
        
        buttonOkHttpGet.setOnClickListener {
            
            val request = Request.Builder()
                    .url("$host/api/shop/getrecord/root")
                    .get()
                    .build()
            
            client.newCall(request).enqueue(object : Callback {
                
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        toast("response")
                        textViewInfo.setText(response.body()?.string() ?: "NUL")
                    }
                }
            })
            
        }
        
    }
}
