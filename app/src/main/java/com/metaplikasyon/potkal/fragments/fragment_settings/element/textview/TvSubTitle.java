package com.metaplikasyon.potkal.fragments.fragment_settings.element.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvSubTitle extends androidx.appcompat.widget.AppCompatTextView {
    public TvSubTitle(Context context, String txt) {
        super(context);
        setText(txt);
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_SETTINGS_TXT));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    }
}
