package io.github.plasmoxy.begin.notetoself

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.activity_settings)
        prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        
        supportActionBar?.title = "Settings"
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // display back button as activity up
        
        checkBoxFX.post { checkBoxFX.isChecked = prefs.getBoolean("soundFX", false) }
        radioGroupAnimation.clearCheck()
        when ( prefs.getInt("animationSpeed", 0) ) {
            1 -> radioGroupAnimation.check(R.id.radioAnimation1)
            2 -> radioGroupAnimation.check(R.id.radioAnimation2)
            3 -> radioGroupAnimation.check(R.id.radioAnimation3)
        }
    }
    
    override fun onPause() {
        prefs.edit()
                .putBoolean("soundFX", checkBoxFX.isChecked)
                .putInt("animationSpeed", when (radioGroupAnimation.checkedRadioButtonId) {
                    R.id.radioAnimation1 -> 1
                    R.id.radioAnimation2 -> 2
                    R.id.radioAnimation3 -> 3
                    else -> 0
                })
                .apply() // save on exit of activity
        
        super.onPause()
    }

    // up navigation
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    
}
