package com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.textviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvTestFirstSetName extends androidx.appcompat.widget.AppCompatTextView {
    public TvTestFirstSetName(Context context, String txt) {
        super(context);
        setText(txt);
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        //setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_GENERAL_TXT));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                5,
                r.getDisplayMetrics()
        );
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,px,0,px);


    }
}
