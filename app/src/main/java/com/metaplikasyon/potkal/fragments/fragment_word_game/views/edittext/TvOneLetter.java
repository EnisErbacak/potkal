package com.metaplikasyon.potkal.fragments.fragment_word_game.views.edittext;

import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.metaplikasyon.potkal.other.PixelConverter;

public class TvOneLetter extends androidx.appcompat.widget.AppCompatTextView {

    public TvOneLetter(@NonNull Context context, String str) {
        super(context);
        setText(str);
        onCreate();

        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int wd=getWidth();
                int mwd=getMeasuredWidth();
                setStyle();
                 wd=getWidth();
                 mwd=getMeasuredWidth();
                System.out.println("asdjfhaskdjfh");
            }
        });
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        PixelConverter pc=new PixelConverter(getContext());
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //lp.setMargins(new PixelConverter(getContext()).dp2Px(5),0,0,0);
        setLayoutParams(lp);
        //lp.setMargins(pc.dp2Px(3), pc.dp2Px(3), pc.dp2Px(3), pc.dp2Px(3));
        //setPadding(pc.dp2Px(7), pc.dp2Px(7), pc.dp2Px(7), pc.dp2Px(7));
    }


}
