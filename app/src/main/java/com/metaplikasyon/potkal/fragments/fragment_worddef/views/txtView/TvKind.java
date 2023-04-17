package com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvKind extends TvWordDefSuper {
    String kind;
    public TvKind(Context context, String kind) {
        super(context);
        this.kind=kind;
        onCreate();
    }

    void onCreate() {
        setText(kind);
        setStyle();
    }

    void setStyle() {
        SPEditor sp=new SPEditor();
        setTypeface(getTypeface(), Typeface.ITALIC);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDDEF_KIND_TXT));
        setTextSize(sp.getInt(getContext(), SPEditor.TXT_SIZE_KIND));
    }
}