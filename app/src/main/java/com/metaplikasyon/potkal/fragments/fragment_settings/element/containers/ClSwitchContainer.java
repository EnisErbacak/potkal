package com.metaplikasyon.potkal.fragments.fragment_settings.element.containers;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.metaplikasyon.potkal.fragments.fragment_settings.element.switch_button.SuperCustomSwitch;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.textview.TvColor;
import com.metaplikasyon.potkal.other.PixelConverter;

public class ClSwitchContainer extends ConstraintLayout {
    private final TvColor tvColor;
    private final SuperCustomSwitch sw;

    private final String txt;
    private ConstraintSet constraintSet;

    public ClSwitchContainer(Context context, String txt, SuperCustomSwitch sw) {
        super(context);
        this.tvColor =new TvColor(context,txt);
        this.sw =sw;
        this.txt= tvColor.getText().toString();
        onCreate();
    }


    private void onCreate() {
        this.constraintSet=new ConstraintSet();
        setStyle();
    }

    private void setStyle() {
        PixelConverter pc=new PixelConverter(getContext());
        LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(pc.dp2Px(10),0,0,pc.dp2Px(10));
        setLayoutParams(lp);
        locateSubPanels();
    }

    private void locateSubPanels() {

        this.setId(ConstraintLayout.generateViewId());
        sw.setId(generateViewId());
        tvColor.setId(generateViewId());

        this.addView(tvColor);
        this.addView(sw);

        constraintSet.clone(this);

        constraintSet.connect(tvColor.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(tvColor.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvColor.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(sw.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END,0);
        constraintSet.connect(sw.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(sw.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP,0);


        constraintSet.connect(tvColor.getId(), ConstraintSet.END,   sw.getId(), ConstraintSet.START);
        constraintSet.connect(sw.getId(), ConstraintSet.START, tvColor.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }
}