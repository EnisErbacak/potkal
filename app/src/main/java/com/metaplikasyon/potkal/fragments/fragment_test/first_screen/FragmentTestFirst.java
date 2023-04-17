package com.metaplikasyon.potkal.fragments.fragment_test.first_screen;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.fragment.app.Fragment;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_test.FragmentListenerBackPress;
import com.metaplikasyon.potkal.fragments.fragment_wordset.FragmentWordSet;

public class FragmentTestFirst extends Fragment {
    private LinearLayout llTestFirstSets, llTestFirstUpper, llTestFirstLower;
    private ConstraintLayout clTestFirstMain, clTestFirstUpperMain;
    private RadioButton rdBtnQWrdDef, rdBtnQDefWord;
    private TextView tvQstnType, tvTestFirstSlctSets;
    private Button btnStartTest;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_test_first,container,false);
        clTestFirstUpperMain=view.findViewById(R.id.clTestFirstSubMain);
        rdBtnQWrdDef=view.findViewById(R.id.rdBtnQWrdDef);
        rdBtnQDefWord=view.findViewById(R.id.rdBtnQDefWord);
        tvQstnType=view.findViewById(R.id.tvQstnType);
        tvTestFirstSlctSets=view.findViewById(R.id.tvWordGameFirstSelectSets);
        btnStartTest=view.findViewById(R.id.btnStartWordTest);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCondition(getView());

        llTestFirstSets =view.findViewById(R.id.llWrdGameSets);
        new CreateFirstScreen().create(getContext(), llTestFirstSets, new SPEditor().getInt(getContext(), SPEditor.COL_TEST_TXT));

        clTestFirstMain=view.findViewById(R.id.clWordGameMain);
        llTestFirstUpper =view.findViewById(R.id.llTestFirstUpper);
        llTestFirstLower=view.findViewById(R.id.llTestFirstLower);

        setStyle(getContext());
    }

    private void setStyle(Context context) {
        SPEditor spEditor=new SPEditor();
        clTestFirstMain.setBackgroundColor(spEditor.getInt(context, SPEditor.COL_TEST_BG));
        clTestFirstUpperMain.getBackground().setTint(spEditor.getInt(context, SPEditor.COL_TEST_PANEL_BG));
        tvQstnType.setTextColor(spEditor.getInt(context, SPEditor.COL_TEST_TXT));
        tvTestFirstSlctSets.setTextColor(spEditor.getInt(context, SPEditor.COL_TEST_TXT));
        rdBtnQDefWord.setTextColor(spEditor.getInt(context, SPEditor.COL_TEST_TXT));
        rdBtnQWrdDef.setTextColor(spEditor.getInt(context, SPEditor.COL_TEST_TXT));
        btnStartTest.getBackground().setTint(spEditor.getInt(context, SPEditor.COL_TEST_PANEL_BG));
        btnStartTest.setTextColor(spEditor.getInt(context, SPEditor.COL_TEST_TXT));


        if(Build.VERSION.SDK_INT >= 21)
        {
            ColorStateList colorStateList = new ColorStateList(
                    new int[][]
                            {
                                    new int[]{-android.R.attr.state_enabled}, // Disabled
                                    new int[]{android.R.attr.state_enabled}   // Enabled
                            },
                    new int[]
                            {
                                    spEditor.getInt(context, SPEditor.COL_TEST_TXT), // disabled
                                    spEditor.getInt(context, SPEditor.COL_TEST_TXT)   // enabled
                            }
            );

            rdBtnQDefWord.setButtonTintList(colorStateList); // set the color tint list
            rdBtnQWrdDef.setButtonTintList(colorStateList); // set the color tint list
        }

    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new FragmentListenerBackPress(FragmentTestFirst.this, new FragmentWordSet()));
    }
}
