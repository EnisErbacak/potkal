package com.metaplikasyon.potkal.fragments.fragment_wordset.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_wordset.dialog.FragmentAddSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnAddSet extends FloatingActionButton
{

    public BtnAddSet(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    public void onCreate() {
        setCondition();
        setStyle();
    }
    private void setStyle() {
        SPEditor sp=new SPEditor();
        setSupportBackgroundTintList(ColorStateList.valueOf(sp.getInt(getContext(),
                SPEditor.COL_WORDSET_BTN_BG)));
        getDrawable().setTint(sp.getInt(getContext(),
                SPEditor.COL_GENERAL_BTN_ICON));
    }

    private void setCondition() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                new FragmentAddSet(view.getRootView().findViewById(R.id.pnlWrdSetMain)).show(
                        ((FragmentActivity)getContext()).getSupportFragmentManager(),"ADD NEW SET"
                );
            }
        });
    }
}