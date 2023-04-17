package com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process;

import android.content.Context;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.FragmentTest;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process.question_creator.TestPool;

import java.util.ArrayList;
import java.util.Collections;

public class TestManager {
    ArrayList<TestScreen> testScreenList;
    private int crntScrnNo=0;
    private final ArrayList<String> wordList;
    private final Context context;
    private final TestPool testPool;
    private final ConstraintLayout clTestMain;
    private final FragmentTest fragmentTest;

    public TestManager(Context context, ArrayList<String> setNameList, TestPool testPool, ConstraintLayout clTestMain, FragmentTest fragmentTest) {
        this.context = context;
        this.clTestMain=clTestMain;
        testScreenList=new ArrayList<>();
        this.testPool=testPool;
        wordList=testPool.getqList();
        this.fragmentTest=fragmentTest;
    }

    public void startTest() {
        testScreenList.get(0).show();
        for(TestScreen ts: testScreenList) {
            System.out.println("--------");
            System.out.println(ts.getQuestion());
            for(Choice ch: ts.getChoiceArr()) {
                System.out.print(ch.getBtnNo()+"__"+ch.getStr()+"  ");
            }
            System.out.println("--------");
        }
    }

    public void nextScreen() {
        System.out.println("************");
        if(crntScrnNo<=testScreenList.size()-2) {
            testScreenList.get(crntScrnNo+1).show();
            if(crntScrnNo+1 ==testScreenList.size()-1) {
                fragmentTest.getView().findViewById(R.id.btnTestFinish).setEnabled(true);
                fragmentTest.getView().findViewById(R.id.btnTestFinish).setVisibility(View.VISIBLE);
            }
            crntScrnNo++;
        }
    }

    public void prevScreen() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<");
        if(crntScrnNo>=1) {
            testScreenList.get(crntScrnNo-1).show();
            crntScrnNo--;
        }
    }

    public void createTest() {
        for(int i=0;i<wordList.size();i++) {
            testScreenList.add(new TestScreen(clTestMain, testPool, i, fragmentTest));
        }
        Collections.shuffle(testScreenList);
    }
}
