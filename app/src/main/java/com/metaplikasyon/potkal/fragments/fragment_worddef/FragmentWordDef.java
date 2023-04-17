package com.metaplikasyon.potkal.fragments.fragment_worddef;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.operator.BuilderEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.btn.BtnSrchWrd;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.edit_text.EtSrchWrd;

public class FragmentWordDef extends Fragment  {
    //********REFACTORED
    private TextView tvWrdDefTop;
    private ConstraintLayout clMainWorddef;
    private ScrollView svMainWorddef;
    private TextView tvWorddefTop;
    private BtnSrchWrd btnSrchWrd;
    private EtSrchWrd etSrchWrd;
    private LinearLayout pnlWordDefVrt;

    public static int ORDER_BY=0;

    public static String setName;

    public FragmentWordDef(String setName)
    {
        FragmentWordDef.setName =setName;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCondition(getView());
        Context context=getContext();
        new BuilderEditor().getUiEditor(getContext(), setName).updateScreen();

        pnlWordDefVrt=view.findViewById(R.id.pnlWordDefVrt);
        etSrchWrd=view.findViewById(R.id.etSrchWrd);
        etSrchWrd.setPnlWordDefVrt(pnlWordDefVrt);
        btnSrchWrd=view.findViewById(R.id.btnSearchWord);
        btnSrchWrd.setEtSrchWrd(etSrchWrd);


        tvWrdDefTop=getView().findViewById(R.id.tvWrdDefTop);
        tvWrdDefTop.setText(setName);


        clMainWorddef=view.findViewById(R.id.clMainWorddef);
        svMainWorddef=view.findViewById(R.id.svMainWorddef);
        setStyle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_word_def,container,false);
    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new KeyLstnrFragWrd());
    }

    public void setStyle() {
        Context context=getContext();
        SPEditor spEditor=new SPEditor();
        tvWrdDefTop.setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_WORDDEF_TOPBAR_TXT));
        svMainWorddef.setBackgroundColor(spEditor.getInt(context, SPEditor.COL_WORDDEF_BG));
        clMainWorddef.setBackgroundColor(spEditor.getInt(context, SPEditor.COL_WORDDEF_STATUSBAR));
    }

    @Override
    public void onDestroy() {
        setName=null;
        super.onDestroy();
    }

    private class KeyLstnrFragWrd implements View.OnKeyListener {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            if(keyEvent.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                int count =((FragmentActivity)getContext()).getSupportFragmentManager().getBackStackEntryCount(); //.getBackStackEntryCount();
                ((FragmentActivity)getContext()).getSupportFragmentManager().getFragments().get(count-1).onResume();
                ((FragmentActivity)getContext()).getSupportFragmentManager().popBackStackImmediate();
                return true;
            }
            return false;
        }
    }
}