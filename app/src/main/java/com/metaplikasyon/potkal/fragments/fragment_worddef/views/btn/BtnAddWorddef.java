package com.metaplikasyon.potkal.fragments.fragment_worddef.views.btn;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.FragmentDialogAddWrdDef;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BtnAddWorddef extends FloatingActionButton {
    private final Context context;

    public BtnAddWorddef(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        onCreate();
    }

    public void onCreate() {
        setCondition();
        setStyle();
    }

    private void setCondition() {
        setOnClickListener(new BtnAddWordDefListener());
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        setSupportBackgroundTintList(ColorStateList.valueOf(sp.getInt(getContext(), SPEditor.COL_WORDDEF_BTN_BG)));
        getDrawable().setTint(sp.getInt(context, SPEditor.COL_GENERAL_BTN_ICON));
    }

    private class BtnAddWordDefListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            LinearLayout pnlWordDefVrt = view.getRootView().findViewById(R.id.pnlWordDefVrt);
            new FragmentDialogAddWrdDef(pnlWordDefVrt, FragmentWordDef.setName).show(((FragmentActivity)context).getSupportFragmentManager(), "ADD NEW WORD");
        }
    }
}


