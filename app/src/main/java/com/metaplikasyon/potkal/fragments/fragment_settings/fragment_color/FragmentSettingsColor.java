package com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.containers.LlMainContainer;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.containers.LlSubContainer;
import com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.views.container.ClColorContainer;
import com.metaplikasyon.potkal.other.PixelConverter;
import com.metaplikasyon.potkal.reaction.Reactor;

public class FragmentSettingsColor extends Fragment {
    private LinearLayout llColorSettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings_color, container, false);
        llColorSettings = rootView.findViewById(R.id.llColorSettings);
        llColorSettings.setBackgroundColor(new SPEditor().getInt(getContext(), SPEditor.COL_SETTINGS_BG));

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCondition(getView());
        onCreate(getContext());
    }

    private void setCondition(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    int count = ((FragmentActivity) getContext()).getSupportFragmentManager().getBackStackEntryCount();
                    ((FragmentActivity) getContext()).getSupportFragmentManager().popBackStackImmediate();
                    //((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction()
                    //        .replace(R.id.containerActivityMain, new FragmentSettings()).commit();
                    //onDestroy();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void onCreate(Context context) {
        String str_set = context.getResources().getString(R.string.set);
        String str_def = context.getResources().getString(R.string.definition);
        String str_bg = context.getResources().getString(R.string.background);
        String str_btn = context.getResources().getString(R.string.button);
        String str_txt = context.getResources().getString(R.string.text);
        String str_settings = context.getResources().getString(R.string.settings);
        String str_topbar = context.getResources().getString(R.string.top_bar);
        String str_dialog = context.getResources().getString(R.string.dialog);
        String str_panel=context.getResources().getString(R.string.panel);


        //GENERAL//////////////////////////////////////////////////////////////////////////////////////////
        ClColorContainer[] arrGeneral = {
                new ClColorContainer(context, str_topbar, SPEditor.COL_TOP_STATUSBAR)
                , new ClColorContainer(context, str_bg, SPEditor.COL_GENERAL_BG)
                ,new ClColorContainer(context,getResources().getString(R.string.col_btn_txt), SPEditor.COL_GENERAL_BTN_TXT)
                , new ClColorContainer(context, str_btn+" "+str_bg, SPEditor.COL_GENERAL_BTN_BG)
        };

        ClColorContainer[] arrGeneralTxt={
                new ClColorContainer(context, context.getResources().getString(R.string.dialog), SPEditor.COL_GENERAL_TXT)
        };

        LlSubContainer llSubGeneral = new LlSubContainer(context, null, arrGeneral);
        LlSubContainer llSubGeneralTxt=new LlSubContainer(context, str_txt, arrGeneralTxt);
        LlMainContainer llMainGeneral = new LlMainContainer(context, context.getResources().getString(R.string.general)
                , new LlSubContainer[]{llSubGeneral, llSubGeneralTxt});
        ///WORDSET/////////////////////////////////////////////////////////////////////////////////////////
        ClColorContainer[] arrWordSet = {
                  new ClColorContainer(context, str_topbar, SPEditor.COL_WORDSET_STATUSBAR)
                , new ClColorContainer(context, str_panel, SPEditor.COL_WORDSET)
                , new ClColorContainer(context, str_bg, SPEditor.COL_WORDSET_BG)
        , new ClColorContainer(context, str_btn + " " + str_bg, SPEditor.COL_WORDSET_BTN_BG)};
        LlSubContainer llSubWordSet = new LlSubContainer(context, null, arrWordSet);
         ///WORDSET_TXT/////////////////////////////////////////////////////////////////////////////////////////
        ClColorContainer[] arrWordSetTxt = {
                new ClColorContainer(context, str_topbar, SPEditor.COL_WORDSET_STATUSBAR_TXT)
                , new ClColorContainer(context, str_set, SPEditor.COL_WORDSET_TXT)};
        LlSubContainer llSubWordSetTxt = new LlSubContainer(context,context.getResources().getString(R.string.text), arrWordSetTxt);

        LlMainContainer llMainWordSet = new LlMainContainer(context, context.getResources().getString(R.string.word_set)
                , new LlSubContainer[]{llSubWordSet, llSubWordSetTxt});

        ///WORDDEF/////////////////////////////////////////////////////////////////////////////////////////
        ClColorContainer[] arrWordDef = {
                  new ClColorContainer(context, str_panel, SPEditor.COL_WORDDEF)
                , new ClColorContainer(context, str_bg, SPEditor.COL_WORDDEF_BG)
                , new ClColorContainer(context, str_btn + " " + str_bg, SPEditor.COL_WORDDEF_BTN_BG)
                , new ClColorContainer(context, str_topbar, SPEditor.COL_WORDDEF_STATUSBAR)};

        ClColorContainer[] arrWordDefTxt ={
                  new ClColorContainer(context, str_topbar, SPEditor.COL_WORDDEF_TOPBAR_TXT)
                //, new ClColorContainer(context, str_def,  SPEditor.COL_WORDDEF_TXT)
                , new ClColorContainer(context, context.getResources().getString(R.string.word), SPEditor.COL_WORDDEF_WORD_TXT)
                , new ClColorContainer(context, context.getResources().getString(R.string.definition), SPEditor.COL_WORDDEF_DEF_TXT)
                , new ClColorContainer(context, context.getResources().getString(R.string.kind), SPEditor.COL_WORDDEF_KIND_TXT)
                , new ClColorContainer(context, context.getResources().getString(R.string.lang), SPEditor.COL_WORDDEF_LANG_TXT)
                , new ClColorContainer(context, context.getResources().getString(R.string.example), SPEditor.COL_WORDDEF_EXMP_TXT)};

        LlSubContainer llSubWordDef = new LlSubContainer(context, null, arrWordDef);
        LlSubContainer llSubWordDefTxt=new LlSubContainer(context, context.getResources().getString(R.string.text), arrWordDefTxt);

        LlMainContainer llMainWordDef = new LlMainContainer(context, context.getResources().getString(R.string.word_definition)
                , new LlSubContainer[]{llSubWordDef,llSubWordDefTxt});

        ///SETTINGS/////////////////////////////////////////////////////////////////////////////////////////
        ClColorContainer[] arrSettings = {
                  new ClColorContainer(context, context.getResources().getString(R.string.panel), SPEditor.COL_SETTINGS_PANEL)
                , new ClColorContainer(context, str_bg, SPEditor.COL_SETTINGS_BG)};
        ClColorContainer[] arrSettingsTxt ={ new ClColorContainer(context, str_settings + " " + str_txt, SPEditor.COL_SETTINGS_TXT)};
        LlSubContainer llSubSettings = new LlSubContainer(context, null, arrSettings);
        LlSubContainer llSubSettingsTxt=new LlSubContainer(context, context.getResources().getString(R.string.text), arrSettingsTxt);

        LlMainContainer llMainSettings = new LlMainContainer(context, context.getResources().getString(R.string.settings)
                , new LlSubContainer[]{llSubSettings, llSubSettingsTxt});


        ///TEST/////////////////////////////////////////////////////////////////////////////////////////
        ClColorContainer[] clSubTest = {
                // test backgroung bottom most.
                 new ClColorContainer(context, context.getResources().getString(R.string.background), SPEditor.COL_TEST_BG)

                // test container background color.
                , new ClColorContainer(context, context.getResources().getString(R.string.panel_background), SPEditor.COL_TEST_PANEL_BG)

                // test choice button bg
                , new ClColorContainer(context, context.getResources().getString(R.string.choice_bg), SPEditor.COL_TEST_CHOICE_BG)};

        LlSubContainer llSubTest = new LlSubContainer(context,null, clSubTest);

        ClColorContainer[] clSubTestTxt = {
                new ClColorContainer(context, context.getResources().getString(R.string.choice), SPEditor.COL_TEST_CHOICE_TXT)
                ,new ClColorContainer(context, context.getResources().getString(R.string.text), SPEditor.COL_TEST_TXT)
                ,new ClColorContainer(context, context.getResources().getString(R.string.test_choice_correct), SPEditor.COL_TEST_CHOICE_CORRECT)
                ,new ClColorContainer(context, context.getResources().getString(R.string.test_choice_incorrect), SPEditor.COL_TEST_CHOICE_INCORRECT)};
        LlSubContainer llSubTestTxt = new LlSubContainer(context, context.getResources().getString(R.string.text), clSubTestTxt);

        LlMainContainer llMainTest = new LlMainContainer(context, "Test"
                , new LlSubContainer[]{llSubTest, llSubTestTxt});


        //WORD_GAME///////////////////////////////////////////////////////////////////////////////////////

        ClColorContainer[] clSubWordGame = {
                // test backgroung bottom most.
                new ClColorContainer(context, context.getResources().getString(R.string.background), SPEditor.COL_WORDGAME_BG)

                // test container background color.
                , new ClColorContainer(context, context.getResources().getString(R.string.panel_background), SPEditor.COL_WORDGAME_PANEL_BG)
                , new ClColorContainer(context, context.getResources().getString(R.string.comb_color), SPEditor.COL_WORDGAME_COMB_PANEL_BG)};

        LlSubContainer llSubWordGame = new LlSubContainer(context,null, clSubWordGame);

        ClColorContainer[] clSubWordGameTxt = {
                new ClColorContainer(context, context.getResources().getString(R.string.text), SPEditor.COL_WORDGAME_TXT)
                ,new ClColorContainer(context, context.getResources().getString(R.string.comb_color), SPEditor.COL_WORDGAME_COMB_PANEL_BG)
                ,new ClColorContainer(context, context.getResources().getString(R.string.comb_letter_color), SPEditor.COL_WORDGAME_COMB_LETTER)};

        LlSubContainer llSubWordGameTxt = new LlSubContainer(context, context.getResources().getString(R.string.text), clSubWordGameTxt);

        LlMainContainer llMainWordGame = new LlMainContainer(context
                , context.getResources().getString(R.string.word_game)
                , new LlSubContainer[]{llSubWordGame, llSubWordGameTxt});

        llColorSettings.addView(llMainGeneral);
        llColorSettings.addView(llMainWordSet);
        llColorSettings.addView(llMainWordDef);
        llColorSettings.addView(llMainSettings);
        llColorSettings.addView(llMainTest);
        llColorSettings.addView(llMainWordGame);
        llColorSettings.addView(new ButtonResetColor(context));
    }

    private class ButtonResetColor extends AppCompatButton{

        private SPEditor sp;
        public ButtonResetColor(@NonNull Context context) {
            super(context);
            sp=new SPEditor();
            onCreate();
        }

        public ButtonResetColor(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            sp=new SPEditor();
        }

        private void onCreate() {
            setStyle();
            setText(getContext().getString(R.string.reset));
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    sp.reset(getContext());
                    Toast.makeText(getContext(),R.string.colors_are_reset,Toast.LENGTH_LONG).show();
                }
            });
        }

        private void setStyle() {
            PixelConverter pc=new PixelConverter(getContext());
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(pc.dp2Px(7),0,pc.dp2Px(7),pc.dp2Px(20));
            setLayoutParams(lp);

            getBackground().setTint(sp.getInt(getContext()
            ,SPEditor.COL_SETTINGS_PANEL));
            setTextColor(sp.getInt(getContext()
            ,SPEditor.COL_SETTINGS_TXT));
        }
    }
}
