package com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class BtnDlg extends androidx.appcompat.widget.AppCompatButton {
    public BtnDlg(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setStyle();
    }

    public BtnDlg(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setStyle();
    }

    public BtnDlg(@NonNull Context context) {
        super(context);
        setStyle();
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        //getBackground().setColorFilter( sp.getInt(getContext(), SPEditor.COL_GENERAL_BTN_BG), PorterDuff.Mode.SRC_ATOP);
        getBackground().setColorFilter(
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(sp.getInt(getContext(), SPEditor.COL_GENERAL_BTN_BG), BlendModeCompat.SRC_ATOP));
        setTextColor(sp.getInt(getContext(), SPEditor.COL_GENERAL_BTN_TXT));
    }
}
