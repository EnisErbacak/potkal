package com.metaplikasyon.potkal.fragments.nav_view.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.FragmentTestFirst;

public class BtnCreateTest extends androidx.appcompat.widget.AppCompatButton {

    public BtnCreateTest(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    void onCreate() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, new FragmentTestFirst()).commit();
                FragmentManager fragManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction fragTransacion = fragManager.beginTransaction();

                //fragTransacion.add(R.id.containerActivityMain, new FragmentTestFirst());
                fragTransacion.addToBackStack(null);
                fragTransacion.replace(R.id.containerActivityMain, new FragmentTestFirst());
                fragTransacion.commit();
            }
        });
        setStyle();
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();

        getBackground().setTint(sp.getInt(getContext()
                ,SPEditor.COL_SETTINGS_PANEL));
        setTextColor(sp.getInt(getContext()
                ,SPEditor.COL_SETTINGS_TXT));

//        setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDSET_STATUSBAR_TXT));
//        setBackgroundTintList(ColorStateList.valueOf(sp.getInt(getContext(), SPEditor.COL_WORDSET_STATUSBAR)));
        //setBackground(sp.getInt(getContext(), SPEditor.COL_GENERAL_BTN_BG));
    }
}
