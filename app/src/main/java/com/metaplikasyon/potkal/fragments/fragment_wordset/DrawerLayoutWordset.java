package com.metaplikasyon.potkal.fragments.fragment_wordset;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

public class DrawerLayoutWordset extends DrawerLayout {

    private TextView tvPotkalWordset, tvPotkalNav;
    private final boolean opened=false;
    public DrawerLayoutWordset(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setStyle();

        /*
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                tvPotkalWordset=view.getRootView().findViewById(R.id.tvPotkalWordset);
                Animation animRotate=AnimationUtils.loadAnimation(context, R.anim.rotate);
            }
        });
        /*
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tvPotkalWordset=view.getRootView().findViewById(R.id.tvPotkalWordset);
                Animation animRotate=AnimationUtils.loadAnimation(context, R.anim.rotate);

                //tvPotkalWordset.setRotation(90);
                //tvPotkalWordset.startAnimation(animRotate);
                return false;
            }
        });

         */

        addDrawerListener(
                new DrawerListener() {
                    @Override
                    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                    }

                    @Override
                    public void onDrawerOpened(@NonNull View drawerView) {
                    }

                    @Override
                    public void onDrawerClosed(@NonNull View drawerView) {
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                }
        );
    }



    void setStyle() {
        LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(lp);
    }
}