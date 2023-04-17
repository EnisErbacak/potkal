package com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvExmp extends TvWordDefSuper{
    public TvExmp(Context context,String exmp) {
        super(context);
        setText(exmp);
        onCreate();
    }

    public void onCreate() {
        setStyle();
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setTypeface(getTypeface(), Typeface.ITALIC);
        setTextSize(sp.getInt(getContext(), SPEditor.TXT_SIZE_EXAMPLE));
        setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDDEF_EXMP_TXT));
    }
}
