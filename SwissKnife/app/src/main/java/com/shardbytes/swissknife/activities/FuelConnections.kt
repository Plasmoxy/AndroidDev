package com.shardbytes.swissknife.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kittinunf.fuel.httpGet
import com.shardbytes.swissknife.R
import kotlinx.android.synthetic.main.activity_fuel_connections.*
import org.jetbrains.anko.getStackTraceString

class FuelConnections : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_connections)
        
        buttonFuelGet.setOnClickListener { view -> 
            
            "https://shardbytes.com:10101/api/shop/getrecord/root".httpGet().responseString { _, _, result ->
                result.fold({
                    textViewInfo.setText(it)
                },{
                    textViewInfo.setText(it.getStackTraceString())
                })
            }
            
        }
        
    }
}
