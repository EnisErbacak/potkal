package com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.listner;

import android.widget.SeekBar;
import android.widget.TextView;

import com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.views.btn.ColorSampler;


public class ListenerSeekbar implements SeekBar.OnSeekBarChangeListener {

    final int MIN=0;
    final int MAX=255;
    final int STEP=1;

    private final TextView tv;
    private final ColorSampler cs;
    private final String type;

    public ListenerSeekbar(TextView tv, ColorSampler cs, String type) {
        this.tv = tv;
        this.cs=cs;
        this.type=type;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        double value = Math.round((progress * (MAX - MIN)) / 100.0);
        int displayValue = ( ((int) value + MIN) / STEP) * STEP;
        tv.setText(String.valueOf(displayValue));

        setValue(cs, type, displayValue);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setValue(ColorSampler cs,String type, int val) {
        switch (type.toLowerCase()) {

            case "red":
                cs.setRed(val);
                cs.update();
                break;
            case "green":
                cs.setGreen(val);
                cs.update();
                break;
            case "blue":
                cs.setBlue(val);
                cs.update();
                break;
        }
    }
}
