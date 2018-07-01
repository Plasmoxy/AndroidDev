package begin.plasmoxy.github.io.lifemenu

import android.widget.SeekBar
import android.widget.TextView

class MainSeekBarListener(private val t : TextView) : SeekBar.OnSeekBarChangeListener {

    override fun onStartTrackingTouch(seekBar: SeekBar) {}

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        val newText = "value=${progress}"
        t.text = newText
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {}

}