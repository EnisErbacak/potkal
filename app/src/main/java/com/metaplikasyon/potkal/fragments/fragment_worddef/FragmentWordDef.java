package com.metaplikasyon.potkal.fragments.fragment_worddef;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    private FloatingActionButton btnWordDefAddWord;
    private SimpleRecyclerAdapter simpleRecyclerAdapter;
    public static int ORDER_BY=0;

    public static String setName;

    private ArrayList<Word> words;

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

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void updateUi() {
        simpleRecyclerAdapter.notifyDataSetChanged();
    }
}