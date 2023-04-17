package com.metaplikasyon.potkal.fragments.fragment_wordset.views.txt_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.other.PixelConverter;

public class SuperTvRght extends androidx.appcompat.widget.AppCompatTextView {

   private final PixelConverter pixelConverter;

    private final int MTCH_PRNT= LinearLayout.LayoutParams.MATCH_PARENT;
    private final int WRP_CNTNT= LinearLayout.LayoutParams.WRAP_CONTENT;

    private final int COL_TXT= Color.BLACK;
    private final int SIZE_TXT= 10;

    private final String txt;

    public SuperTvRght(@NonNull Context context, String txt) {
        super(context);
        setText(txt);
        this.txt=txt;
        pixelConverter=new PixelConverter(context);
        onCreate();
    }

    private void onCreate() {
        setView();
    }

    private void setView() {
        setLayoutParams(new LinearLayout.LayoutParams(MTCH_PRNT,WRP_CNTNT));
        setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_WORDSET_TXT));
        setTextSize(TypedValue.COMPLEX_UNIT_SP,SIZE_TXT);
        setTypeface(getTypeface(), Typeface.BOLD);
        setTextAlignment(TEXT_ALIGNMENT_VIEW_END);
    }

    public String getText() {
        return txt;
    }
}
