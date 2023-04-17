package com.metaplikasyon.potkal.fragments.fragment_worddef.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class BtnSrchWrd extends FloatingActionButton {
    private EditText etSrchWrd;
    public BtnSrchWrd(@NonNull Context context, @Nullable AttributeSet attrs) {
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
        etSrchWrd.bringToFront();
        etSrchWrd.setVisibility(VISIBLE);
        etSrchWrd.setInputType(InputType.TYPE_CLASS_TEXT);
        etSrchWrd.setFocusable(true);
        etSrchWrd.requestFocus();
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT);
        return super.performClick();
    }

    public void setEtSrchWrd(EditText etSrchWrd) {
        this.etSrchWrd = etSrchWrd;
    }
}
