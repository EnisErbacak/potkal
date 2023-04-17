package com.metaplikasyon.potkal.fragments.fragment_settings.element.switch_button;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CompoundButton;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class SwitchTestMovement extends SuperCustomSwitch{
    private final String SPKEY = SPEditor.TEST_BUTTONS;
    private final String ON = "on";
    private final String OFF = "off";
    private SPEditor sp;

    public SwitchTestMovement(Context context, String onStr, String offStr) {
        super(context, onStr, offStr);
        sp=new SPEditor();
        onCreate();
    }

    private void onCreate() {
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    sp.setValues(getContext()
                            , SPKEY, ON);
                } else {
                    sp.setValues(getContext()
                            , SPKEY, OFF);
                }
            }
        });

        setState();
    }

    private void setState() {
        if (sp.getString(getContext(), SPKEY).equals(ON))
            setChecked(true);
        else setChecked(false);
    }
}
