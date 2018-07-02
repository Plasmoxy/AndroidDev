package begin.plasmoxy.github.io.lifemenu

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_secondary.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import java.util.concurrent.Executors

class SecondaryActivity : AppCompatActivity() {

    val okHttpClient : OkHttpClient by lazy { OkHttpClient() }
    val mainThreadExecutor = Executors.newScheduledThreadPool(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.getBooleanExtra("bigText", false)) {
            valueView.textSize = 100f
        }

        if (intent.getBooleanExtra("redText", false)) {
            valueView.textColor = Color.RED
        }

        valueView.text = "value = ${intent.getIntExtra("value", 0)}"

        val request = Request.Builder().url("wss://maggit.herokuapp.com/timews").build()
        val listener = EchoWebSocketListener(::log, ::updateTime, mainThreadExecutor)
        okHttpClient.newWebSocket(request, listener)

        startButton.setOnClickListener({ listener.startUpdate() })
        stopButton.setOnClickListener({ listener.stopUpdate() })
        
    }

    override fun onDestroy() {
        toast("destroying -> stopping executor")
        mainThreadExecutor.shutdown()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


    fun log(s: String) {
        runOnUiThread {
            logTextView.text = "${logTextView.text}\n$s"
        }
    }

    fun updateTime(s: String) {
        runOnUiThread {
            timeTextView.text = s
        }
    }

}
