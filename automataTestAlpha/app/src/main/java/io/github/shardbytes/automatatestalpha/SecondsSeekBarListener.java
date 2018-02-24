package io.github.shardbytes.automatatestalpha;

import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Plasmoxy on 13.02.2018.
 */

public class SecondsSeekBarListener implements SeekBar.OnSeekBarChangeListener {

    private SeekBar bar;
    private TextView secondsText;
    private Seconds secs;

    private int progressBefore;

    public SecondsSeekBarListener(SeekBar bar, TextView text, Seconds secs) {
        this.bar = bar;
        this.secondsText = text;
        this.secs = secs;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (progress != progressBefore) {
            secs.set(progress);
            secondsText.setText(String.valueOf(progress) + " s");
            progressBefore = progress;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
