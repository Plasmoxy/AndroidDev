package begin.plasmoxy.github.io.lifemenu

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread


class EchoWebSocketListener(
        private val output: (String) -> Any?,
        private val update: (String) -> Any?
) : WebSocketListener() {

    var active = AtomicBoolean(true)

    override fun onOpen(webSocket: WebSocket, response: Response) {
        thread {
            while (active.get()) {
                webSocket.send("getTime")
                Thread.sleep(1000)
            }
        }
        //webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        update(text ?:  "")
        output("Received msg : $text")
    }


    override fun onClosing(webSocket: WebSocket, code: Int, reason: String?) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null)
        output("Closing : $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
        output("Error : ${t?.message}")
    }

    companion object {
        private val NORMAL_CLOSURE_STATUS = 1000
    }
}