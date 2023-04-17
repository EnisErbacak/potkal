package com.metaplikasyon.potkal.fragments.fragment_settings.element.containers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintSet;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.textview.TvGroupTitle;
import com.metaplikasyon.potkal.other.PixelConverter;

public class LlMainContainer extends LinearLayout {
    private final TvGroupTitle tvSub;

    private final String txt;
    private ConstraintSet constraintSet;
    private final View[] arrLlSubContainers;

    public LlMainContainer(Context context, String txt, View[] arrLlSubContainers) {
        super(context);
        this.txt=txt;
        this.tvSub = new TvGroupTitle(context, txt);
        this.arrLlSubContainers=arrLlSubContainers;
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
        if(txt!=null) addView(tvSub);
        for(View v:arrLlSubContainers) {
            addView(v);
        }
    }

    private void setStyle() {
        PixelConverter pc=new PixelConverter(getContext());
        setOrientation(VERTICAL);
        LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(pc.dp2Px(10),pc.dp2Px(10),pc.dp2Px(10),pc.dp2Px(10));
        setPadding(pc.dp2Px(5),pc.dp2Px(5),pc.dp2Px(5),pc.dp2Px(5));
        setLayoutParams(lp);
        setBackground(getGradientDrawable(new SPEditor().getInt(getContext(), SPEditor.COL_SETTINGS_PANEL)
                , new PixelConverter(getContext()).dp2Px(10)));
    }

    private Drawable getGradientDrawable(int color, int radius) {
        GradientDrawable gradientDrawable=new GradientDrawable();

        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }
}

