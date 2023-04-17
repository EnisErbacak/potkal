package com.metaplikasyon.potkal.fragments.fragment_test.result;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.textviews.TvTestFirstSetName;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process.TestManager;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process.TestScreen;

import java.util.ArrayList;

public class FragmentTestResult extends Fragment {
    public static final String TAG="test_result";
    private final String correct;
    private final String inCorrect;
    private ArrayList<String> sets;
    private ConstraintLayout clMainTestResult,clTestResUpper, clTestResLower;

    private LinearLayout llTestResInner;
    private TextView tvTestResultIncorrect, tvTestResultCorrect, tvTestResultCorrectStr
            , tvTestResultIncorrectStr,tvTestResultMistakes;
    private Button btnTestExit;
    private TestManager testManager;

    public FragmentTestResult(String correct, String inCorrect /* ArrayList<String> sets*/) {
        this.correct = correct;
        this.inCorrect = inCorrect;
        this.sets = TestScreen.getIncorrectList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_test_result,container,false);
        clMainTestResult=rootView.findViewById(R.id.clMainTestResult);
        llTestResInner=rootView.findViewById(R.id.llTestResInner);
        clTestResLower=rootView.findViewById(R.id.clWordGameResLower);
        clTestResUpper=rootView.findViewById(R.id.clTestResUpper);
        tvTestResultCorrect =rootView.findViewById(R.id.tvTestResultCorrect);
        tvTestResultIncorrect =rootView.findViewById(R.id.tvTestResultIncorrect);
        tvTestResultCorrectStr=rootView.findViewById(R.id.tvWordGameResCorrectStr);
        tvTestResultIncorrectStr=rootView.findViewById(R.id.tvTestResultIncorrectStr);
        tvTestResultMistakes=rootView.findViewById(R.id.tvTestResultMistakes);



        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                5,
                getResources().getDisplayMetrics());

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                        , (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        1,
                        getResources().getDisplayMetrics()));

        SPEditor sp=new SPEditor();
        if(sets.size()!=0) {
            for(int i=0;i<sets.size();i++) { //String str: sets) {
                llTestResInner.addView(new TvTestFirstSetName(getContext(),sets.get(i)));

                if(i==sets.size()-1)
                    continue;
                View v = new View(getContext());
                v.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1
                ));
                v.setBackgroundColor(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));
                llTestResInner.addView(v);
            }
        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTestResultIncorrect =view.getRootView().findViewById(R.id.tvTestResultIncorrect);
        tvTestResultCorrect =view.getRootView().findViewById(R.id.tvTestResultCorrect);
        btnTestExit=view.getRootView().findViewById(R.id.btnTestExit);

        btnTestExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                FragmentTransaction fragTransacion = fm.beginTransaction();
                //fragTransacion.add(R.id.containerActivityMain, FragmentWordSet.getInstance());
                fragTransacion.commit();

                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        tvTestResultCorrect.setText(correct);
        tvTestResultIncorrect.setText(inCorrect);
        setStyle();
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();

        clMainTestResult.setBackgroundColor(sp.getInt(getContext(), SPEditor.COL_TEST_BG));

        clTestResLower.getBackground().setTint(sp.getInt(getContext(),SPEditor.COL_TEST_PANEL_BG));
        clTestResUpper.getBackground().setTint(sp.getInt(getContext(),SPEditor.COL_TEST_PANEL_BG));


        btnTestExit.getBackground().setTint(sp.getInt(getContext(),SPEditor.COL_TEST_PANEL_BG));
        btnTestExit.setTextColor(sp.getInt(getContext(), SPEditor.COL_TEST_TXT));

        tvTestResultCorrect.setTextColor(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));
        tvTestResultIncorrect.setTextColor(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));
        tvTestResultCorrectStr.setTextColor(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));
        tvTestResultIncorrectStr.setTextColor(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));
        tvTestResultMistakes.setTextColor(sp.getInt(getContext(),SPEditor.COL_TEST_TXT));


        for(int i=0;i<llTestResInner.getChildCount();i++){
            if(llTestResInner.getChildAt(i) instanceof TextView) {
                TextView tv = (TextView) llTestResInner.getChildAt(i);
                tv.setTextColor(sp.getInt(getContext(), SPEditor.COL_TEST_TXT));
            }
        }

    }


}