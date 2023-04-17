package com.metaplikasyon.potkal.fragments.fragment_wordset.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_wordset.FragmentWordSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.editor.UiEdtrWrdSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.manager.WordsetManager;
import com.metaplikasyon.potkal.reaction.Reactor;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentAddSet extends AppCompatDialogFragment {

    private final int MTCH_PRNT=WindowManager.LayoutParams.MATCH_PARENT;

    private final LinearLayout panelMainVrt;
    private String wordSetName;
    private  LinearLayout llDlgAddWrdSet;

    private Button btnDlgAddWrdSetAdd,btnDlgAddWrdSetCncl;
    private EditText edtTxtWrdSetName;

    public FragmentAddSet(LinearLayout panelMainVrt) {
        this.panelMainVrt=panelMainVrt;
    }

    private void setView() {

        WindowManager.LayoutParams lp=getDialog().getWindow().getAttributes();
        lp.width=MTCH_PRNT;
        lp.height=MTCH_PRNT;
        getDialog().getWindow().setLayout(MTCH_PRNT,MTCH_PRNT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_word_set_add,container);
        llDlgAddWrdSet=view.findViewById(R.id.llDlgAddWrdSet);
        setStyle();
        return view;
    }

    private void setStyle() {
        //llDlgAddWrdSet.getBackground().setColorFilter( new SPEditor().getInt(getContext(), SPEditor.COL_GENERAL_BG), PorterDuff.Mode.SRC_ATOP);
        llDlgAddWrdSet.getBackground().setColorFilter(
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(new SPEditor().getInt(getContext(), SPEditor.COL_GENERAL_BG), BlendModeCompat.SRC_ATOP));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtTxtWrdSetName=view.findViewById(R.id.edtTxtWrdSetName);
        btnDlgAddWrdSetAdd=view.findViewById(R.id.btnDlgAddWrdSetAdd);
        btnDlgAddWrdSetCncl=view.findViewById(R.id.btnDlgAddWrdSetCncl);

        edtTxtWrdSetName.setOnClickListener(new EdtTxtLsnr());
        btnDlgAddWrdSetCncl.setOnClickListener(new BtnCnclLstnr());
        btnDlgAddWrdSetAdd.setOnClickListener(new BtnAddLstnr());

        setBckGrnd();
    }

    @Override
    public void onResume() {
        super.onResume();
        setView();
    }

    private void setBckGrnd() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private class EdtTxtLsnr implements View.OnClickListener {

        boolean clicked=false;
        @Override
        public void onClick(View view) {
            if(clicked==false) {
                ((EditText) view).setText("");
                clicked=true;}
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class BtnAddLstnr implements View.OnClickListener{

        String wordSetName;
        @Override
        public void onClick(View view) {
            wordSetName=edtTxtWrdSetName.getText().toString();

            if(!isEmpty(wordSetName)) {
                if(new WordsetManager().operate(getContext()).add(wordSetName, new JSONObject())) {
                    FragmentAddSet.this.dismiss();
                    new UiEdtrWrdSet(getContext()).updateScrn(FragmentWordSet.ORDER_BY);
                } else {
                    new Reactor(getContext()).showShort(view.getContext().getResources().getString(R.string.set_exists));
                }
            }
            else
                new Reactor(getContext()).showShort(getContext().getResources().getString(R.string.input_set_name));
        }

        private boolean isEmpty(String txt) {
            Pattern p = Pattern.compile("\\w");
            Matcher m = p.matcher(txt);

            return !m.find();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class BtnCnclLstnr implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            FragmentAddSet.this.dismiss();
        }
    }
}
