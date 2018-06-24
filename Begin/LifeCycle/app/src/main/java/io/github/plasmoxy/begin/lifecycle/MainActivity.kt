package io.github.plasmoxy.begin.lifecycle

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import kotlin.properties.Delegates.observable

class MainActivity : AppCompatActivity() {
    
    // observables
    
    var info : String by observable("XD") { _, _, newVal ->
        infoTextView.text = newVal
    }
    
    // listeners
    
    var randomVal = View.OnClickListener {
        info = (Math.random() * 10).toInt().toString()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        mainButton.setOnClickListener(randomVal)
        
        toastButton.setOnClickListener { 
            toast("SOME TOAST")
        }
        
        dialogButton.setOnClickListener {
            alert("fokin answer boi", "YOUR MOM GEY?") {
                yesButton { toast("HA GAYAYYYYY") }
                noButton { toast("VERY WELL") }
            }.show()
        }
        
        selectorButton.setOnClickListener {
            val countries = listOf("Russia", "USA", "Japan", "Australia")
            
            selector("Where are you from?", countries) { dialogInterface, i ->
                toast("So you're living in ${countries[i]}, right?")
            }
        }
        
        progressButton.setOnClickListener {
            val dialog = progressDialog(message = "Please wait a bit…", title = "Destroying the entire universe")
            
            dialog.setOnDismissListener { 
                toast("progress complete")
            }

            var h = Handler()
            h.post( object : Runnable {
                override fun run() {
                    Log.d("SEBB", "HANDLER RUNNING -> $dialog.progress")
                    dialog.incrementProgressBy(1)
                    if (dialog.progress < dialog.max) h.postDelayed(this, 100)
                    else dialog.dismiss()
                }
            })
        }
        
        snackButton.setOnClickListener { 
            snackbar(it, "YO A FAGGOT").show()
        }
        
        toast("app started")
    }
}
