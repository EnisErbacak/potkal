package com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.views.buttons.ButtonChoice;

public class Choice {
    Context context;
    private String str;
    private int btnNo, defNo;
    private boolean isSelected, isCorrect, isSelectable=true;
    private ButtonChoice buttonChoice;
    private TextView tvScoreCorrect, tvScoreIncorrect;
    private final TestScreen testScreen;
    private Drawable btnCorrect, btnIncorrect;

    public Choice(TestScreen testScreen, Context context) {
        this.context=context;
        this.defNo=-1;
        this.testScreen=testScreen;
        btnCorrect=ContextCompat.getDrawable(context,R.drawable.bg_dialog_custom_btn);
        btnIncorrect=ContextCompat.getDrawable(context,R.drawable.bg_dialog_custom_btn);
    }

    public void setButtonChoice(ButtonChoice buttonChoice) {
        this.buttonChoice = buttonChoice;
    }

    public ButtonChoice getButtonChoice() {
        return buttonChoice;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getBtnNo() {
        return btnNo;
    }

    public void setBtnNo(int btnNo) {
        this.btnNo = btnNo;
    }

    public int getDefNo() {
        return defNo;
    }

    public void setDefNo(int defNo) {
        this.defNo = defNo;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public void setTvScoreCorrect(TextView tvScoreCorrect) {
        this.tvScoreCorrect = tvScoreCorrect;
    }

    public void setTvScoreIncorrect(TextView tvScoreIncorrect) {
        this.tvScoreIncorrect = tvScoreIncorrect;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }

    public void mark() {
        Animation scale = AnimationUtils.loadAnimation(context, R.anim.scale_1_04);
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shaking);
        SPEditor sp=new SPEditor();
            if (isSelected()) {
                if (isCorrect()) {
                    buttonChoice.setTextColor(sp.getInt(context, SPEditor.COL_TEST_CHOICE_CORRECT));
                }
                else {
                    buttonChoice.setTextColor(sp.getInt(context, SPEditor.COL_TEST_CHOICE_INCORRECT));
                }
            }
    }

    public void select() {
        if(isSelectable()) {
            testScreen.makeAllUnselectable();
            setSelected(true);
            Animation scale = AnimationUtils.loadAnimation(context, R.anim.scale_1_04);
            Animation shake = AnimationUtils.loadAnimation(context, R.anim.shaking);
            if (isCorrect) {
                testScreen.reDraw();
                tvScoreCorrect.setText(String.valueOf(Integer.parseInt(tvScoreCorrect.getText().toString()) + 1));
            } else {
                testScreen.reDraw();
                testScreen.getIncorrectList().add(testScreen.getQuestion());
                tvScoreIncorrect.setText(String.valueOf(Integer.parseInt(tvScoreIncorrect.getText().toString()) + 1));
            }
            testScreen.showCorrect();
        }
    }

    public void draw() {
        SPEditor sp=new SPEditor();
        buttonChoice.setChoice(Choice.this);
        buttonChoice.setText(str);

        buttonChoice.setTextColor(sp.getInt(context, SPEditor.COL_TEST_CHOICE_TXT));

        Drawable roundDrawable = ContextCompat.getDrawable(context,R.drawable.bg_dialog_custom_btn);
//        roundDrawable.setColorFilter(sp.getInt(context,SPEditor.COL_TEST_CHOICE_BG)
//                , PorterDuff.Mode.SRC_ATOP);
        roundDrawable.setTint(sp.getInt(context,SPEditor.COL_TEST_CHOICE_BG));


        buttonChoice.setBackground(roundDrawable);

        mark();
    }

    private void setCorrect(Drawable drawable) {

    }

    private void setIncorrect(Drawable drawable) {

    }
}