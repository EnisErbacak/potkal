package com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.views.btn;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.views.FragmentRGBPicker;

public class BtnChangeColor extends androidx.appcompat.widget.AppCompatButton {
    private int colHex;
    private final String prefKey;
    private final Context context;
    private int dp;
    public BtnChangeColor(@NonNull Context context, String prefKey) {
        super(context);
        this.context=context;
        onCreate(context,prefKey);
        this.prefKey=prefKey;
        colHex= new SPEditor().getInt(context, prefKey);
        setOnClickListener(new Lstner());
    }

    private void onCreate(Context context, String prefKey) {
        colHex= new SPEditor().getInt(context, prefKey);
        setPadding(0
                ,(int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics())
                ,0
                ,(int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics()));

        setStyle();
    }

    private void setStyle() {
        DisplayMetrics displaymetrics = getContext().getResources().getDisplayMetrics();
        dp = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 10, displaymetrics );

        setBackground(getGradientDrawable(colHex, dp));
    }

    private Drawable getGradientDrawable(int color,int radius) {
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    public void update(int colHex) {
        setBackground(getGradientDrawable(colHex, dp));
    }

    private class Lstner implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            new FragmentRGBPicker(prefKey,BtnChangeColor.this, colHex).show( ((FragmentActivity)context).getSupportFragmentManager(), "RGB COLOR PICKER");
        }
    }
}
