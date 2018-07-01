package begin.plasmoxy.github.io.lifemenu

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_secondary.*
import org.jetbrains.anko.textColor

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        if (intent.getBooleanExtra("bigText", false)) {
            valueView.textSize = 100f
        }

        if (intent.getBooleanExtra("redText", false)) {
            valueView.textColor = Color.RED
        }

        val newText = "value = ${intent.getIntExtra("value", 0)}"
        valueView.text = newText

    }
}
