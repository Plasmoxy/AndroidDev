package io.github.plasmoxy.begin.widdgett

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    private val scrollToCardListener = View.OnFocusChangeListener {v, hasFocus ->
        if (hasFocus) {
            coreScroll.post {
                coreScroll.smoothScrollTo(0, cardViewLogin.bottom)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        editTextName.setOnFocusChangeListener(scrollToCardListener)
        editTextPassword.setOnFocusChangeListener(scrollToCardListener)
        
        buttonLogIn.setOnClickListener {
            startActivity(Intent(this, ButtonActivity::class.java))
        }
    }
    
}
