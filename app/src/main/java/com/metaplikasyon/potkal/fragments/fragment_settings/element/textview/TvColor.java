package com.metaplikasyon.potkal.fragments.fragment_settings.element.textview;

import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvColor extends androidx.appcompat.widget.AppCompatTextView {

    public TvColor(Context context, String txt) {
        super(context);
        setText(txt);
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        SPEditor spEditor=new SPEditor();
        setLayoutParams(new LinearLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ViewGroup.LayoutParams.MATCH_PARENT));
        setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_SETTINGS_TXT));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    }
}