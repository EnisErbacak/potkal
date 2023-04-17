package com.metaplikasyon.potkal.fragments.fragment_settings;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.containers.ClSpinnerContainer;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.containers.ClSwitchContainer;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.containers.LlSubContainer;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.containers.LlMainContainer;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.switch_button.SwitchTestMovement;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.switch_button.SwitchWordDefDialog;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.switch_button.SwitchWordDefStyle;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.textview.TvGroupTitle;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.textview.TvSubTitle;
import com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.FragmentSettingsColor;
import com.metaplikasyon.potkal.fragments.fragment_settings.spinners.txt_size.SpnrTxtSize;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.metaplikasyon.potkal.other.PixelConverter;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentSettings extends Fragment {

    LinearLayout llSettings;

    ExpandableListView expandableListView, subExpandableListView;

    HashMap<String, ArrayList<ConstraintLayout>> mainList;
    ArrayList<String> titles;
    ExpandableListView elvFragmentSettings;
    TextView tvSettingsColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_settings, container,false);
        llSettings=rootView.findViewById(R.id.llSettings);
        llSettings.setBackgroundColor(new SPEditor().getInt(getContext(), SPEditor.COL_SETTINGS_BG));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setCondition(getView());

        tvSettingsColor=new TvGroupTitle(getContext(), getContext().getResources().getString(R.string.color));
        tvSettingsColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragManager = ((FragmentActivity)view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragTransacion = fragManager.beginTransaction();

                fragTransacion.add(R.id.containerActivityMain, new FragmentSettingsColor());
                fragTransacion.addToBackStack(null);

                fragTransacion.commit();
            }
        });

        addChildren(getContext());
        setStyle();
    }

    @Override
    public void onResume() {
        super.onResume();
        setCondition(getView());
    }

    @Override
    public void onPause() {
        super.onPause();
        getView().setFocusableInTouchMode(false);
        getView().clearFocus();

        getView().setOnKeyListener(null);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new KeyLstnrFragWrd());
    }

    private void setStyle() {
//        expandableListView.setBackgroundColor(Integer.parseInt(new SPEditor().getValue(getContext(), SPEditor.COL_SETTINGS_BG)));
    }

    private class KeyLstnrFragWrd implements View.OnKeyListener {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            if(keyEvent.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                ((FragmentActivity)getContext()).getSupportFragmentManager().popBackStackImmediate();
                //((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.containerActivityMain, FragmentWordSet.getInstance()).commit();
                //onDestroy();
                return true;
            }
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void addChildren(Context context) {

        ClSpinnerContainer[] arrClSpinnerWordSet={new ClSpinnerContainer(context,  context.getResources().getString(R.string.set)
                ,new SpnrTxtSize(context, SPEditor.TXT_SIZE_SET))};
        LlSubContainer llSubContainerWordSetTxtSize=new LlSubContainer(getContext(), context.getResources().getString(R.string.word_set), arrClSpinnerWordSet);


        ClSpinnerContainer[] arrClSpinnerWordDef=
                {
                new ClSpinnerContainer(context, context.getResources().getString(R.string.word)
                    ,new SpnrTxtSize(context, SPEditor.TXT_SIZE_WORD)),
                new ClSpinnerContainer(context,  context.getResources().getString(R.string.definition)
                    ,new SpnrTxtSize(context,SPEditor.TXT_SIZE_DEF))
                ,new ClSpinnerContainer(context, context.getResources().getString(R.string.kind)
                    ,new SpnrTxtSize(context, SPEditor.TXT_SIZE_KIND))
                ,new ClSpinnerContainer(context, context.getResources().getString(R.string.lang)
                    ,new SpnrTxtSize(context, SPEditor.TXT_SIZE_LANG))
                ,new ClSpinnerContainer(context, context.getResources().getString(R.string.example)
                    ,new SpnrTxtSize(context, SPEditor.TXT_SIZE_EXAMPLE))};
        LlSubContainer llSubContainerWordDefTxtSize=new LlSubContainer(getContext(), context.getResources().getString(R.string.word_definition), arrClSpinnerWordDef);


        ClSpinnerContainer[] arrClSpinnerTest={new ClSpinnerContainer(context, context.getResources().getString(R.string.question), new SpnrTxtSize(context, SPEditor.TXT_SIZE_TEST_QUESTION))
        , new ClSpinnerContainer(context, context.getResources().getString(R.string.choice), new SpnrTxtSize(context, SPEditor.TXT_SIZE_TEST_CHOICE))};
        LlSubContainer llSubContainerTestTxtSize=new LlSubContainer(getContext(), "Test", arrClSpinnerTest);

        ///////////////////////////////// WORD GAME
        ClSpinnerContainer[] arrClSpinnerWordGame={new ClSpinnerContainer(context, context.getResources().getString(R.string.question), new SpnrTxtSize(context, SPEditor.TXT_SIZE_WORD_GAME_QUESTION))
                , new ClSpinnerContainer(context, context.getResources().getString(R.string.letter_size), new SpnrTxtSize(context, SPEditor.TXT_SIZE_WORD_GAME_LETTER))
                , new ClSpinnerContainer(context, context.getResources().getString(R.string.clue_size), new SpnrTxtSize(context, SPEditor.TXT_SIZE_WORD_GAME_CLUE))};
        LlSubContainer llSubContainerWordGameTxtSize=new LlSubContainer(getContext(), context.getResources().getString(R.string.word_game), arrClSpinnerWordGame);

        LlSubContainer[] arrTxtSize={llSubContainerWordSetTxtSize, llSubContainerWordDefTxtSize, llSubContainerTestTxtSize, llSubContainerWordGameTxtSize};
        LlMainContainer llMainContainerTxtSize=new LlMainContainer(getContext(), context.getResources().getString(R.string.text_size), arrTxtSize);

        //WORDDEF STYLE----------------------------------------------------------------------------------

        ClSwitchContainer[] arrClSpinnerAppearance={
                new ClSwitchContainer(context, context.getResources().getString(R.string.word_definition)
                        , new SwitchWordDefStyle(context
                        , context.getResources().getString(R.string.detailed)
                        ,context.getResources().getString(R.string.simple)))
        ,new ClSwitchContainer(context, context.getResources().getString(R.string.word_definition)
                +context.getResources().getString(R.string.dialog)
                , new SwitchWordDefDialog(context
                , context.getResources().getString(R.string.detailed)
                ,context.getResources().getString(R.string.simple)))};
        LlSubContainer llSubContainerAppearance=new LlSubContainer(getContext(), null,arrClSpinnerAppearance);
        LlMainContainer llMainContainerAppearance=new LlMainContainer(getContext(), context.getResources().getString(R.string.appearance), new LlSubContainer[]{llSubContainerAppearance});



        //TEST MOVEMENT BUTTONS----------------------------------------------------------------------------------
        ClSwitchContainer[] arrClSpinnerOther={
                new ClSwitchContainer(context, context.getResources().getString(R.string.test_movement)
                        , new SwitchTestMovement(context
                        ,context.getResources().getString(R.string.test_movement_button)
                        ,context.getResources().getString(R.string.test_movement_swipe)))};

        LlSubContainer llSubContainerOther=new LlSubContainer(getContext(), null,arrClSpinnerOther);
        LlMainContainer llMainContainerOther=new LlMainContainer(getContext(), context.getResources().getString(R.string.other), new LlSubContainer[]{llSubContainerOther});



        String email;
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if(acct!=null)
            email=acct.getEmail();


        email= acct==null ? context.getResources().getString(R.string.not_signed) : acct.getEmail();
        TvSubTitle tvEmail=new TvSubTitle(context,email);

        PixelConverter pc=new PixelConverter(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(pc.dp2Px(10),0,0,0);
        tvEmail.setLayoutParams(params);


        llSettings.addView(new LlMainContainer(context,
                context.getString(R.string.account_connected),new View[]{tvEmail}));

        llSettings.addView(new LlMainContainer(context, null, new View[]{tvSettingsColor}));
        llSettings.addView(llMainContainerTxtSize);
        llSettings.addView(llMainContainerOther);
        llSettings.addView(llMainContainerAppearance);
    }
}