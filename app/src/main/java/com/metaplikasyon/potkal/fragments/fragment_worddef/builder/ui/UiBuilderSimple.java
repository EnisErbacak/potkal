package com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.Word;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.operator.WordOperator;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed.LlContainerLower;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed.LlContainerMain;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed.ClContainerUpper;
import com.metaplikasyon.potkal.fragments.fragment_wordset.manager.WordsetManager;
import com.metaplikasyon.potkal.other.ScannerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class UiBuilderSimple implements UiBuilder {

    public static final int BY_CRTD_DSC=0; // NEWER TO OLDER
    public static final int BY_CRTD_ASC=1; // OLDER TO NEWER
    public static final int BY_ALPH_ASC=2; // A-Z
    public static final int BY_ALPH_DSC =3; // Z-A

    private final Context context;
    private final String setName;

    public UiBuilderSimple(Context context, String setName) {
        this.context = context;
        this.setName = setName;
    }

    @Override
    public void updateScreen() {
        buildScreen(FragmentWordDef.ORDER_BY);
    }

    public void buildScreen(int order) {
        switch (order) {
            case 0: // Default first build
                buildByCreateDateDsc();
                break;
            case 1:
                buildByCreateDateAsc();
                break;
            case 2:
                //buildByAlphAsc();
                break;
            case 3:
                //buildByAlphDsc();
                break;
        }
    }

    @Override
    public ArrayList<String> getKeyList(Iterator<String> iterator, boolean reversed) {
        ArrayList<String> keys=new ArrayList<>();
        while(iterator.hasNext())
            keys.add(iterator.next());

        if(reversed==true)
            Collections.reverse(keys);
        return keys;
    }

    @Override
    public ArrayList<String> sortAlphabetical(ArrayList<String> keys, boolean reversed) {
        ArrayList<String> keyList=keys;
        Collections.sort(keyList, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        if(reversed)
            Collections.reverse(keyList);
        return keyList;
    }

    @Override
    public View getCleanedPanel(Context context) {
//        LinearLayout ll= new ScannerActivity().scanForActivity(context).findViewById(R.id.pnlWordDefVrt);
//        ll.removeAllViews();
        return null;
    }

    @Override
    public void buildByCreateDateAsc() {
        JSONObject jObj=new  WordsetManager().operate(context).get(setName);
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);
        ArrayList<String> keys= getKeyList(jObj.keys(), false);
        createView(ll, keys, jObj);
    }

    @Override
    public void buildByCreateDateDsc() {
        JSONObject jObj=new WordsetManager().operate(context).get(setName);
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);
        ArrayList<String> keys= getKeyList(jObj.keys(), true);
        createView(ll, keys, jObj);
    }

    @Override
    public void buildByAlphabeticalAsc() {
        JSONObject jObj=new WordsetManager().operate(context).get(setName);
        LinearLayout ll=(LinearLayout) getCleanedPanel(context);
        ArrayList<String> keys=sortAlphabetical(getKeyList(jObj.keys(), false),false);

        createView(ll, keys, jObj);
    }

    @Override
    public void buildByAlphabeticalDsc() {
        JSONObject jObj = new WordsetManager().operate(context).get(setName);
        LinearLayout ll = (LinearLayout) getCleanedPanel(context);
        ArrayList<String> keys = sortAlphabetical(getKeyList(jObj.keys(), false), true);

        createView(ll, keys, jObj);
    }

    @Override
    public void createView(LinearLayout ll, ArrayList<String> keys, JSONObject jObj) {
        Word word;
        WordOperator wordOperator=new WordOperator();
        for(int i=0;i<keys.size();i++) {
            try {
                word=wordOperator.convert2Word(jObj.getJSONObject(keys.get(i)), keys.get(i));
            }catch (JSONException je) {
                je.printStackTrace();
                break;
            }
            ll.addView(new LlContainerMain(context, new ClContainerUpper(context, word.getWrd())
                    , new LlContainerLower(context, word.getDef())));
        }
    }

}
