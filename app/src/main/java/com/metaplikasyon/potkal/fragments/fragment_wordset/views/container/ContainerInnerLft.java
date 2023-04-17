package com.metaplikasyon.potkal.fragments.fragment_wordset.views.container;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ContainerInnerLft extends LinearLayout {
    private final View[] subViews;

    public ContainerInnerLft(Context context, View[] subViews) {
        super(context);
        this.subViews=subViews;
        onCreate();
    }

    public void onCreate() {
        this.setId(generateViewId());
        setStyle();
        addViews(subViews);
    }

    public void setStyle() {
        setLayoutParams(new LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.HORIZONTAL);
    }

    private void addViews(View[] subViews) {
        for(int i=0;i<subViews.length;i++) {
            addView(subViews[i]);
        }
    }
}
