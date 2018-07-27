package io.github.plasmoxy.begin.widdgett

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.okButton

class MainActivity : AppCompatActivity() {
    
    private val scrollToCardListener = View.OnFocusChangeListener {v, hasFocus ->
        if (hasFocus) {
            coreScroll.post {
                coreScroll.smoothScrollTo(0, cardViewLogin.bottom)
            }
        }
    }
    
    private fun login() {
        if (editTextName.text.isEmpty() || editTextPassword.text.isEmpty()) {
            alert("Please enter your name and password !"){ okButton {} }.show()
            return
        }

        val loginTask = LoginTask {
            //toast(it).show()
            when (it) {
                
                "OK_LOGIN" -> {
                    startActivity(intentFor<ButtonActivity>("passhash" to "XD"))
                    finish()
                }
                
                "@ERROR:WRONG_HASH" -> {
                    alert("Wrong password !") { okButton {} }.show()
                }
                
                "@ERROR:NO_USER" -> {
                    alert("This user does not exist!") { okButton {} }.show()
                }
                
            }
        }

        loginTask.execute(editTextName.text.toString(), editTextPassword.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        editTextName.setOnFocusChangeListener(scrollToCardListener)
        editTextPassword.setOnFocusChangeListener(scrollToCardListener)
        
        buttonLogIn.setOnClickListener { login() }
        editTextPassword.setOnEditorActionListener { v, actionId, event -> 
            login()
            false // block rest of the event ?
        }
        
        /*
        buttonLogIn.setOnClickListener {
            startActivity(Intent(this, ButtonActivity::class.java))
        }
        */
    }
    
}
