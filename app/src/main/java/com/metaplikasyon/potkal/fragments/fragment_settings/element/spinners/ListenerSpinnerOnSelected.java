package com.metaplikasyon.potkal.fragments.fragment_settings.element.spinners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class ListenerSpinnerOnSelected implements AdapterView.OnItemSelectedListener{
    private final String prefKey;
    private final String[] customArr;

    public ListenerSpinnerOnSelected(String prefKey, @Nullable String[] customArr) {
        this.prefKey = prefKey;
        this.customArr=customArr;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        SPEditor spEditor=new SPEditor();

        // Child view text color
        ((TextView)parent.getChildAt(0)).setTextColor( spEditor.getInt(view.getContext(), SPEditor.COL_SETTINGS_TXT));
        String selected;
        if(customArr!=null)  selected=customArr[pos];
        else selected=String.valueOf(parent.getItemAtPosition(pos));

        spEditor.setValues(view.getContext(), prefKey, String.valueOf(selected).toLowerCase());
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
