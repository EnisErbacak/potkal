package com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.FragmentTest;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process.question_creator.TestPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Class that binds the texts to the buttons and text views.
public class TestScreen {
    private Context context;
    private int questionNo;
    private final ConstraintLayout clTestMain;
    private final TextView tvQ;

    private final TestPool testPool;
    private final FragmentTest fragmentTest;
    private final String question;
    private final int screenNo;
    private final Choice[] choiceArr;
    public static ArrayList<String> incorrectList;


    public TestScreen(ConstraintLayout clTestMain, TestPool testPool, int questionNo, FragmentTest fragmentTest) {
        context=clTestMain.getContext();
        this.clTestMain=clTestMain;
        this.testPool=testPool;
        this.questionNo=questionNo;
        this.fragmentTest=fragmentTest;
        screenNo=questionNo;
        question=testPool.getqList().get(questionNo);
        tvQ = clTestMain.findViewById(R.id.tvTestQuestion);
        incorrectList=new ArrayList<>();

        choiceArr=new Choice[]{new Choice(TestScreen.this, clTestMain.getContext())
                , new Choice(TestScreen.this, clTestMain.getContext())
                , new Choice(TestScreen.this, clTestMain.getContext())
                , new Choice(TestScreen.this, clTestMain.getContext())};

        this.questionNo=questionNo;

        onCreate();
    }

    public void show() {
        reDraw();
    }

    private void onCreate() {
        tvQ.setText(testPool.getqList().get(questionNo));

        generateRandoms2(testPool.getChoiceList().size()-1);

        attachChoiceStr();
        setChoiceBtnNo();

        setCorrectChoice();

        attachBtn2Choice();
    }

    // Generates random numbers as count passed by in a range.
    public void generateRandoms2(int max) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<=max; i++) {
            if(i==questionNo) continue;
            list.add(i);
        }

        Collections.shuffle(list);
        for (int i=0; i<choiceArr.length; i++) {
            choiceArr[i].setDefNo(list.get(i));
        }
    }

    // Generates random number in a range.
    private int getRandom(int max) {
        return new Random().nextInt(max+1);
    }

    // Sets the buttons texts.
    private void attachChoiceStr(){
        for(Choice ch:choiceArr) {
            ch.setStr(testPool.getChoiceList().get(ch.getDefNo()));
        }
    }

    // Makes placements of the the buttons
    private void setChoiceBtnNo() {
        for(int i=0;i<choiceArr.length;i++) {
            choiceArr[i].setBtnNo(i);
        }
    }

    // Selects a random number and sets it as a correct choice
    private void setCorrectChoice() {
        int randomInt=getRandom(3);
        choiceArr[randomInt].setStr(testPool.getChoiceList().get(questionNo));
        choiceArr[randomInt].setCorrect(true);
    }

    // Attaches choice objects to the buttons.
    private void attachBtn2Choice() {
        for(Choice ch:choiceArr) {
            fragmentTest.getBtnChArr()[ch.getBtnNo()].setChoice(ch);
            ch.setTvScoreCorrect(fragmentTest.getTvTestScoreCorrect());
            ch.setTvScoreIncorrect(fragmentTest.getTvTestScoreIncorrect());
            ch.setButtonChoice(fragmentTest.getBtnChArr()[ch.getBtnNo()]);
        }
    }

    // Marks the touched buttons red or green.
    protected void reDraw() {
        tvQ.setText(testPool.getqList().get(questionNo));
        for(Choice ch:choiceArr) {
            ch.draw();
        }
    }

    // Makes the other buttons unselectable/touchable if a button touched/selected.
    public void makeAllUnselectable() {
        for(Choice ch: choiceArr) {
            //ch.setSelected(true);
            ch.setSelectable(false);
        }
    }

    protected void showCorrect(){
        Animation blink = AnimationUtils.loadAnimation(context, R.anim.blink);
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shaking);
        for(Choice ch: choiceArr) {
            //ch.setSelected(true);
            ch.setSelectable(false);
            if(ch.isCorrect()) {
                ch.setSelected(true);
                ch.getButtonChoice().startAnimation(blink);
            }
            ch.draw();
        }
    }

    public static ArrayList<String> getIncorrectList() {
        return incorrectList;
    }

    public Choice[] getChoiceArr() {
        return choiceArr;
    }

    public String getQuestion() {
        return question;
    }
}