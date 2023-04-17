package com.metaplikasyon.potkal.fragments.fragment_worddef.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.popup.PopupSort;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnSrt extends FloatingActionButton {

    public BtnSrt(Context context, AttributeSet attrs) {
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
        new PopupSort(getContext(), this).show();
        return super.performClick();
    }
}
