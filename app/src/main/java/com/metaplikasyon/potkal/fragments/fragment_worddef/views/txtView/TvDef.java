package com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvDef extends TvWordDefSuper {
    public TvDef(Context context, String txt) {
        super(context);
        setText(txt);
        onCreate();
    }

    public void onCreate() {
        setStyle();
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(sp.getInt(getContext(), SPEditor.TXT_SIZE_DEF));
        setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_WORDDEF_DEF_TXT));
    }
}
