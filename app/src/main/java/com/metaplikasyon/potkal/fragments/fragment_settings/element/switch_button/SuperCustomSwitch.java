package com.metaplikasyon.potkal.fragments.fragment_settings.element.switch_button;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.widget.Switch;

import androidx.core.content.ContextCompat;

import com.metaplikasyon.potkal.R;

public class SuperCustomSwitch extends Switch {
    private Drawable track,thumb;
    private String onStr,offStr;
    public SuperCustomSwitch(Context context, String onStr, String offStr) {
        super(context);
        this.onStr=onStr;
        this.offStr=offStr;
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        setTrackDrawable(ContextCompat.getDrawable(getContext(),R.drawable.track_big));
        setThumbDrawable(ContextCompat.getDrawable(getContext(),R.drawable.switch_thumb_big));

        setShowText(true);
        setTextOn(onStr);
        setTextOff(offStr);
        setTextColor(ContextCompat.getColor(getContext(),R.color.dark_blue));
        setTypeface(null, Typeface.BOLD);
    }
}
