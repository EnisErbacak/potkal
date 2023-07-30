package com.metaplikasyon.potkal.fragments.fragment_worddef;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.adapters.SimpleRecyclerAdapter;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.Word;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.operator.WordOperator;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.UiBuilderSimple;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.operator.BuilderEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.FragmentDialogAddWrdDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.btn.BtnSrchWrd;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed.ClContainerUpper;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed.LlContainerLower;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed.LlContainerMain;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.edit_text.EtSrchWrd;
import com.metaplikasyon.potkal.fragments.fragment_wordset.manager.WordsetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentWordDef extends Fragment  {
    //********REFACTORED
    private TextView tvWrdDefTop;
    private ConstraintLayout clMainWorddef;
    private ScrollView svMainWorddef;
    private TextView tvWorddefTop;
    private BtnSrchWrd btnSrchWrd;
    private EtSrchWrd etSrchWrd;
    private LinearLayout pnlWordDefVrt;

    private FloatingActionButton btnWordDefAddWord, btnSearchWord, btnWordDefOpt, btnWordDefSrt;
    private LinearLayoutCompat llWordDefBtnContainer;
    private static SimpleRecyclerAdapter simpleRecyclerAdapter;
    public static int ORDER_BY=0;

    public static String setName;

    private static ArrayList<Word> words;

    public FragmentWordDef(String setName)
    {
        FragmentWordDef.setName =setName;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCondition(getView());
        Context context=getContext();
//        new BuilderEditor().getUiEditor(getContext(), setName).updateScreen();

        RecyclerView recyclerView = view.findViewById(R.id.rvWordDef);

        UiBuilderSimple uiBuilderSimple = new UiBuilderSimple(context, setName);
        JSONObject jObj=new WordsetManager().operate(context).get(setName);
        ArrayList<String> keys= uiBuilderSimple.getKeyList(jObj.keys(), false);
        words = getWordList(keys, jObj);


        simpleRecyclerAdapter = new SimpleRecyclerAdapter(words);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(simpleRecyclerAdapter);

//        pnlWordDefVrt=view.findViewById(R.id.pnlWordDefVrt);
//        etSrchWrd=view.findViewById(R.id.etSrchWrd);
//        etSrchWrd.setPnlWordDefVrt(pnlWordDefVrt);
//        btnSrchWrd=view.findViewById(R.id.btnSearchWord);
//        btnSrchWrd.setEtSrchWrd(etSrchWrd);


        tvWrdDefTop=getView().findViewById(R.id.tvWrdDefTop);
        tvWrdDefTop.setText(setName);

        btnWordDefAddWord = getView().findViewById(R.id.btnWordDefAddWord);
        btnWordDefAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FragmentDialogAddWrdDef(FragmentWordDef.setName
                        , FragmentWordDef.this).show(((FragmentActivity)context)
                        .getSupportFragmentManager(), "ADD NEW WORD");
            }
        });

        btnSearchWord = getView().findViewById(R.id.btnSearchWord);
        btnWordDefOpt = getView().findViewById(R.id.btnWordDefOpt);
        btnWordDefSrt = getView().findViewById(R.id.btnWordDefSrt);

        llWordDefBtnContainer = getView().findViewById(R.id.llWordDefBtnContainer);
        llWordDefBtnContainer.setOnTouchListener(new SwipeTouchListener(view.getContext(), llWordDefBtnContainer));


//        clMainWorddef=view.findViewById(R.id.clMainWorddef);
//        svMainWorddef=view.findViewById(R.id.svMainWorddef);
//        setStyle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_word_def_new,container,false);
    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new KeyLstnrFragWrd());
    }

    public void setStyle() {
        Context context=getContext();
        SPEditor spEditor=new SPEditor();
        tvWrdDefTop.setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_WORDDEF_TOPBAR_TXT));
        svMainWorddef.setBackgroundColor(spEditor.getInt(context, SPEditor.COL_WORDDEF_BG));
        clMainWorddef.setBackgroundColor(spEditor.getInt(context, SPEditor.COL_WORDDEF_STATUSBAR));
    }

    @Override
    public void onDestroy() {
        setName=null;
        super.onDestroy();
    }

    private class KeyLstnrFragWrd implements View.OnKeyListener {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            if(keyEvent.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                int count =((FragmentActivity)getContext()).getSupportFragmentManager().getBackStackEntryCount(); //.getBackStackEntryCount();
                ((FragmentActivity)getContext()).getSupportFragmentManager().getFragments().get(count-1).onResume();
                ((FragmentActivity)getContext()).getSupportFragmentManager().popBackStackImmediate();
                return true;
            }
            return false;
        }
    }

    private ArrayList<Word> getWordList(ArrayList<String> keys, JSONObject jObj) {
        ArrayList<Word> wordList = new ArrayList<>();
        WordOperator wordOperator=new WordOperator();
        for(int i=0;i<keys.size();i++) {
            try {
                wordList.add(wordOperator.convert2Word(jObj.getJSONObject(keys.get(i)), keys.get(i)));
            }catch (JSONException je) {
                je.printStackTrace();
                break;
            }
        }
        return wordList;
    }

    public static ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public static void updateUi() {

        if(simpleRecyclerAdapter != null ) {
            simpleRecyclerAdapter.notifyDataSetChanged();
        }
    }




    class SwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        private final int SWIPE_THRESHOLD = 15;
        private final int SWIPE_MIN_DISTANCE = 12;
        private final int SWIPE_MAX_OFF_PATH = 25;
        private final int SWIPE_THRESHOLD_VELOCITY = 20;
        private final LinearLayoutCompat ll;
        public SwipeTouchListener(Context context, LinearLayoutCompat ll){
            gestureDetector = new GestureDetector(context, new GestureListener());
            this.ll = ll;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if ((e1.getAction() == MotionEvent.ACTION_DOWN) &&
                        (e2.getAction() == MotionEvent.ACTION_MOVE) &&
                        Math.abs(distanceX) > SWIPE_THRESHOLD) {

                    if (e2.getPointerCount() > 1) {
                        if (distanceX > 0)
                            onTwoFingerSwipeLeft();
                        else
                            onTwoFingerSwipeRight();
                        return true;
                    }

                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {

                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH
                            || Math.abs(velocityY) < SWIPE_THRESHOLD_VELOCITY)
                        return false;
                    if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE)
                        onSwipeUp();
                    else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE)
                        onSwipeDown();
                } else {
                    if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY)
                        return false;
                    if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE)
                        onSwipeLeft();
                    else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE)
                        onSwipeRight();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }

        }

        public void onSwipeRight() {}

        public void onSwipeLeft() {
            btnSearchWord.setVisibility(View.VISIBLE);
            btnWordDefOpt.setVisibility(View.VISIBLE);
            btnWordDefSrt.setVisibility(View.VISIBLE);

            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnSearchWord.setVisibility(View.GONE);
                    btnWordDefOpt.setVisibility(View.GONE);
                    btnWordDefSrt.setVisibility(View.GONE);
                }
            }, 2000);

        }
    }

        public void onSwipeUp() {}

        public void onSwipeDown() {}

        public void onTwoFingerSwipeLeft() {}

        public void onTwoFingerSwipeRight() {}
}