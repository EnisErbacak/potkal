package com.metaplikasyon.potkal.fragments.fragment_settings.element.containers;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintSet;

import com.metaplikasyon.potkal.fragments.fragment_settings.element.textview.TvSubTitle;
import com.metaplikasyon.potkal.other.PixelConverter;

public class LlSubContainer extends LinearLayout {
    private final String txt;
    private ConstraintSet constraintSet;
    private final View[] arrClSpinnerContainer;

    public LlSubContainer(Context context, String txt, View[] arrClSpinnerContainer) {
        super(context);
        this.txt=txt;
        this.arrClSpinnerContainer=arrClSpinnerContainer;
        onCreate();
    }

    public String getTxt() {
        return txt;
    }

    private void onCreate() {
        addChildren();
        setStyle();
    }

    private void addChildren() {
        if(txt!=null) addView(new TvSubTitle(getContext(), txt));
        for(View v:arrClSpinnerContainer) {
            addView(v);
        }
    }

    private void setStyle() {
        PixelConverter pc=new PixelConverter(getContext());
        setOrientation(VERTICAL);
        LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(pc.dp2Px(7),pc.dp2Px(5),0,pc.dp2Px(5));
        setLayoutParams(lp);
    }
}
