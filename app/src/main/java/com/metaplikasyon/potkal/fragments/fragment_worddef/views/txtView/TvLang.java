package com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvLang extends TvWordDefSuper {
    public TvLang(Context context,String lang) {
        super(context);
        setText(lang);
        onCreate();
    }

    void onCreate() {
        setStyle();
    }

    void setStyle() {
        SPEditor sp=new SPEditor();
        setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDDEF_LANG_TXT));
        setTextSize(sp.getInt(getContext(), SPEditor.TXT_SIZE_LANG));
        setTypeface(getTypeface(), Typeface.ITALIC);
    }
}