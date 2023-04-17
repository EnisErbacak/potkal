package com.metaplikasyon.potkal.file.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.metaplikasyon.potkal.R;

import java.util.HashMap;
import java.util.Map;

public class SPEditor {

    private HashMap<String,String> hm;
    private final String SP_NAME="SharedPref";
    public static final String FIRST_OPEN="true";

    public static final String COL_TOP_STATUSBAR="col_top_statusbar";
    public static final String COL_GENERAL_BG ="col_general_bg";
    public static final String COL_GENERAL_TXT ="col_general_txt";
    public static final String COL_GENERAL_BTN_BG="col_general_btn_bg";
    public static final String COL_GENERAL_BTN_TXT="col_general_btn_txt";
    public static final String COL_GENERAL_BTN_ICON="col_general_btn_txt";

    public static final String TXT_SIZE_SET ="set_txt_size";
    public static final String TXT_SIZE_WORD ="word_txt_size";
    public static final String TXT_SIZE_DEF ="def_txt_size";
    public static final String TXT_SIZE_KIND="txt_size_kind";
    public static final String TXT_SIZE_LANG="txt_size_lang";
    public static final String TXT_SIZE_EXAMPLE="txt_size_example";

    public static final String APP_THEME="app_theme";
    public static final String APP_LANG="app_lang";
    public static final String APPEARANCE="appearance";
    public static final String DIALOG_APPEARANCE="dialog_appearance";

    // ALL COLORS ARE IN HEX FORMAT.
    public static final String COL_WORDSET="col_wordset";
    public static final String COL_WORDSET_BG="col_wordset_bg";
    public static final String COL_WORDSET_BTN_BG="col_wordset_btn_bg";
    public static final String COL_WORDSET_TXT="col_wordset_txt";
    public static final String COL_WORDSET_STATUSBAR="col_wordset_statusbar";
    public static final String COL_WORDSET_STATUSBAR_TXT="col_wordset_statusbar_txt";

    public static final String COL_WORDDEF="col_worddef";
    public static final String COL_WORDDEF_BG="col_worddef_bg";
    public static final String COL_WORDDEF_BTN_BG="col_worddef_btn_bg";
    public static final String COL_WORDDEF_TXT="col_worddef_txt";
    public static final String COL_WORDDEF_STATUSBAR="col_worddef_statusbar";
    public static final String COL_WORDDEF_TOPBAR_TXT ="col_worddef_statusbar_txt";

    public static final String COL_WORDDEF_WORD_TXT="col_worddef_word_txt";
    public static final String COL_WORDDEF_DEF_TXT="col_worddef_def_txt";
    public static final String COL_WORDDEF_KIND_TXT="col_worddef_kind_txt";
    public static final String COL_WORDDEF_LANG_TXT="col_worddef_lang_txt";
    public static final String COL_WORDDEF_EXMP_TXT="col_worddef_exmp_txt";


    public static final String COL_SETTINGS_BG="col_settings_bg";
    public static final String COL_SETTINGS_PANEL="col_settings_panel";
    public static final String COL_SETTINGS_TXT="col_settings_txt";

    public static final String DUPLICATION_CHCK="duplication_check";
    public static final String DUPLICATION_CHCK_CURRENT="current";
    public static final String DUPLICATION_CHCK_ALL="all";

    public static final String COL_TEST_BG="col_test_bg";
    public static final String COL_TEST_CHOICE_BG="col_test_choice_bg";
    public static final String COL_TEST_CHOICE_TXT="col_test_choice_txt";
    public static final String COL_TEST_PANEL_BG="col_test_panel_bg";
    public static final String COL_TEST_TXT ="col_test_txt";
    public static final String COL_TEST_CHOICE_CORRECT="col_test_choice_correct";
    public static final String COL_TEST_CHOICE_INCORRECT="col_test_choice_incorrect";

    public static final String TXT_SIZE_TEST_QUESTION="txt_size_test_question";
    public static final String TXT_SIZE_TEST_CHOICE="txt_size_test_choice";
    public static final String TEST_BUTTONS="test_buttons";

    public static final String TXT_SIZE_WORD_GAME_LETTER="txt_size_word_game_letter";
    public static final String TXT_SIZE_WORD_GAME_CLUE="txt_size_word_game_clue";
    public static final String HEXAGON_SIZE_WORD_GAME="size_hexagon_word_game";
    public static final String TXT_SIZE_WORD_GAME_QUESTION="txt_size_word_game_question";

    public static final String COL_WORDGAME_BG="col_wordgame_bg";
    public static final String COL_WORDGAME_TXT="col_wordgame_txt";
    public static final String COL_WORDGAME_PANEL_BG="col_wordgame_panel_bg";
    public static final String COL_WORDGAME_COMB_PANEL_BG="col_wordgame_comb_panel_bg";
    public static final String COL_WORDGAME_COMB_LETTER="col_wordgame_comb_letter";
    public static final String COL_WORDGAME_BTN_BG ="col_wordgame_btn_bg";
    public static final String COL_WORDGAME_BTN_TXT="col_wordgame_btn_txt";


    private void initialize(Context context) {
        hm=new HashMap<>();
        hm.put(TXT_SIZE_SET,"35");
        hm.put(TXT_SIZE_WORD,"25");
        hm.put(TXT_SIZE_DEF,"15");
        hm.put(TXT_SIZE_LANG, "15");
        hm.put(TXT_SIZE_KIND, "15");
        hm.put(TXT_SIZE_EXAMPLE, "15");
        hm.put(COL_TOP_STATUSBAR, String.valueOf(ContextCompat.getColor(context, R.color.top_status_bar)));
        hm.put(COL_GENERAL_BG, String.valueOf(ContextCompat.getColor(context, R.color.dialog_bg)));
        hm.put(COL_GENERAL_TXT, String.valueOf(ContextCompat.getColor(context, R.color.general_txt)));
        hm.put(COL_GENERAL_BTN_BG, String.valueOf(ContextCompat.getColor(context, R.color.general_btn_bg)));
        hm.put(COL_GENERAL_BTN_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_btn_gnrl_txt)));
        hm.put(COL_GENERAL_BTN_ICON, String.valueOf(ContextCompat.getColor(context, R.color.col_btn_gnrl_icon)));

        hm.put(APP_THEME, "eng");
        hm.put(APP_LANG,"eng");
        hm.put(APPEARANCE, "detailed");
        hm.put(DIALOG_APPEARANCE,"detailed");

        hm.put(DUPLICATION_CHCK, "all");
        hm.put(TEST_BUTTONS, "on");

        hm.put(COL_WORDSET,String.valueOf(ContextCompat.getColor(context,R.color.wordset_panel)));
        hm.put(COL_WORDSET_BG,String.valueOf(ContextCompat.getColor(context,R.color.wordset_bg)));
        hm.put(COL_WORDSET_BTN_BG,String.valueOf(ContextCompat.getColor(context,R.color.wordset_btn_bg)));
        hm.put(COL_WORDSET_TXT,String.valueOf(ContextCompat.getColor(context,R.color.col_black)));
        hm.put(COL_WORDSET_STATUSBAR,String.valueOf(ContextCompat.getColor(context,R.color.wordset_statusbar)));
        hm.put(COL_WORDSET_STATUSBAR_TXT,String.valueOf(ContextCompat.getColor(context,R.color.wordset_statusbar_txt)));

        hm.put(COL_WORDDEF,String.valueOf(ContextCompat.getColor(context, R.color.worddef_panel)));
        hm.put(COL_WORDDEF_BG,String.valueOf(ContextCompat.getColor(context, R.color.worddef_bg)));
        hm.put(COL_WORDDEF_BTN_BG,String.valueOf(ContextCompat.getColor(context, R.color.worddef_btn_bg)));
        hm.put(COL_WORDDEF_TXT,String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_STATUSBAR,String.valueOf(ContextCompat.getColor(context, R.color.worddef_statusbar)));
        hm.put(COL_WORDDEF_TOPBAR_TXT,String.valueOf(ContextCompat.getColor(context, R.color.worddef_topbar_txt)));

        hm.put(COL_WORDDEF_WORD_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_DEF_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_KIND_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_LANG_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_EXMP_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));


        hm.put(COL_TEST_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_bg)));
        hm.put(COL_TEST_PANEL_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_panel_bg)));
        hm.put(COL_TEST_CHOICE_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_choice_bg)));
        hm.put(COL_TEST_CHOICE_TXT, String.valueOf(ContextCompat.getColor(context, R.color.test_choice_txt)));
        hm.put(COL_TEST_TXT, String.valueOf(ContextCompat.getColor(context, R.color.test_txt)));
        hm.put(COL_TEST_CHOICE_CORRECT, String.valueOf(ContextCompat.getColor(context, R.color.test_choice_correct)));
        hm.put(COL_TEST_CHOICE_INCORRECT, String.valueOf(ContextCompat.getColor(context, R.color.test_choice_incorrect)));

        hm.put(TXT_SIZE_TEST_QUESTION, String.valueOf(30));
        hm.put(TXT_SIZE_TEST_CHOICE, String.valueOf(14));

        hm.put(COL_SETTINGS_BG,String.valueOf(ContextCompat.getColor(context, R.color.settings_bg)));
        hm.put(COL_SETTINGS_PANEL,String.valueOf(ContextCompat.getColor(context, R.color.settings_panel)));
        hm.put(COL_SETTINGS_TXT,String.valueOf(ContextCompat.getColor(context, R.color.settings_txt)));

        hm.put(TXT_SIZE_WORD_GAME_CLUE, String.valueOf(15));
        hm.put(TXT_SIZE_WORD_GAME_LETTER, String.valueOf(30));
        hm.put(HEXAGON_SIZE_WORD_GAME, String.valueOf(30));
        hm.put(TXT_SIZE_WORD_GAME_QUESTION, String.valueOf(20));

        hm.put(COL_WORDGAME_TXT, String.valueOf(ContextCompat.getColor(context, R.color.test_txt)));
        hm.put(COL_WORDGAME_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_txt)));
        hm.put(COL_WORDGAME_PANEL_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_panel_bg)));
        hm.put(COL_WORDGAME_COMB_PANEL_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_txt)));
        hm.put(COL_WORDGAME_COMB_LETTER, String.valueOf(ContextCompat.getColor(context, R.color.test_panel_bg)));
        hm.put(COL_WORDGAME_BTN_BG, String.valueOf(ContextCompat.getColor(context, R.color.btn_wordgame_bg)));
        hm.put(COL_WORDGAME_BTN_TXT, String.valueOf(ContextCompat.getColor(context, R.color.test_panel_bg)));
    }

    public void setValues(Context context, String key, String value) {
        SharedPreferences sp= context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString(key, value);
        editor.apply();
    }

    public boolean firstStart(Context context, FragmentManager fm) {
        boolean result=false;
        SharedPreferences sp= context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        if (! sp.contains("first_run")) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            setValues(context, "first_run","true");
            initialize(context);
            checkValues(context);
            result=true;
        }
        return result;
    }

    public void update(Context context) {
        SharedPreferences sp= context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        reBuild(context);
        if (! sp.contains("update1")) {
            setValues(context, "update1","true");
            checkValues(context);
        }
        if (! sp.contains("update2")) {
            setValues(context, "update2","true");
            checkValues(context);
        }if (! sp.contains("update3")) {
            setValues(context, "update3","true");
            checkValues(context);
        }if (! sp.contains("update4")) {
            setValues(context, "update4","true");
            checkValues(context);
        }if (! sp.contains("update5")) {
            setValues(context, "update5","true");
            checkValues(context);
        }if (! sp.contains("update6")) {
            setValues(context, "update6","true");
            checkValues(context);
        }if (! sp.contains("update7")) {
            setValues(context, "update7","true");
            checkValues(context);
        }if (! sp.contains("update8")) {
            setValues(context, "update8","true");
            checkValues(context);
        }if (! sp.contains("update9")) {
            setValues(context, "update9","true");
            checkValues(context);
        }if (! sp.contains("update10")) {
            setValues(context, "update10","true");
            checkValues(context);
            reset(context);
        }
    }

    private void checkValues(Context context) {
        SharedPreferences sp= context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        for (String key : hm.keySet()) {
            if(!sp.contains(key)) setValues(context,key,hm.get(key));
        }
    }

    public String getString(Context context, String key) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key,"");
    }

    public int getInt(Context context, String key) {
        return Integer.parseInt(context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key,""));
    }

    private void reBuild(Context context) {
        hm=new HashMap<>();
        hm.put(TXT_SIZE_SET,"35");
        hm.put(TXT_SIZE_WORD,"25");
        hm.put(TXT_SIZE_DEF,"15");
        hm.put(TXT_SIZE_LANG, "15");
        hm.put(TXT_SIZE_KIND, "15");
        hm.put(TXT_SIZE_EXAMPLE, "15");
        hm.put(COL_TOP_STATUSBAR, String.valueOf(ContextCompat.getColor(context, R.color.top_status_bar)));
        hm.put(COL_GENERAL_BG, String.valueOf(ContextCompat.getColor(context, R.color.dialog_bg)));
        hm.put(COL_GENERAL_TXT, String.valueOf(ContextCompat.getColor(context, R.color.general_txt)));
        hm.put(COL_GENERAL_BTN_BG, String.valueOf(ContextCompat.getColor(context, R.color.general_btn_bg)));
        hm.put(COL_GENERAL_BTN_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_btn_gnrl_txt)));
        hm.put(COL_GENERAL_BTN_ICON, String.valueOf(ContextCompat.getColor(context, R.color.col_btn_gnrl_icon)));

        hm.put(APP_THEME, "eng");
        hm.put(APP_LANG,"eng");
        hm.put(APPEARANCE, "detailed");
        hm.put(DIALOG_APPEARANCE,"detailed");

        hm.put(DUPLICATION_CHCK, "all");
        hm.put(TEST_BUTTONS, "on");

        hm.put(COL_WORDSET,String.valueOf(ContextCompat.getColor(context,R.color.wordset_panel)));
        hm.put(COL_WORDSET_BG,String.valueOf(ContextCompat.getColor(context,R.color.wordset_bg)));
        hm.put(COL_WORDSET_BTN_BG,String.valueOf(ContextCompat.getColor(context,R.color.wordset_btn_bg)));
        hm.put(COL_WORDSET_TXT,String.valueOf(ContextCompat.getColor(context,R.color.col_black)));
        hm.put(COL_WORDSET_STATUSBAR,String.valueOf(ContextCompat.getColor(context,R.color.wordset_statusbar)));
        hm.put(COL_WORDSET_STATUSBAR_TXT,String.valueOf(ContextCompat.getColor(context,R.color.wordset_statusbar_txt)));

        hm.put(COL_WORDDEF,String.valueOf(ContextCompat.getColor(context, R.color.worddef_panel)));
        hm.put(COL_WORDDEF_BG,String.valueOf(ContextCompat.getColor(context, R.color.worddef_bg)));
        hm.put(COL_WORDDEF_BTN_BG,String.valueOf(ContextCompat.getColor(context, R.color.worddef_btn_bg)));
        hm.put(COL_WORDDEF_TXT,String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_STATUSBAR,String.valueOf(ContextCompat.getColor(context, R.color.worddef_statusbar)));
        hm.put(COL_WORDDEF_TOPBAR_TXT,String.valueOf(ContextCompat.getColor(context, R.color.worddef_topbar_txt)));

        hm.put(COL_WORDDEF_WORD_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_DEF_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_KIND_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_LANG_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));
        hm.put(COL_WORDDEF_EXMP_TXT, String.valueOf(ContextCompat.getColor(context, R.color.col_black)));


        hm.put(COL_TEST_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_bg)));
        hm.put(COL_TEST_PANEL_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_panel_bg)));
        hm.put(COL_TEST_CHOICE_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_choice_bg)));
        hm.put(COL_TEST_CHOICE_TXT, String.valueOf(ContextCompat.getColor(context, R.color.test_choice_txt)));
        hm.put(COL_TEST_TXT, String.valueOf(ContextCompat.getColor(context, R.color.test_txt)));
        hm.put(COL_TEST_CHOICE_CORRECT, String.valueOf(ContextCompat.getColor(context, R.color.test_choice_correct)));
        hm.put(COL_TEST_CHOICE_INCORRECT, String.valueOf(ContextCompat.getColor(context, R.color.test_choice_incorrect)));

        hm.put(TXT_SIZE_TEST_QUESTION, String.valueOf(30));
        hm.put(TXT_SIZE_TEST_CHOICE, String.valueOf(14));

        hm.put(COL_SETTINGS_BG,String.valueOf(ContextCompat.getColor(context, R.color.settings_bg)));
        hm.put(COL_SETTINGS_PANEL,String.valueOf(ContextCompat.getColor(context, R.color.settings_panel)));
        hm.put(COL_SETTINGS_TXT,String.valueOf(ContextCompat.getColor(context, R.color.settings_txt)));

        hm.put(TXT_SIZE_WORD_GAME_CLUE, String.valueOf(15));
        hm.put(TXT_SIZE_WORD_GAME_LETTER, String.valueOf(30));
        hm.put(HEXAGON_SIZE_WORD_GAME, String.valueOf(30));
        hm.put(TXT_SIZE_WORD_GAME_QUESTION, String.valueOf(20));

        hm.put(COL_WORDGAME_TXT, String.valueOf(ContextCompat.getColor(context, R.color.test_txt)));
        hm.put(COL_WORDGAME_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_txt)));
        hm.put(COL_WORDGAME_PANEL_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_panel_bg)));
        hm.put(COL_WORDGAME_COMB_PANEL_BG, String.valueOf(ContextCompat.getColor(context, R.color.test_txt)));
        hm.put(COL_WORDGAME_COMB_LETTER, String.valueOf(ContextCompat.getColor(context, R.color.test_panel_bg)));
        hm.put(COL_WORDGAME_BTN_BG, String.valueOf(ContextCompat.getColor(context, R.color.btn_wordgame_bg)));
        hm.put(COL_WORDGAME_BTN_TXT, String.valueOf(ContextCompat.getColor(context, R.color.test_panel_bg)));


    }

    public void reset(Context context) {
        reBuild(context);
        for (Map.Entry<String, String> entry : hm.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            setValues(context,key,value);
        }
    }
}
