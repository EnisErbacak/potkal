package com.metaplikasyon.potkal.fragments.fragment_wordset.views.txt_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_wordset.popup.PopupWordSetEdit;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;

public class TvWordsetLeft extends androidx.appcompat.widget.AppCompatTextView {
    private final String setName;
    private final boolean attachListener;

    private final int COL_TXT = Color.parseColor("#111F29");
    //private final int SIZE_TXT=pix2Dip(10);

    private final Context context;


    public TvWordsetLeft(Context context, String setName, boolean attachListener) {
        super(context);
        this.setName = setName;
        this.context = context;
        this.attachListener=attachListener;
        onCreate();
    }

    void onCreate() {
        setStyle();
        setConditions(TvWordsetLeft.this);
        setText(setName);
    }

    // SETS VISUAL FEATURES
    void setStyle() {
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_WORDSET_TXT));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, getTxtSize());
        setTypeface(getTypeface(), Typeface.BOLD);
        setSingleLine(false);
    }

    //SETS BACKGROUND FOR DRAWABLE
    void setConditions(View view) {
        if (attachListener) {
            view.setOnClickListener(new TxtViewWrdSetLngClckLstnr());
            setOnLongClickListener(new TxtViewWrdSetLngLstnr());
        }
    }

    public String getWordsetName() {
        return setName;
    }

    private int getTxtSize() {
        return new SPEditor().getInt(context, SPEditor.TXT_SIZE_SET);
    }

    private class TxtViewWrdSetLngClckLstnr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            FragmentManager fragManager = ((FragmentActivity)context).getSupportFragmentManager();
            FragmentTransaction fragTransacion = fragManager.beginTransaction();

            //fragTransacion.add(R.id.containerActivityMain, new FragmentWordDef(setName));
            fragTransacion.replace(R.id.containerActivityMain, new FragmentWordDef(setName));
            fragTransacion.addToBackStack(null);
            fragTransacion.commit();
                //((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, new FragmentWordDef(setName)).commit();
        }
    }

    private class TxtViewWrdSetLngLstnr implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            new PopupWordSetEdit(context, view).show();
            return false;
        }
    }
}
