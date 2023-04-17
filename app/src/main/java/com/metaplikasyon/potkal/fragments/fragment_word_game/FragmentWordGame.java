package com.metaplikasyon.potkal.fragments.fragment_word_game;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_word_game.process.GameManager;
import com.metaplikasyon.potkal.fragments.fragment_word_game.result.FragmentWordGameResult;
import com.metaplikasyon.potkal.fragments.fragment_word_game.views.edittext.TvOneLetter;

import java.util.ArrayList;
/*
SCORE STR
SCORE İMAGE VİEW
UPPER PANEL
MİD PANEL
LOWER PANEL
BUTTON BG
BUTTON TXT

COMB BG
COMB LETTER

QUESTION TXT
GET LETTER BUTTON BG
GET LETTER BUTTON TXT

 */


public class FragmentWordGame extends Fragment {
    private LinearLayout llWrdGameQstn;
    private TextView tvWrdGameDef, tvWrdGameCrrct, tvWrdGameIncrrct, tvWrdGameScore
            ,tvWrdGameWrdPts, tvWrdGameLetterCount, tvWrdGamePtsGain,tvWordGameDef
            , tvWrdGameLetterPts, tvWordGameClue, tvWordGameChance,tvWordGameKind
            ,tvInputLetterCount,tvWordGameTotalPtsStr,tvWordGameWordPtsStr,tvWordGameLetterCountStr
            ,tvWordGamePtsToEarn,tvWordGamePtsToEarnStr;

    private AppCompatImageView ivCorrect,ivIncorrect;
    private TvOneLetter[] arrTv;
    private ArrayList<String> setNameList;
    private GameManager gameManager;
    private Button btnWordGameGetLetter, btnWordGameNext, btnWordGameAnswer, btnWordGameShowClue;
    private EditText etWrdGameAnswr;
    private ConstraintLayout clWordGameMain, clWordGameScoreMain, clWordGameLower;
    private ScrollView svWordGameCombs, svWordGameMid;
    private int qType;

    public FragmentWordGame(ArrayList<String> setNameList, int qType) {
        this.setNameList = setNameList;
        this.qType=qType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_word_game, container, false);
        llWrdGameQstn = rootView.findViewById(R.id.llWrdGameLetters);
        tvWrdGameDef=rootView.findViewById(R.id.tvWordGameDef);

        etWrdGameAnswr=rootView.findViewById(R.id.etWrdGameAnswr);

        tvWrdGamePtsGain=rootView.findViewById(R.id.tvWordGamePtsToEarn);

        tvInputLetterCount=rootView.findViewById(R.id.tvInputLetterCount);

        clWordGameMain=rootView.findViewById(R.id.clWordGameMain);
        clWordGameScoreMain=rootView.findViewById(R.id.clWordGameScoreMain);
        clWordGameLower=rootView.findViewById(R.id.clWordGameLower);
        svWordGameCombs=rootView.findViewById(R.id.svWordGameCombs);
        svWordGameMid=rootView.findViewById(R.id.svWordGameMid);

        ivCorrect=rootView.findViewById(R.id.ivWordGameCorrect);
        ivIncorrect=rootView.findViewById(R.id.ivWordGameIncorrect);
        tvWrdGameCrrct=rootView.findViewById(R.id.tvWrdGameCrrct);
        tvWrdGameIncrrct=rootView.findViewById(R.id.tvWrdGameIncorrect);
        tvWrdGameScore=rootView.findViewById(R.id.tvWrdGameScore);
        tvWordGameTotalPtsStr=rootView.findViewById(R.id.tvWordGameTotalPtsStr);

        tvWordGameWordPtsStr=rootView.findViewById(R.id.tvWordGameWordPtsStr);
        tvWrdGameWrdPts=rootView.findViewById(R.id.tvWrdGameWordPts);
        tvWordGameLetterCountStr=rootView.findViewById(R.id.tvWordGameLetterCountStr);
        tvWrdGameWrdPts=rootView.findViewById(R.id.tvWrdGameWordPts);
        tvWrdGameLetterCount=rootView.findViewById(R.id.tvWordGameLetterCount);

        tvWordGameDef=rootView.findViewById(R.id.tvWordGameDef);
        tvWordGameKind=rootView.findViewById(R.id.tvWordGameKind);
        tvWordGameClue=rootView.findViewById(R.id.tvWordGameClue);

        tvWordGamePtsToEarnStr=rootView.findViewById(R.id.tvWordGamePtsToEarnStr);
        tvWordGamePtsToEarn=rootView.findViewById(R.id.tvWordGamePtsToEarn);
        btnWordGameGetLetter=rootView.findViewById(R.id.btnWordGameGetLetter);
        btnWordGameNext=rootView.findViewById(R.id.btnWrdGameNext);
        btnWordGameAnswer=rootView.findViewById(R.id.btnWrdGameAnswer);
        btnWordGameShowClue=rootView.findViewById(R.id.btnWordGameShowClue);
        return rootView;
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        tvWrdGameDef.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp.getInt(getContext(), SPEditor.TXT_SIZE_WORD_GAME_QUESTION));
        tvWordGameClue.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp.getInt(getContext(), SPEditor.TXT_SIZE_WORD_GAME_CLUE));

        clWordGameMain.setBackgroundColor(Color.BLACK);
        clWordGameScoreMain.getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_PANEL_BG));
        clWordGameLower.getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_PANEL_BG));
        svWordGameCombs.getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_PANEL_BG));
        svWordGameMid.getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_PANEL_BG));

        ivCorrect.getDrawable().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        ivIncorrect.getDrawable().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWrdGameCrrct.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWrdGameIncrrct.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWrdGameScore.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWrdGameWrdPts.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWordGameTotalPtsStr.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));

        tvWordGameWordPtsStr.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWrdGameWrdPts.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWordGameLetterCountStr.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWrdGameWrdPts.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWrdGameLetterCount.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));

        tvWordGameDef.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWordGameKind.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWordGameClue.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));

        tvWordGamePtsToEarnStr.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvWordGamePtsToEarn.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        btnWordGameGetLetter.getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        btnWordGameNext.getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        btnWordGameAnswer.getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        btnWordGameShowClue.getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));

        btnWordGameGetLetter.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_PANEL_BG));
        btnWordGameNext.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_PANEL_BG));
        btnWordGameAnswer.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_PANEL_BG));
        btnWordGameShowClue.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_PANEL_BG));

        etWrdGameAnswr.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        etWrdGameAnswr.getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
        tvInputLetterCount.setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_TXT));


    }

    public LinearLayout getLlWrdGameQstn() {
        return llWrdGameQstn;
    }

    public void setLlWrdGameQstn(LinearLayout llWrdGameQstn) {
        this.llWrdGameQstn = llWrdGameQstn;
    }

    public TextView getTvWrdGameDef() {
        return tvWrdGameDef;
    }

    public void setTvWrdGameDef(TextView tvWrdGameDef) {
        this.tvWrdGameDef = tvWrdGameDef;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStyle();

        gameManager=new GameManager(FragmentWordGame.this, setNameList, qType);
        gameManager.createGame();
        gameManager.startGame();

        btnWordGameGetLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.getLetter();
            }
        });

        btnWordGameNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameManager.getGameScreensList().size()==1) btnWordGameNext.setText("BITIR");
                if(! gameManager.isFinished()) gameManager.nextScreen();
                else {
                    FragmentManager fragManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
                    FragmentTransaction fragTransacion = fragManager.beginTransaction();

                    fragTransacion.add(R.id.containerActivityMain, new FragmentWordGameResult(gameManager.getGameScreensList()));
                    fragTransacion.addToBackStack(null);
                    fragTransacion.commit();
                }
            }
        });

        btnWordGameShowClue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.showClue();
            }
        });

      etWrdGameAnswr.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              tvInputLetterCount.setText(String.valueOf(charSequence.length()));
          }

          @Override
          public void afterTextChanged(Editable editable) {

          }
      });
        btnWordGameAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.checkAnswer(etWrdGameAnswr.getText().toString());
            }
        });
    }

    public EditText getEtWrdGameAnswr() {
        return etWrdGameAnswr;
    }


    public Button getBtnAnswer() {
        return btnWordGameAnswer;
    }

    public Button getBtnCheck() {
        return btnWordGameAnswer;
    }

    public Button getBtnGetLetter() {
        return btnWordGameGetLetter;
    }

    public Button getBtnNext() {
        return btnWordGameNext;
    }


    public TextView getTvWrdGameCrrct() {
        return tvWrdGameCrrct;
    }

    public TextView getTvWrdGameIncrrct() {
        return tvWrdGameIncrrct;
    }

    public TextView getTvWrdGameScore() {
        return tvWrdGameScore;
    }


    public TextView getTvWrdGameWrdPts() {
        return tvWrdGameWrdPts;
    }

    public TextView getTvWrdGameLetterCount() {
        return tvWrdGameLetterCount;
    }

    public TextView getTvWrdGamePtsGain() {
        return tvWrdGamePtsGain;
    }

    public Button getBtnShowClue() {
        return btnWordGameShowClue;
    }

    public TextView getTvWordGameClue() {
        return tvWordGameClue;
    }

    public TextView getTvWordGameKind() {
        return tvWordGameKind;
    }

    public TextView getTvInputLetterCount() {
        return tvInputLetterCount;
    }

    public TextView getTvWordGameWordPtsStr() {
        return tvWordGameWordPtsStr;
    }


}