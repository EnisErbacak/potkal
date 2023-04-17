package com.metaplikasyon.potkal.fragments.nav_view.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.FragmentTestFirst;
import com.metaplikasyon.potkal.fragments.fragment_word_game.first.FragmentWordGameFirst;

public class BtnCreateWordGame extends androidx.appcompat.widget.AppCompatButton {

    public BtnCreateWordGame(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    void onCreate() {
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction fragTransacion = fragManager.beginTransaction();

                fragTransacion.addToBackStack(null);
                fragTransacion.replace(R.id.containerActivityMain, new FragmentWordGameFirst());
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
    }
}
