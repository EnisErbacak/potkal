package com.metaplikasyon.potkal.fragments.fragment_test.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvTestCommon extends androidx.appcompat.widget.AppCompatTextView {


    public TvTestCommon(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setStyle();
    }

    private void setStyle() {
        setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_GENERAL_TXT));
    }
}
