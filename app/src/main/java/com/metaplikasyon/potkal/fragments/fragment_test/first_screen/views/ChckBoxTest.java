package com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class ChckBoxTest extends androidx.appcompat.widget.AppCompatCheckBox {
    public ChckBoxTest(@NonNull Context context) {
        super(context);
        setStyle();
    }

    void setStyle() {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
    }
}
