package com.metaplikasyon.potkal.fragments.fragment_settings.element.containers;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.metaplikasyon.potkal.fragments.fragment_settings.element.textview.TvColor;
import com.metaplikasyon.potkal.other.PixelConverter;

public class ClSpinnerContainer extends ConstraintLayout {
    private final TvColor tvColor;
    private final Spinner sp;

    private final String txt;
    private ConstraintSet constraintSet;

    public ClSpinnerContainer(Context context, String txt, Spinner sp) {
        super(context);
        this.tvColor =new TvColor(context,txt);
        this.sp=sp;
        this.txt= tvColor.getText().toString();
        onCreate();
    }


    private void onCreate() {
        this.constraintSet=new ConstraintSet();
        setStyle();
    }

    private void setStyle() {
        LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMarginStart(new PixelConverter(getContext()).dp2Px(10));
        setLayoutParams(lp);
        locateSubPanels();
    }

    private void locateSubPanels() {

        this.setId(ConstraintLayout.generateViewId());
        sp.setId(generateViewId());
        tvColor.setId(generateViewId());

        this.addView(tvColor);
        this.addView(sp);

        constraintSet.clone(this);

        constraintSet.connect(tvColor.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(tvColor.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvColor.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(sp.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END,0);
        constraintSet.connect(sp.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(sp.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP,0);


        constraintSet.connect(tvColor.getId(), ConstraintSet.END,   sp.getId(), ConstraintSet.START);
        constraintSet.connect(sp.getId(), ConstraintSet.START, tvColor.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }
}
