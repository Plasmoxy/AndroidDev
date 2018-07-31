package io.github.plasmoxy.begin.widgetmania2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            textViewRadio.text = when(checkedId) {
                R.id.radioButton1 -> "1"
                R.id.radioButton2 -> "2"
                R.id.radioButton3 -> "3"
                else -> "ERROR"
            }
        }
    }
}
