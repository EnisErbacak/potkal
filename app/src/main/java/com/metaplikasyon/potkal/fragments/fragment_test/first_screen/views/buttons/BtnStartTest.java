package com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.operator.FileManager;
import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.container.ContainerTestWordset;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.FragmentTest;
import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process.question_creator.QuestionCreator;
import com.metaplikasyon.potkal.reaction.Reactor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class BtnStartTest extends androidx.appcompat.widget.AppCompatButton {

    public static final int START_TEST=0;
    public static final int START_WORD_GAME=1;
    private final Context context;
    public BtnStartTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        onCreate();
    }

    private void onCreate() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout ll=view.getRootView().findViewById(R.id.llWrdGameSets);
                RadioButton rdBtn=view.getRootView().findViewById(R.id.rdBtnQWrdDef);

                // getSetNames(ll).size must be greater than 4 at least
                Reactor reactor =new Reactor(context);
                if(getSetNames(ll).size()==0) reactor.showShort(getContext().getResources().getString(R.string.pls_choose_set));
                else if(! checkSet(ll)) reactor.showShort(context.getResources().getString(R.string.pls_choose_safe_set_test));
                else {
                    FragmentManager fragManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                    FragmentTransaction fragTransacion = fragManager.beginTransaction();

                    fragTransacion.add(R.id.containerActivityMain, new FragmentTest(getSetNames(ll), getQType(rdBtn)));
                    fragTransacion.addToBackStack(null);
                    fragTransacion.commit();
                }
            }
        });
        setStyle();
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        //getBackground().setColorFilter(sp.getInt(context, SPEditor.COL_GENERAL_BTN_BG), PorterDuff.Mode.SRC_ATOP);
//        getBackground().setColorFilter(
//                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(sp.getInt(context,SPEditor.COL_GENERAL_BTN_BG), BlendModeCompat.SRC_ATOP));
        setTextColor(sp.getInt(context, SPEditor.COL_TEST_TXT));
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
                        jsonException.printStackTrace();
                    }
                }
                System.out.println("asdfasdfa");
            }
        return keyValPair > 4;
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



    private int getQType(RadioButton  rbtn) {
        int result=0;
        if(rbtn.isChecked()) result=QuestionCreator.WORD_IS_QUESTION;
        else result=QuestionCreator.DEFINITION_IS_QUESTION;

        return result;
    }
}