package com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.manager.WorddefManager;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.operator.WordOperator;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.operator.BuilderEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.Word;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.spinners.SpinnerPts;
import com.metaplikasyon.potkal.tdk.TdkBtnLstner;
import com.metaplikasyon.potkal.tdk.TdkTxtWatcher;
import com.metaplikasyon.potkal.tdk.process.TdkWord;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentDialogAddWrdDef extends AppCompatDialogFragment implements CustomDialogFragment {

    private final int MTCH_PRNT=WindowManager.LayoutParams.MATCH_PARENT;
    private final String DIALOG_DETAILED="detailed";
    private final String DIALOG_SIMPLE="simple";

    private FragmentWordDef fragmentWordDef;
    private Button btnCncl,btnAddWrdDef, btnDsplyTdk;
    private BtnAddWrdDefLstnr addWrdDefLstnr;
    private ProgressBar pbTdk;
    private ScrollView svDlgAddWordDef;

    private SpinnerPts spinnerPts;
    private EditText etDlgAddWrdStr, etDlgAddDef, etDlgAddKind, etDlgAddLang, etDlgAddExmp;
//    private final LinearLayout pnlWrdDefVrt;

    private final String setName;

    private ArrayList<TdkWord> tdkWordList;

    private TdkTxtWatcher etWrdTxtWtchr;

    private Word word; // Object that holds selected word's features.

    public FragmentDialogAddWrdDef(String setName, FragmentWordDef fragmentWordDef) {
//        this.pnlWrdDefVrt=pnlWrdDefVrt;
        this.setName=setName;
        word=new Word();
        this.fragmentWordDef = fragmentWordDef;
    }

    // FIRST CALLED
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    private void setView() {
        WindowManager.LayoutParams lp=getDialog().getWindow().getAttributes();
        lp.width=MTCH_PRNT;
        lp.height=MTCH_PRNT;
        getDialog().getWindow().setLayout(MTCH_PRNT,MTCH_PRNT);
    }

    private void setBckGrnd() {
        if(getDialog() !=null && getDialog().getWindow() !=null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    //2ND CALLED
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_word_def_add,container);
        svDlgAddWordDef=view.findViewById(R.id.svDlgAddWordDef);

        addWrdDefLstnr = new BtnAddWrdDefLstnr(FragmentDialogAddWrdDef.this,setName);

        etDlgAddWrdStr = view.findViewById(R.id.etDlgAddWrd);
        etDlgAddDef = view.findViewById(R.id.etDlgAddDef);
        etDlgAddExmp=view.findViewById(R.id.etDlgAddExmp);
        etDlgAddLang= view.findViewById(R.id.etDlgAddLang);
        etDlgAddKind= view.findViewById(R.id.etDlgAddKind);
        spinnerPts=view.findViewById(R.id.spnrPtsDlgAdd);

        btnAddWrdDef = view.findViewById(R.id.btnDlgAddWrdSetAdd);
        btnCncl = view.findViewById(R.id.btnDlgAddWrdSetCncl);
        btnDsplyTdk=view.findViewById(R.id.btnDsplyTdkAdd);
        pbTdk=view.findViewById(R.id.pbTdkAdd);
        setStyle();
        setBckGrnd();
        return view;
    }

    private void setStyle() {
       SPEditor sp=new SPEditor();
        svDlgAddWordDef.getBackground().setColorFilter(
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(new SPEditor().getInt(getContext(), SPEditor.COL_GENERAL_BG), BlendModeCompat.SRC_ATOP));
        pbTdk.getIndeterminateDrawable().setTint(new SPEditor().getInt(getContext()
                ,SPEditor.COL_WORDDEF));

        if(sp.getString(getContext(), SPEditor.DIALOG_APPEARANCE).equals(DIALOG_SIMPLE)) {
            etDlgAddExmp.setVisibility(View.GONE);
            etDlgAddKind.setVisibility(View.GONE);
            etDlgAddLang.setVisibility(View.GONE);
        }else {
            etDlgAddExmp.setVisibility(View.VISIBLE);
            etDlgAddKind.setVisibility(View.VISIBLE);
            etDlgAddLang.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnDsplyTdk.setOnClickListener(new TdkBtnLstner(FragmentDialogAddWrdDef.this));
        btnCncl.setOnClickListener(new BtnCnclWrdDefLstenr(this));
        btnAddWrdDef.setOnClickListener(addWrdDefLstnr);

        etWrdTxtWtchr=new TdkTxtWatcher(FragmentDialogAddWrdDef.this, btnDsplyTdk, pbTdk, etDlgAddDef);
        etDlgAddWrdStr.addTextChangedListener(etWrdTxtWtchr);
    }

    @Override
    public void onResume() {
        super.onResume();
        setView();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public EditText getEtWrd() {
        return etDlgAddWrdStr;
    }

    @Override
    public EditText getEtDef() {
        return etDlgAddDef;
    }

    @Override
    public EditText getEtLang() {
        return etDlgAddLang;
    }

    @Override
    public EditText getEtKind() {
        return etDlgAddKind;
    }

    @Override
    public EditText getEtExmp() {
        return etDlgAddExmp;
    }

    @Override
    public Word getWordObj() {
        return word;
    }

    @Override
    public void setWordObj(Word wordObj) {
        this.word= wordObj;
    }

    public Button getBtnDsplyTdk() {
        return btnDsplyTdk;
    }

    @Override
    public void setTdkWordList(ArrayList<TdkWord> tdkWordList) {
        this.tdkWordList=tdkWordList;
    }

    @Override
    public ArrayList<TdkWord> getTdkWordList() {
        return this.tdkWordList;
    }

    @Override
    public ProgressBar getPbTdk() {
        return pbTdk;
    }

    public void removeEtWrdTxtWtcher() {
        System.out.println("--Txt Watcher Removed!");
        etDlgAddWrdStr.removeTextChangedListener(etWrdTxtWtchr);
    }

    public void reAttachEtWrdListener() {
        System.out.println("--Txt Watcher Reattached!");
        etDlgAddWrdStr.addTextChangedListener(etWrdTxtWtchr);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class BtnCnclWrdDefLstenr implements View.OnClickListener {

        private final FragmentDialogAddWrdDef dialog;
        public BtnCnclWrdDefLstenr(FragmentDialogAddWrdDef dialog) { this.dialog=dialog;}

        @Override
        public void onClick(View view) {
            dialog.dismiss();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class BtnAddWrdDefLstnr implements View.OnClickListener {

        private final FragmentDialogAddWrdDef dialog;
//        private final LinearLayout pnlWrdDefVrt;
        private final String setName;
        private final String appearance= new SPEditor().getString(getContext(), SPEditor.APPEARANCE);

        public BtnAddWrdDefLstnr(FragmentDialogAddWrdDef dialog,String setName) {
            this.dialog=dialog;
//            this.pnlWrdDefVrt=pnlWrdDefVrt;
            this.setName=setName;
        }
        int pts;
        @Override
        public void onClick(View view) {
            String wrdStr = ((EditText) view.getRootView().findViewById(R.id.etDlgAddWrd)).getText().toString();
            String defStr= ((EditText) view.getRootView().findViewById(R.id.etDlgAddDef)).getText().toString();
            String exmpStr=((EditText) view.getRootView().findViewById(R.id.etDlgAddExmp)).getText().toString();
            String kindStr=((EditText) view.getRootView().findViewById(R.id.etDlgAddKind)).getText().toString();
            String langStr=((EditText) view.getRootView().findViewById(R.id.etDlgAddLang)).getText().toString();
            pts = spinnerPts.getSelectedItemPosition()+1;

            if (isEmpty(wrdStr))
                Toast.makeText(view.getContext(), getContext().getString(R.string.input_word)
                        , Toast.LENGTH_SHORT).show();
            else {
                getWordObj().setWrd(wrdStr);
                getWordObj().setDef(defStr);
                getWordObj().setPts(pts);
                if(exmpStr!=null) getWordObj().setExmp(exmpStr);
                if(kindStr!=null) getWordObj().setKind(kindStr);
                if(langStr!=null) getWordObj().setLang(langStr);

                if( new WorddefManager().operate(getContext()).add(word.getWrd(), new WordOperator().convert2Json(getWordObj()))) {
//                    new BuilderEditor().getUiEditor(getContext(), setName).updateScreen();
//                    dialog.dismiss();
                    fragmentWordDef.getWords().add(getWordObj());
                    fragmentWordDef.updateUi();
                    dialog.dismiss();
                }


            }
        }

        private boolean isEmpty(String txt) {
            Pattern p = Pattern.compile("\\w");
            Matcher m = p.matcher(txt);

            return !m.find();
        }
    }
}

