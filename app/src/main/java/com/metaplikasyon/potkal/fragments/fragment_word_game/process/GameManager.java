package com.metaplikasyon.potkal.fragments.fragment_word_game.process;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_word_game.FragmentWordGame;
import com.metaplikasyon.potkal.fragments.fragment_word_game.views.containers.FlTvWordGameLetterContainer;
import com.metaplikasyon.potkal.fragments.fragment_word_game.views.containers.LlWrdGameClue;
import com.metaplikasyon.potkal.fragments.fragment_word_game.views.textviews.TvWrdGameLetter;
import com.metaplikasyon.potkal.other.PixelConverter;

import java.util.ArrayList;
import java.util.Collections;

public class GameManager {
    private ArrayList<GameScreen> gameScreensList;
    private int qType;
    private FragmentWordGame fragmentWordGame;
    private ArrayList<String> setNameList;
    private Context context;
    private GameCreator gc;
    private LlWrdGameClue llClueCurrent;
    private LinearLayout llClueMain;
    private GameScreen gameScreenCrrnt;
    private int currentScreenNo;
    private String strClue;
    //private ArrayList<TvWrdGameLetter> viewLetterList;
    private ArrayList<FlTvWordGameLetterContainer> viewLetterList;
    int chance=3;
    private PixelConverter pc;

    public GameManager(FragmentWordGame fragmentWordGame, ArrayList<String> setNameList, int qType) {
        this.fragmentWordGame = fragmentWordGame;
        this.setNameList = setNameList;
        context=fragmentWordGame.getContext();
        viewLetterList=new ArrayList<>();
        gc=new GameCreator(context);
        this.qType=qType;
        pc=new PixelConverter(context);

        llClueMain=fragmentWordGame.getView().findViewById(R.id.llWrdGameLetters);
        currentScreenNo=0;
    }

    public void nextScreen() {
        if(currentScreenNo != gameScreensList.size()-1) {
            currentScreenNo++;
            gameScreenCrrnt=gameScreensList.get(currentScreenNo);

            drawScreen();
        }
    }

    public boolean isFinished() {
         boolean result= currentScreenNo==gameScreensList.size()-1 ? true:false;
         return result;
    }

    public void prevScreen() {
        if(currentScreenNo >0) {
            currentScreenNo--;
            gameScreenCrrnt=gameScreensList.get(currentScreenNo);
            drawScreen();
        }
    }

    public void createGame() {
        gameScreensList=gc.createGame(setNameList, qType);
    }

    public void startGame() {
        gameScreenCrrnt=gameScreensList.get(0);
        drawScreen();
    }

    private void drawScreen() {
        cleanScreen();
        if(gameScreenCrrnt.getClue()!=null) {
            fragmentWordGame.getBtnShowClue().setVisibility(View.VISIBLE);
            fragmentWordGame.getTvWordGameClue().setText(gameScreenCrrnt.getClue());
            fragmentWordGame.getTvWordGameClue().setVisibility(View.INVISIBLE);
        } else {
            fragmentWordGame.getBtnShowClue().setVisibility(View.INVISIBLE);
            fragmentWordGame.getTvWordGameClue().setVisibility(View.INVISIBLE);
        }
        fragmentWordGame.getTvWrdGameDef().setText(gameScreensList.get(currentScreenNo).getQuestion());
        fragmentWordGame.getTvWrdGameWrdPts().setText(String.valueOf(gameScreensList.get(currentScreenNo).getPts()));
        fragmentWordGame.getTvWrdGamePtsGain().setText(String.valueOf(gameScreenCrrnt.getPts()));
        fragmentWordGame.getTvWrdGameLetterCount().setText(String.valueOf(gameScreensList.get(currentScreenNo).getAnswer().length()));
        fragmentWordGame.getTvWordGameKind().setText(gameScreenCrrnt.getKind());
        fragmentWordGame.getBtnGetLetter().setText(context.getResources().getString(R.string.get_letter) +"("+String.valueOf(-gameScreenCrrnt.getPtsLetter())+")");
        fragmentWordGame.getBtnCheck().setText(context.getResources().getString(R.string.answer) +"("+String.valueOf(chance)+")");
        strClue=gameScreenCrrnt.getAnswer();

        drawClue();
    }

    private void cleanScreen() {
        llClueMain.removeAllViews();
        chance=3;
        //fragmentWordGame.getBtnCheck().setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));

        fragmentWordGame.getBtnCheck().setEnabled(true);
        //fragmentWordGame.getClWrdGameMid().setBackgroundColor(Color.WHITE);
        fragmentWordGame.getEtWrdGameAnswr().setText("");
        fragmentWordGame.getBtnNext().setVisibility(View.INVISIBLE);
        fragmentWordGame.getBtnNext().setEnabled(false);
        fragmentWordGame.getBtnGetLetter().setEnabled(true);
    }
    private void drawClue() {
        gameScreenCrrnt.setUnShownCnt(gameScreenCrrnt.getAnswer().length());
        if(gameScreenCrrnt.getUnShownCnt()>0) fragmentWordGame.getBtnGetLetter().setEnabled(true);
        llClueCurrent =new LlWrdGameClue(context);
        llClueMain.addView(llClueCurrent);

        llClueCurrent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llClueCurrent.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int wdtOccupied = 0;
                int widthLlCurrent=llClueCurrent.getMeasuredWidth();

                for(int i=0;i<strClue.length();i++) {
                    FlTvWordGameLetterContainer flTvWordGameLetterContainer
                            =new FlTvWordGameLetterContainer(context,strClue.charAt(i),gameScreenCrrnt );

                    viewLetterList.add(flTvWordGameLetterContainer);
                    flTvWordGameLetterContainer.measure
                            (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    //wdtOccupied += tv.getMeasuredWidth()+10;
                    wdtOccupied += flTvWordGameLetterContainer.getMeasuredWidth()+ pc.dp2Px(15);

                    if (wdtOccupied >= widthLlCurrent) {
                        llClueCurrent = new LlWrdGameClue(context);
                        wdtOccupied = flTvWordGameLetterContainer.getMeasuredWidth();
                        llClueMain.addView(llClueCurrent);
                    }
                    llClueCurrent.addView(flTvWordGameLetterContainer);
                }
            }});
    }

    public void checkAnswer(String answer) {
        answer=answer.replaceAll("I","ı");
        answer=answer.replaceAll("İ","i");

        if(answer.toLowerCase().equals(gameScreenCrrnt.getAnswer().toLowerCase())) {
            correct();
            //fragmentWordGame.getClWrdGameMid().setBackgroundColor(Color.GREEN);
        } else {
            chance--;
            if(chance==0) {
                inCorrect();
            }
            fragmentWordGame.getBtnCheck().setText(context.getResources().getString(R.string.answer) +"("+String.valueOf(chance)+")");
            tryAgain();
        }

        if(currentScreenNo==gameScreensList.size()-1)  {
            fragmentWordGame.getBtnNext().setText("Bitir");
        }
        //fragmentWordGame.getBtnAnswer().setClickable(false);
    }

    private void showAllLetters(boolean isCorrect) {

        Animation animTrue = AnimationUtils.loadAnimation(context, R.anim.bounce);
        Animation animFalse = AnimationUtils.loadAnimation(context, R.anim.shaking);
        Animation upAndDown = AnimationUtils.loadAnimation(context, R.anim.up_and_down);
        Animation blink = AnimationUtils.loadAnimation(context, R.anim.blink);

        AnimatorSet as = new AnimatorSet();

        for(int i=0;i<viewLetterList.size();i++) {
            if(! viewLetterList.get(i).getTvWrdGameLetter().isShown())
                viewLetterList.get(i).getTvWrdGameLetter().showLetter();
            if(isCorrect) {
                viewLetterList.get(i).startAnimation(blink);
            }
            else viewLetterList.get(i).startAnimation(animFalse);
        }
        fragmentWordGame.getBtnGetLetter().setEnabled(false);
    }

    public void getLetter() {
        while(true && gameScreenCrrnt.getUnShownCnt()>0) {
            Collections.shuffle(viewLetterList);
            if(viewLetterList.get(0).getTvWrdGameLetter().isShown()==false) {

                animate(viewLetterList.get(0).getTvWrdGameLetter());
                viewLetterList.get(0).getTvWrdGameLetter().showLetter();
                gameScreenCrrnt.setUnShownCnt(gameScreenCrrnt.getUnShownCnt()-1);
                gameScreenCrrnt.setShownLetterCnt(gameScreenCrrnt.getShownLetterCnt()+1);
                int ptsGain= Integer.parseInt(fragmentWordGame.getTvWrdGamePtsGain().getText().toString());

                fragmentWordGame.getTvWrdGamePtsGain().setText(String.valueOf(ptsGain-gameScreenCrrnt.getPtsLetter()));

                if(gameScreenCrrnt.getUnShownCnt()==0) {
                    fragmentWordGame.getBtnCheck().setEnabled(false);
                    fragmentWordGame.getBtnGetLetter().setEnabled(false);
                    fragmentWordGame.getBtnNext().setEnabled(true);
                    fragmentWordGame.getBtnNext().setVisibility(View.VISIBLE);
                    gameScreenCrrnt.setAnsweredAs(0);
                    fragmentWordGame.getBtnNext().setText("BITIR");
                }
                break;
            }
        }
    }

    private void correct() {
        Button btn=fragmentWordGame.getBtnCheck();
        Animation scale = AnimationUtils.loadAnimation(context, R.anim.scale_1_04);
        btn.startAnimation(scale);

        showAllLetters(true);

        int correct=Integer.parseInt(fragmentWordGame.getTvWrdGameCrrct().getText().toString());
        int score= Integer.parseInt(fragmentWordGame.getTvWrdGameScore().getText().toString());
        gameScreenCrrnt.setAnsweredAs(1);
        fragmentWordGame.getTvWrdGameCrrct().setText(String.valueOf(correct+1));
        fragmentWordGame.getTvWrdGameScore().setText(String.valueOf(score+Integer.parseInt(fragmentWordGame.getTvWrdGamePtsGain().getText().toString())));
        gameScreenCrrnt.setPtsGained(Integer.parseInt(fragmentWordGame.getTvWrdGamePtsGain().getText().toString()));
        fragmentWordGame.getBtnNext().setVisibility(View.VISIBLE);
        fragmentWordGame.getBtnNext().setEnabled(true);
        fragmentWordGame.getBtnCheck().setEnabled(false);
    }

    private static final int RED = 0xffFF8080;
    private static final int WHITE = Color.WHITE;
    private void tryAgain() {
        //ConstraintLayout cl=fragmentWordGame.getClWrdGameMid();
        Button btn=fragmentWordGame.getBtnCheck();
        Animation shaking = AnimationUtils.loadAnimation(context, R.anim.shaking);
        btn.startAnimation(shaking);
    }

    private void inCorrect() {
        showAllLetters(false);
        //fragmentWordGame.getBtnCheck().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        //fragmentWordGame.getClWrdGameMid().setBackgroundColor(Color.RED);
        int inCorrect=Integer.parseInt(fragmentWordGame.getTvWrdGameIncrrct().getText().toString());
        int score= Integer.parseInt(fragmentWordGame.getTvWrdGameScore().getText().toString());
        gameScreenCrrnt.setAnsweredAs(-1);
        gameScreenCrrnt.setPtsGained(-Integer.parseInt(fragmentWordGame.getTvWrdGamePtsGain().getText().toString()));
        fragmentWordGame.getTvWrdGameIncrrct().setText(String.valueOf(inCorrect+1));
       // fragmentWordGame.getTvWrdGameScore().setText(String.valueOf(score-Integer.parseInt(fragmentWordGame.getTvWrdGamePtsGain().getText().toString())));
        fragmentWordGame.getTvWrdGameScore().setText(String.valueOf(score-gameScreenCrrnt.getPts()));
        fragmentWordGame.getBtnNext().setVisibility(View.VISIBLE);
        fragmentWordGame.getBtnNext().setEnabled(true);
        fragmentWordGame.getBtnCheck().setEnabled(false);
    }

    public void showClue() {
        fragmentWordGame.getTvWordGameClue().setVisibility(View.VISIBLE);
    }

    public ArrayList<GameScreen> getGameScreensList() {
        return gameScreensList;
    }

    private void blink(ConstraintLayout cl){
        final Handler handler = new Handler(Looper.getMainLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 100;    //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if( ((ColorDrawable)cl.getBackground()).getColor()==Color.RED) cl.setBackgroundColor(Color.WHITE);
                        else cl.setBackgroundColor(Color.RED);
                        blink(cl);
                    }
                });
            }
        }).start();
    }

    private void animate(TvWrdGameLetter v) {
        Animation blink = AnimationUtils.loadAnimation(context, R.anim.blink);
        v.startAnimation(blink);
    }

}