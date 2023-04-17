package com.metaplikasyon.potkal.fragments.fragment_wordset.views.container;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ContainerInnerRght extends LinearLayout {
    private final View[] subViews;

    public ContainerInnerRght(Context context, View[] subViews) {
        super(context);
        this.subViews=subViews;
        onCreate();
    }

    void onCreate() {
        this.setId(generateViewId());
        addViews(subViews);
        setView();
    }
    void setView(){
        setOrientation(LinearLayout.VERTICAL);
        setLayoutParams(new LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT));

    }

    private void addViews(View[] subViews) {
        for(int i=0;i<subViews.length;i++) {
            addView(subViews[i]);
        }
    }

    public View[] getSubViews() {
        return subViews;
    }
}