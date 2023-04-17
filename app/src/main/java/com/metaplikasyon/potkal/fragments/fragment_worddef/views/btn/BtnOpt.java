package com.metaplikasyon.potkal.fragments.fragment_worddef.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.popup.PopupWorddefOpt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnOpt extends FloatingActionButton {
    public BtnOpt(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    private void onCreate() {
        setStyle();
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        setSupportBackgroundTintList(ColorStateList.valueOf(sp.getInt(getContext(), SPEditor.COL_WORDDEF_BTN_BG)));
        getDrawable().setTint(sp.getInt(getContext(), SPEditor.COL_GENERAL_BTN_ICON));
    }

    public boolean performClick() {
        //new PopupWorddefOpt(getContext(), this).show();
        new PopupWorddefOpt(getContext(), this).show();
        return super.performClick();
    }
}
