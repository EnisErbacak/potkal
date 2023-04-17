package com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class EtDlg extends androidx.appcompat.widget.AppCompatEditText {

    public EtDlg(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setStyle();
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        setTextColor(sp.getInt(getContext(), SPEditor.COL_GENERAL_TXT));
       // getBackground().setColorFilter( sp.getInt(getContext(), SPEditor.COL_GENERAL_TXT), PorterDuff.Mode.SRC_ATOP);
        getBackground().setColorFilter(
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(sp.getInt(getContext(), SPEditor.COL_GENERAL_TXT), BlendModeCompat.SRC_ATOP));
    }
}
