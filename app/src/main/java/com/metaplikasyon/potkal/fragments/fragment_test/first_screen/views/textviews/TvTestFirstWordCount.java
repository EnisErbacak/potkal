package com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.textviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvTestFirstWordCount extends androidx.appcompat.widget.AppCompatTextView {

    public TvTestFirstWordCount(@NonNull Context context, int wordCnt) {
        super(context);
        setText(String.valueOf(wordCnt));
        setStyle();
    }

    private void setStyle() {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_GENERAL_TXT));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        setTypeface(getTypeface(), Typeface.BOLD);
        setTextAlignment(TEXT_ALIGNMENT_VIEW_END);
    }
}
