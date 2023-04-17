package com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EtDlgExmp extends EtDlg{
    public EtDlgExmp(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTypeface(getTypeface(), Typeface.ITALIC);
    }
}
