package begin.plasmoxy.github.io.lifemenu

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class EchoWebSocketListener(
        private val output: (String) -> Any?,
        private val update: (String) -> Any?,
        private val executor: ScheduledExecutorService
) : WebSocketListener() {
    
    private lateinit var ws : WebSocket
    private var task : ScheduledFuture<*>? = null
    
    fun log(s: String) {
        Log.i("WS Task", s)
        output("[log] $s")
    }

    fun startUpdate() {
        log("trying to start updating")
        
        if ( task?.isDone ?: true ) { // if task done or null
            log("task is startable, starting ...")
            
            task = executor.scheduleAtFixedRate({
                
                log("running...")
                try {
                    ws.send("getTime")
                }
                catch(t: Throwable) {
                    log("Exception in executor : ${t.message}")
                }
                
            }, 0, 1, TimeUnit.SECONDS)
            
            log("task started")
        }
    }

    fun stopUpdate() {
        log("trying to cancel task")
        task?.apply {
            cancel(false) // if task is null, don't
            log("task canceled")
        }
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        ws = webSocket
        log("websocket opened !")
        update("connected")
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        update(text ?:  "")
        output("Received msg : $text")
    }


    override fun onClosing(webSocket: WebSocket, code: Int, reason: String?) {
        stopUpdate()
        webSocket.close(NORMAL_CLOSURE_STATUS, null)
        log("Closing : $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
        log("Error : ${t?.message} | response = $response")
    }

    companion object {
        private val NORMAL_CLOSURE_STATUS = 1000
    }
}