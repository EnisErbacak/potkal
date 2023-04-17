package com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.other.PixelConverter;

public class TvPts extends TvWordDefSuper{

    public TvPts(@NonNull Context context, int pts) {
        super(context);
        setText(String.valueOf(pts));
    }

    void onCreate() {
        setStyle();
    }

    void setStyle() {
        SPEditor sp=new SPEditor();
        PixelConverter pc=new PixelConverter(getContext());
        ConstraintLayout.LayoutParams lp=new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        lp.setMargins(pc.dp2Px(5),0,0,0);
        setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDDEF_LANG_TXT));
        setTextSize(sp.getInt(getContext(), SPEditor.TXT_SIZE_LANG));
        setTypeface(getTypeface(), Typeface.ITALIC);
    }
}