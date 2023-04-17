package com.metaplikasyon.potkal.fragments.fragment_worddef.views.btn;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import com.metaplikasyon.potkal.R;

public class SwitchWordDefAppearance extends SwitchCompat {

    public SwitchWordDefAppearance(@NonNull Context context) {
        super(context);
        onCreate();
    }

    public SwitchWordDefAppearance(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    public SwitchWordDefAppearance(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate();
    }


    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        setTrackDrawable(ContextCompat.getDrawable(getContext(), R.drawable.switch_track_small));
        setThumbDrawable(ContextCompat.getDrawable(getContext(),R.drawable.switch_thumb_small));

        //setShowText(true);
        setTextOn(getContext().getString(R.string.detailed));
        setTextOff(getContext().getString(R.string.simple));
        setTextColor(ContextCompat.getColor(getContext(),R.color.dark_blue));
        setTypeface(null, Typeface.BOLD);
    }
}
