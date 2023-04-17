package com.metaplikasyon.potkal.fragments.fragment_settings.element.switch_button;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class SwitchWordDefStyle extends SuperCustomSwitch {
    private final String SPKEY = SPEditor.APPEARANCE;
    private final String DETAILED = "detailed";
    private final String SIMPLE = "simple";
    SPEditor sp;

    public SwitchWordDefStyle(Context context, String onStr, String offStr) {
        super(context, onStr, offStr);
        sp = new SPEditor();
        onCreate();
    }

    private void onCreate() {
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    sp.setValues(getContext()
                            , SPKEY, DETAILED);
                } else {
                    sp.setValues(getContext()
                            , SPKEY, SIMPLE);
                }
            }
        });

        setState();
    }

    private void setState() {
        if (sp.getString(getContext(), SPKEY).equals(DETAILED))
            setChecked(true);
        else setChecked(false);
    }
}
