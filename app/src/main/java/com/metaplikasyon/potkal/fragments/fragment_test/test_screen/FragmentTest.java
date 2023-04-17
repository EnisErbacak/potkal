package com.metaplikasyon.potkal.fragments.fragment_test.test_screen;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_test.FragmentListenerBackPress;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.FragmentTestFirst;
import com.metaplikasyon.potkal.fragments.fragment_test.result.FragmentTestResult;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.listener.OnSwipeTouchListener;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process.question_creator.QuestionCreator;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process.question_creator.TestPool;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process.TestManager;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.views.buttons.ButtonChoice;
import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.views.BtnDlg;

import java.util.ArrayList;

public class FragmentTest extends Fragment {
    private SPEditor spEditor;
    private final ArrayList<String> setNames;
    private QuestionCreator questionCreator;
    private Button btnFinishTest;
    private ConstraintLayout clTestMain, clTestScore;
    LinearLayout llTestChoice;
    private BtnDlg btnTestPrev, btnTestNext;

    private ScrollView svTestChoice;
    TestPool testPool;
    ButtonChoice[] btnChArr;
    private TextView tvTestScoreCorrect, tvTestScoreIncorrect, tvTestQuestion;
    private ImageView ivTestScoreCorrect, ivTestScoreIncorrect;
    private final int questionChoiceType;

    public FragmentTest(ArrayList<String> setNames, int questionChoiceType) {
        this.setNames=setNames;
        this.questionChoiceType=questionChoiceType;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCondition(getView());
        spEditor=new SPEditor();

        clTestMain=view.findViewById(R.id.clTestMain);
        clTestScore=view.findViewById(R.id.clTestScore);
        svTestChoice=view.findViewById(R.id.svTestChoice);

        tvTestScoreCorrect=view.findViewById(R.id.tvTestScoreCorrect);
        tvTestScoreIncorrect=view.findViewById(R.id.tvTestScoreIncorrect);

        tvTestQuestion=view.findViewById(R.id.tvTestQuestion);
        tvTestQuestion.setMovementMethod(new ScrollingMovementMethod());

        btnTestPrev=view.findViewById(R.id.btnTestPrev);
        btnTestNext=view.findViewById(R.id.btnTestNext);

        btnFinishTest=view.findViewById(R.id.btnTestFinish);

        ivTestScoreCorrect=view.findViewById(R.id.imageView);
        ivTestScoreIncorrect=view.findViewById(R.id.imageView2);


        ButtonChoice btnCh1=view.findViewById(R.id.btnTestCh1);
        ButtonChoice btnCh2=view.findViewById(R.id.btnTestCh2);
        ButtonChoice btnCh3=view.findViewById(R.id.btnTestCh3);
        ButtonChoice btnCh4=view.findViewById(R.id.btnTestCh4);

        btnChArr=new ButtonChoice[]{btnCh1, btnCh2, btnCh3, btnCh4};

        questionCreator=new QuestionCreator(getActivity().getApplicationContext(), questionChoiceType);
        testPool = questionCreator.createTestPool(setNames);
        TestManager testManager=new TestManager(getContext(), setNames, testPool, clTestMain, FragmentTest.this);
        testManager.createTest();
        testManager.startTest();

        setMovement(getContext(), spEditor, testManager, btnTestPrev, btnTestNext);

        btnFinishTest.setVisibility(View.GONE);
        btnFinishTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerActivityMain, new FragmentTestResult(tvTestScoreCorrect.getText().toString(), tvTestScoreIncorrect.getText().toString()))
                        .commit();
            }
        });

        setStyle();
    }

    private void setMovement(Context context,SPEditor spEditor,TestManager tm, Button btnPrev, Button btnNext) {
        if(spEditor.getString(getContext(), SPEditor.TEST_BUTTONS).equals("on")) {
            btnTestPrev.setVisibility(View.VISIBLE);
            btnTestNext.setVisibility(View.VISIBLE);
            btnTestPrev.setEnabled(true);
            btnTestNext.setEnabled(true);

            btnTestPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tm.prevScreen();
                }
            });

            btnTestNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tm.nextScreen();
                }
            });

        }else {
            btnNext.setVisibility(View.INVISIBLE);
            btnNext.setEnabled(false);
            btnPrev.setVisibility(View.INVISIBLE);
            btnPrev.setEnabled(false);
            setSwing(context,tm);
        }
    }

    public ButtonChoice[] getBtnChArr() {
        return btnChArr;
    }


    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new FragmentListenerBackPress(FragmentTest.this, new FragmentTestFirst()));
    }

    private void setSwing(Context context, TestManager testManager) {
        clTestMain.setOnTouchListener(new OnSwipeTouchListener(context) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {
                testManager.prevScreen();
            }
            public void onSwipeLeft() {
                testManager.nextScreen();
            }
            public void onSwipeBottom() {
            }

        });
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        Drawable panelDrawable = ContextCompat.getDrawable(getContext(),R.drawable.bg_panel_rounded);
//        panelDrawable.setColorFilter(sp.getInt(getContext(),SPEditor.COL_TEST_PANEL_BG)
//                , PorterDuff.Mode.SRC_ATOP);
        panelDrawable.setTint(sp.getInt(getContext(),SPEditor.COL_TEST_PANEL_BG));

        tvTestQuestion.setTextColor(sp.getInt(getContext(), SPEditor.COL_TEST_TXT));
        tvTestScoreCorrect.setTextColor(sp.getInt(getContext(), SPEditor.COL_TEST_TXT));
        tvTestScoreIncorrect.setTextColor(sp.getInt(getContext(), SPEditor.COL_TEST_TXT));


        clTestMain.setBackgroundColor(sp.getInt(getContext(),SPEditor.COL_TEST_BG));

        tvTestQuestion.setBackground(panelDrawable);
        clTestScore.setBackground(panelDrawable);

        svTestChoice.getBackground().setTint(sp.getInt(getContext(),SPEditor.COL_TEST_PANEL_BG));


        Drawable roundDrawable = ContextCompat.getDrawable(getContext(),R.drawable.bg_dialog_custom_btn);
//        roundDrawable.setColorFilter(sp.getInt(getContext(),SPEditor.COL_TEST_PANEL_BG)
//                , PorterDuff.Mode.SRC_ATOP);
        roundDrawable.setTint(sp.getInt(getContext(),SPEditor.COL_TEST_PANEL_BG));

        btnFinishTest.setBackground(roundDrawable);
        btnFinishTest.setTextColor(sp.getInt(getContext(), SPEditor.COL_TEST_TXT));

        btnTestNext.setBackground(roundDrawable);
        btnTestPrev.setBackground(roundDrawable);

        btnTestNext.setTextColor(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));
        btnTestPrev.setTextColor(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));

        ivTestScoreCorrect.getDrawable().setTint(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));
        ivTestScoreIncorrect.getDrawable().setTint(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));
    }



    public TextView getTvTestScoreCorrect() {
        return tvTestScoreCorrect;
    }

    public TextView getTvTestScoreIncorrect() {
        return tvTestScoreIncorrect;
    }
}
