package com.shardbytes.swissknife

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.shardbytes.swissknife.activities.FuelConnections
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        buttonFuelConnections.setOnClickListener { startActivity<FuelConnections>() }
    }
}
