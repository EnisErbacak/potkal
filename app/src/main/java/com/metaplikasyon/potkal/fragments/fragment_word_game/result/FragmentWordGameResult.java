package com.metaplikasyon.potkal.fragments.fragment_word_game.result;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_word_game.process.GameScreen;
import com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.containers.ClWordGameRes;

import java.util.ArrayList;

public class FragmentWordGameResult extends Fragment {

    private ArrayList<GameScreen> gameScreenList;
    private TextView tvWordGameResPtsStr,tvWordGameResPts,tvWordGameResPtsEarnedStr
            ,tvWordGameResPtsEarned,tvWordGameResIncorrectStr,tvWordGameResIncorrect
            ,tvWordGameResCorrectStr,tvWordGameResCorrect,tvWordGameResNullStr,tvWordGameResNull
            ,tvWordGameResultIncorrectWords, tvWordGameResultCorrectWords, tvWordGameResultNullWords;
    private LinearLayout llWordgameCorrects, llWordGameIncorrects, llWordGameNull;
    private Context context;
    private Button btnWordGameExit;
    private ConstraintLayout clWordGameResUpper,clWordGameResLower;

    public FragmentWordGameResult(ArrayList<GameScreen> gameScreenList) {
        this.gameScreenList = gameScreenList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_word_game_result, container,false);
        clWordGameResLower=rootView.findViewById(R.id.clWordGameResLower);
        clWordGameResUpper=rootView.findViewById(R.id.clWordGameResUpper);
        llWordgameCorrects=rootView.findViewById(R.id.llWordGameCorrects);
        llWordGameIncorrects=rootView.findViewById(R.id.llWordGameIncorrects);
        llWordGameNull=rootView.findViewById(R.id.llWordGameNull);
        tvWordGameResPtsStr=rootView.findViewById(R.id.tvWordGameResPtsStr);
        tvWordGameResPts=rootView.findViewById(R.id.tvWordGameResPts);
        tvWordGameResPtsEarnedStr=rootView.findViewById(R.id.tvWordGameResPtsEarnedStr);
        tvWordGameResPtsEarned=rootView.findViewById(R.id.tvWordGameResPtsEarned);
        tvWordGameResIncorrectStr=rootView.findViewById(R.id.tvWordGameResIncorrectStr);
        tvWordGameResIncorrect=rootView.findViewById(R.id.tvWordGameResIncorrect);
        tvWordGameResCorrectStr=rootView.findViewById(R.id.tvWordGameResCorrectStr);
        tvWordGameResCorrect=rootView.findViewById(R.id.tvWordGameResCorrect);
        tvWordGameResNullStr=rootView.findViewById(R.id.tvWordGameResNullStr);
        tvWordGameResNull=rootView.findViewById(R.id.tvWordGameResNull);
        tvWordGameResultIncorrectWords=rootView.findViewById(R.id.tvWordGameResultIncorrectWords);
        tvWordGameResultCorrectWords=rootView.findViewById(R.id.tvWordGameResultCorrectWords);
        tvWordGameResultNullWords=rootView.findViewById(R.id.tvWordGameResultNullWords);
        btnWordGameExit=rootView.findViewById(R.id.btnWordGameExit);
        this.context=getContext();

        btnWordGameExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentTransaction fragTransacion = fm.beginTransaction();
                fragTransacion.commit();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // Removes all the fragments exist before
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addChildren();
        setStyle();
    }

    private void addChildren() {
        int correct=0, inCorrect=0, unAnwsered=0, ptsTotal=0, ptsGained=0;
        for(GameScreen gs: gameScreenList) {
            if(gs.getAnsweredAs()==1) {
                llWordgameCorrects.addView(new ClWordGameRes(context, gs.getAnswer(), gs.getAnswer().length()
                        , gs.getPts(), gs.getPtsGained(), gs.getShownLetterCnt()));
                correct++;
                ptsGained+=gs.getPtsGained();
            }
            if(gs.getAnsweredAs()==-1) {
                llWordGameIncorrects.addView(new ClWordGameRes(context, gs.getAnswer(), gs.getAnswer().length()
                        , gs.getPts(), -gs.getPts() /*gs.getPtsGained() */, gs.getShownLetterCnt()));
                inCorrect++;
                //ptsGained+=gs.getPtsGained();
                ptsGained -=gs.getPts();//gs.getPtsGained();
            }
            if(gs.getAnsweredAs()==0) {
                llWordGameNull.addView(new ClWordGameRes(context, gs.getAnswer(), gs.getAnswer().length()
                        , gs.getPts(), gs.getPtsGained(), gs.getShownLetterCnt()));
                unAnwsered++;
            }
            ptsTotal+=gs.getPts();
        }
        tvWordGameResCorrect.setText(String.valueOf(correct));
        tvWordGameResIncorrect.setText(String.valueOf(inCorrect));
        tvWordGameResNull.setText(String.valueOf(unAnwsered));
        tvWordGameResPts.setText(String.valueOf(ptsTotal));
        tvWordGameResPtsEarned.setText(String.valueOf(ptsGained));
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        clWordGameResUpper.getBackground().setTint(sp.getInt(context, SPEditor.COL_WORDGAME_PANEL_BG));
        clWordGameResLower.getBackground().setTint(sp.getInt(context,SPEditor.COL_WORDGAME_PANEL_BG));

        tvWordGameResPtsStr.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResPts.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResPtsEarnedStr.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResPtsEarned.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResIncorrectStr.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResIncorrect.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResCorrectStr.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResCorrect.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResNullStr.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResNull.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResultIncorrectWords.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResultCorrectWords.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
        tvWordGameResultNullWords.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));

        btnWordGameExit.getBackground().setTint(sp.getInt(context,SPEditor.COL_WORDGAME_PANEL_BG));
        btnWordGameExit.setTextColor(sp.getInt(context, SPEditor.COL_WORDGAME_TXT));
    }
}