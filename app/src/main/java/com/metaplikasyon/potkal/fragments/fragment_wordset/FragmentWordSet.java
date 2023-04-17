package com.metaplikasyon.potkal.fragments.fragment_wordset;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_wordset.editor.UiEdtrWrdSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.views.btn.BtnTopMenu;
import com.google.android.material.navigation.NavigationView;

public class FragmentWordSet extends Fragment {
    public static int ORDER_BY=0;
    private ProgressBar pb;

    private ConstraintLayout clMain, clNavMain; // Top parent container of wordset.
    private ScrollView svSub, svNav; // Inner container for wordset fragment.
    private TextView tvPotkalWordset, tvNvPotkalWordset;
    private DrawerLayout dlWordset;
    private BtnTopMenu btnTopMenu;
    private NavigationView navView;
    public static FragmentWordSet getInstance() {
        return new FragmentWordSet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_word_set,container,false);
        navView=view.findViewById(R.id.nvWordset);
        tvNvPotkalWordset=view.findViewById(R.id.tvNvPotkalWordset);
        clNavMain=view.findViewById(R.id.clNavMain);
        svNav=view.findViewById(R.id.svNav);
        pb=view.findViewById(R.id.pBarWordSet);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new UiEdtrWrdSet(getContext()).updateScrn(ORDER_BY);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCondition();
        btnTopMenu=view.findViewById(R.id.btnTopMenu);
        clMain=view.findViewById(R.id.clMainWordset);
        svSub=view.findViewById(R.id.svMainWordset);

        dlWordset=view.findViewById(R.id.dlWordset);
        tvPotkalWordset=view.findViewById(R.id.tvPotkalWordset);


        tvPotkalWordset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlWordset.openDrawer(GravityCompat.START);
            }
        });
        setStyle();
        new UiEdtrWrdSet(getContext()).updateScrn(ORDER_BY);
    }

    public void setStyle() {
        Context context=getContext();
        SPEditor spEditor=new SPEditor();
        btnTopMenu.setSupportBackgroundTintList(ColorStateList.valueOf(spEditor.getInt(getContext(), SPEditor.COL_WORDSET_STATUSBAR)));
        btnTopMenu.getDrawable().setTint(spEditor.getInt(getContext(), SPEditor.COL_WORDSET_STATUSBAR_TXT));
        pb.getIndeterminateDrawable().setTint(spEditor.getInt(context
                ,SPEditor.COL_WORDSET_STATUSBAR_TXT));

        clMain.setBackgroundColor(spEditor.getInt(context, SPEditor.COL_WORDSET_STATUSBAR));
        svSub.setBackgroundColor(spEditor.getInt(context, SPEditor.COL_WORDSET_BG));
        tvPotkalWordset.setTextColor(spEditor.getInt(context, SPEditor.COL_WORDSET_STATUSBAR_TXT));
        tvNvPotkalWordset.setTextColor(spEditor.getInt(context, SPEditor.COL_WORDSET_STATUSBAR_TXT));

        //navView.setBackgroundColor(spEditor.getInt(context, SPEditor.COL_GENERAL_BG));
        //svNav.setBackgroundColor(spEditor.getInt(context, SPEditor.COL_GENERAL_BG));
        clNavMain.setBackgroundColor(spEditor.getInt(getContext(), SPEditor.COL_WORDSET_STATUSBAR));
    }

    private void setCondition() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}