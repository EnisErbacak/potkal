package com.metaplikasyon.potkal.fragments.fragment_wordset.editor;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.widget.LinearLayout;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.fragments.fragment_wordset.FragmentWordSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.manager.WordsetManager;
import com.metaplikasyon.potkal.fragments.fragment_wordset.views.container.ContainerWrdset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    CLASS FOR UI UPDATION FOR WORDSET FRAGMENT.
 */

public class UiEdtrWrdSet {
    public static final int BY_CRTD_DSC=0; // NEWER TO OLDER
    public static final int BY_CRTD_ASC=1; // OLDER TO NEWER
    public static final int BY_ALPH_ASC=2; // A-Z
    public static final int BY_ALPH_DSC =3; // Z-A

    private final Context context;

    public UiEdtrWrdSet(Context context) {
        this.context = context;
    }

    public void updateScrn(int order) {
        switch (order) {
            case 0:
                buildByCrtdDateDsc();
                break;
            case 1:
                buildByCrtdDateAsc();
                break;
            case 2:
                buildByAlphAsc();
                break;
            case 3:
                buildByAlphDsc();
                break;
        }
    }

    private void buildByCrtdDateDsc() {
        WordsetManager manager=new WordsetManager();
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        ArrayList<String> fileNames=manager.explore(context).getNames(new PathPicker(context).get(PathPicker.WORDSET));

        for(int i=(fileNames.size()-1);i>=0;i--) {
            llWrdSet.addView(new ContainerWrdset(context,fileNames.get(i),true));
        }
        FragmentWordSet.ORDER_BY=BY_CRTD_DSC;
    }

    private void buildByCrtdDateAsc() {
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        ArrayList<String> fileNames=new WordsetManager().explore(context).getNames(new PathPicker(context).get("wordset"));

        for(int i=0;i<fileNames.size();i++) {
            llWrdSet.addView(new ContainerWrdset(context,fileNames.get(i),true));
        }
        FragmentWordSet.ORDER_BY=BY_CRTD_ASC;
    }

    private void buildByAlphAsc() {
        ArrayList<String> wordSetList= new WordsetManager().explore(context).getNames(new PathPicker(context).get(PathPicker.WORDSET));
        Collections.sort(wordSetList, new Comparator<String>(){

            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }
        });
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        for(int i=0;i<wordSetList.size();i++) {
            llWrdSet.addView(new ContainerWrdset(context,wordSetList.get(i),true));
        }
        FragmentWordSet.ORDER_BY=BY_ALPH_ASC;
    }

    private void buildByAlphDsc() {
        ArrayList<String> wordSetList=new WordsetManager().explore(context).getNames(new PathPicker(context).get(PathPicker.WORDSET));
        Collections.sort(wordSetList, new Comparator<String>(){

            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }

            /*
            @Override
            public int compare(Object s1, Object s2) {
                return ((String)s1).compareToIgnoreCase((String)s2);
            }

             */
        });
        LinearLayout llWrdSet=scanForActivity(context).findViewById(R.id.pnlWrdSetMain);
        llWrdSet.removeAllViews();
        for(int i=(wordSetList.size()-1);i>=0;i--) {
            llWrdSet.addView(new ContainerWrdset(context,wordSetList.get(i),true));
        }
        FragmentWordSet.ORDER_BY= BY_ALPH_DSC;
    }

    private Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());
        return null;
    }
}
