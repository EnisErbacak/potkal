package com.metaplikasyon.potkal.fragments.fragment_word_game.first;

import android.content.Context;
import android.content.res.ColorStateList;
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
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.CreateFirstScreen;
import com.metaplikasyon.potkal.fragments.fragment_wordset.FragmentWordSet;

public class FragmentWordGameFirst extends Fragment {

    public static final int QTypeByCollectionOrder=0, QTypeByLetterCount=1, QTypeByRandom=2;
    private LinearLayout llWrdGameSets;
    private ConstraintLayout clWordGameMain;
    private TextView tvWordGameFirstQuestionType,tvWordGameFirstSelectSets;
    private RadioButton rbWordGameByCollectionOrder, rbWordGameByLetterCount,rbWordGameByRandom;
    private Button btnStartWordGame;
    private LinearLayout llWordGameFirstSubMain;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_word_game_first,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCondition(getView());
        llWrdGameSets=getView().findViewById(R.id.llWrdGameSets);
        new CreateFirstScreen().create(getContext(), llWrdGameSets, new SPEditor().getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        clWordGameMain=getView().findViewById(R.id.clWordGameFirstMain);
        tvWordGameFirstQuestionType=getView().findViewById(R.id.tvWordGameFirstQuestionType);
        tvWordGameFirstSelectSets= getView().findViewById(R.id.tvWordGameFirstSelectSets);
        btnStartWordGame=getView().findViewById(R.id.btnStartWordGame);
        rbWordGameByCollectionOrder=getView().findViewById(R.id.rbWordGameByCollectionOrder);
        rbWordGameByLetterCount =getView().findViewById(R.id.rbWordGameByLetterCount);
        rbWordGameByRandom =getView().findViewById(R.id.rbWordGameByRandom);
        llWordGameFirstSubMain= getView().findViewById(R.id.llWordGameFirstSubMain);
        setStyle(getContext());
    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new FragmentListenerBackPress(FragmentWordGameFirst.this, new FragmentWordSet()));
    }

    private void setStyle(Context context) {
        SPEditor spEditor=new SPEditor();
        clWordGameMain.getBackground().setColorFilter(
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        spEditor.getInt(context, SPEditor.COL_WORDGAME_BG)
                        , BlendModeCompat.SRC_ATOP));
        llWordGameFirstSubMain.getBackground().setTint(spEditor.getInt(context,SPEditor.COL_WORDGAME_PANEL_BG));
        tvWordGameFirstSelectSets.setTextColor(spEditor.getInt(context,
                SPEditor.COL_WORDGAME_TXT));
        tvWordGameFirstQuestionType.setTextColor(spEditor.getInt(context,
                SPEditor.COL_WORDGAME_TXT));
        btnStartWordGame.setTextColor(spEditor.getInt(context, SPEditor.COL_WORDGAME_TXT));
        btnStartWordGame.getBackground().setTint(spEditor.getInt(context,SPEditor.COL_WORDGAME_PANEL_BG));


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
                                    spEditor.getInt(context, SPEditor.COL_WORDGAME_TXT), // disabled
                                    spEditor.getInt(context, SPEditor.COL_WORDGAME_TXT)   // enabled
                            }
            );

            rbWordGameByCollectionOrder.setButtonTintList(colorStateList); // set the color tint list
            rbWordGameByLetterCount.setButtonTintList(colorStateList); // set the color tint list
            rbWordGameByRandom.setButtonTintList(colorStateList); // set the color tint list

            rbWordGameByCollectionOrder.setTextColor(spEditor.getInt(context, SPEditor.COL_WORDGAME_TXT)); // set the color tint list
            rbWordGameByLetterCount.setTextColor(spEditor.getInt(context, SPEditor.COL_WORDGAME_TXT)); // set the color tint list
            rbWordGameByRandom.setTextColor(spEditor.getInt(context, SPEditor.COL_WORDGAME_TXT)); // set the color tint list
        }
    }
}
