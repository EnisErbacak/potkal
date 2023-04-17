package com.metaplikasyon.potkal.fragments.fragment_word_game.first.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.operator.FileManager;
import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.container.ContainerTestWordset;
import com.metaplikasyon.potkal.fragments.fragment_word_game.FragmentWordGame;
import com.metaplikasyon.potkal.fragments.fragment_word_game.first.FragmentWordGameFirst;
import com.metaplikasyon.potkal.reaction.Reactor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class BtnStartWordGame extends androidx.appcompat.widget.AppCompatButton {
    private Context context;
    private RadioButton rbWordGameByCollectionOrder, rbWordGameByLetterCount, rbWordGameByRandom;
    public BtnStartWordGame(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        onCreate();
    }

    private void onCreate() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout ll=view.getRootView().findViewById(R.id.llWrdGameSets);
                RadioGroup rbg=view.getRootView().findViewById(R.id.rbGroupWordGameFirstQType);
                rbWordGameByCollectionOrder=view.getRootView().findViewById(R.id.rbWordGameByCollectionOrder);
                rbWordGameByLetterCount=view.getRootView().findViewById(R.id.rbWordGameByLetterCount);
                rbWordGameByRandom=view.getRootView().findViewById(R.id.rbWordGameByRandom);

                // getSetNames(ll).size must be greater than 4 at least
                Reactor reactor =new Reactor(context);
                if(getSetNames(ll).size()==0) reactor.showShort(getContext().getResources().getString(R.string.pls_choose_set));
                else if(! checkSet(ll)) reactor.showShort(context.getResources().getString(R.string.pls_choose_safe_set_wordgame));
                else {
                    FragmentManager fragManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                    FragmentTransaction fragTransacion = fragManager.beginTransaction();

                    fragTransacion.add(R.id.containerActivityMain, new FragmentWordGame(getSetNames(ll), getQType(rbg)));
                    fragTransacion.addToBackStack(null);
                    fragTransacion.commit();
                }
            }
        });
    }

    private int getQType(RadioGroup rg) {
        int result = FragmentWordGameFirst.QTypeByCollectionOrder;
            if(rg.getCheckedRadioButtonId()== rbWordGameByCollectionOrder.getId()) {
                result=FragmentWordGameFirst.QTypeByCollectionOrder;
            }
            if(rg.getCheckedRadioButtonId()== rbWordGameByLetterCount.getId()) {
                result=FragmentWordGameFirst.QTypeByLetterCount;
            }
            if(rg.getCheckedRadioButtonId()== rbWordGameByRandom.getId()) {
                result=FragmentWordGameFirst.QTypeByRandom;
            }
            return result;
    }

    private ArrayList<String> getSetNames(LinearLayout ll) {
        ArrayList<String> names=new ArrayList<>();
        for(int i=0;i<ll.getChildCount();i++) {
            ContainerTestWordset containerTestWordset= (ContainerTestWordset) ll.getChildAt(i);
            if(containerTestWordset.getCheckBox().isChecked()) names.add(containerTestWordset.getSetName());
        }
        return names;
    }

    private boolean checkSet(LinearLayout ll) {
        ArrayList<JSONObject> jObjList=getPairsJ(getSetNames(ll));
        int keyValPair=0; // Number of the word - definition pairs.

        for(int i=0;i<jObjList.size();i++) {
            JSONObject jTemp=jObjList.get(i);

            Iterator<String> keys=jObjList.get(i).keys();
            while (keys.hasNext()) {
                String key=keys.next();
                try {
                    jTemp.getJSONObject(key).getString("def");
                    keyValPair++;
                }catch (JSONException jsonException) {
                    //jsonException.printStackTrace();
                }
            }
            System.out.println("asdfasdfa");
        }
        boolean result= keyValPair > 0;
        return result;
    }


    private ArrayList<JSONObject> getPairsJ(ArrayList<String> setNameList) {
        ArrayList<JSONObject> jObjList=new ArrayList<>();
        FileManager fileManager=new FileManager();
        for(int i=0;i<setNameList.size();i++) {
            try {
                jObjList.add(new JSONObject(fileManager.operate().read(new PathPicker(context).get(PathPicker.WORDSET) + File.separator+ setNameList.get(i))));
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        }
        return jObjList;
    }
}
