package com.metaplikasyon.potkal.fragments.fragment_settings.spinners.txt_size;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.spinners.CustomSpinner;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.spinners.ListenerSpinnerOnSelected;

public class SpnrTxtSize extends CustomSpinner {
    private final Context context;
    private final SPEditor spEditor;
    private final String sharedPrefKey;

    public SpnrTxtSize(@NonNull Context context,String sharedPrefKey) {
        super(context);
        this.context=context;
        spEditor=new SPEditor();
        this.sharedPrefKey=sharedPrefKey;
        onCreate();
    }


    private void onCreate() {
        initialize();
        setOnItemSelectedListener(new ListenerSpinnerOnSelected(sharedPrefKey, null));
    }

    public void initialize() {
        ArrayAdapter<Object> arrayAdapter= super.getNumericArrayadapter(context,15,55,5);
        setAdapter(arrayAdapter);
        setLastSelecedItem(arrayAdapter);
    }

     public int getLastValue() {
        return spEditor.getInt(context, sharedPrefKey);
    }

    @Override
    public void setLastSelecedItem(ArrayAdapter<Object> arrayAdapter) {
        setSelection(arrayAdapter.getPosition(getLastValue()));
    }
}