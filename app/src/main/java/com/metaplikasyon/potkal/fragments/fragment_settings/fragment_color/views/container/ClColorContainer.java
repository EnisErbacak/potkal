package com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.views.container;

import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.metaplikasyon.potkal.fragments.fragment_settings.element.textview.TvColor;
import com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.views.btn.BtnChangeColor;

public class ClColorContainer extends ConstraintLayout {
    private final TvColor tvColor;
    private final BtnChangeColor btnChangeColor;
    private ConstraintSet constraintSet;

    public ClColorContainer(Context context, String txt, String prefKey) {
        super(context);
        this.tvColor =new TvColor(context,txt);
        this.btnChangeColor =new BtnChangeColor(context, prefKey);
        onCreate();
    }

    private void onCreate() {
        this.constraintSet=new ConstraintSet();
        setView();
    }

    private void setView() {
        setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        locateSubPanels();
    }


    private void locateSubPanels() {

        this.setId(ConstraintLayout.generateViewId());
        tvColor.setId(generateViewId());
        btnChangeColor.setId(generateViewId());

        this.addView(tvColor);
        this.addView(btnChangeColor);

        constraintSet.clone(this);

        constraintSet.connect(tvColor.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START,50);
        constraintSet.connect(tvColor.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvColor.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(btnChangeColor.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END
                ,(int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics()));
        constraintSet.connect(btnChangeColor.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM,50);
        constraintSet.connect(btnChangeColor.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP,50);


        constraintSet.connect(tvColor.getId(), ConstraintSet.END,   btnChangeColor.getId(), ConstraintSet.START);
        constraintSet.connect(btnChangeColor.getId(), ConstraintSet.START, tvColor.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }

}
