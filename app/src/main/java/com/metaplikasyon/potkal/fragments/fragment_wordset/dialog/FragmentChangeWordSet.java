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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.operator.FileManager;
import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_wordset.editor.UiEdtrWrdSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.manager.WordsetManager;
import com.metaplikasyon.potkal.reaction.Reactor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentChangeWordSet  extends AppCompatDialogFragment {
    private final int MTCH_PRNT= WindowManager.LayoutParams.MATCH_PARENT;

    private  LinearLayout llDlgChngWrdSet;

    private Button btnDlgChngWrdSet,btnDlgChngWrdSetCncl;
    private EditText etDlgChngWrdSet;
    private String oldSetName;

    public FragmentChangeWordSet(String oldSetName) {
        this.oldSetName=oldSetName;
    }

    private void setView() {
        getDialog().getWindow().setLayout(MTCH_PRNT,MTCH_PRNT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_wordset_change,container);
        llDlgChngWrdSet=view.findViewById(R.id.llDlgChngWrdSet);
        btnDlgChngWrdSet=view.findViewById(R.id.btnDlgChngWrdSet);
        btnDlgChngWrdSetCncl=view.findViewById(R.id.btnDlgChngWrdSetCncl);
        etDlgChngWrdSet=view.findViewById(R.id.etDlgChngWrdSet);

        return view;
    }

    private void setStyle() {
        //llDlgChngWrdSet.getBackground().setColorFilter( new SPEditor().getInt(getContext(), SPEditor.COL_GENERAL_BG), PorterDuff.Mode.SRC_ATOP);
        llDlgChngWrdSet.getBackground().setColorFilter(
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(new SPEditor().getInt(getContext(), SPEditor.COL_GENERAL_BG), BlendModeCompat.SRC_ATOP));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDlgChngWrdSet.setOnClickListener(new BtnAddLstnr());
        btnDlgChngWrdSetCncl.setOnClickListener(new BtnCnclLstnr());
        setBckGrnd();
        setStyle();
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

        String newWordSetName;
        @Override
        public void onClick(View view) {
            newWordSetName =etDlgChngWrdSet.getText().toString();
            WordsetManager wordsetManager=new WordsetManager();

            if(!isEmpty(newWordSetName)) {
                if (!new FileManager().explore().checkDuplication(new PathPicker(view.getContext()).get(PathPicker.WORDSET), newWordSetName)) {
                    wordsetManager.operate(view.getContext())
                            .rename(oldSetName, newWordSetName);
                    //view.setText(editTextsetName.getText().toString());
                    WordsetManager manager=new WordsetManager();


                    FragmentChangeWordSet.this.dismiss();
                    new UiEdtrWrdSet(getContext()).updateScrn(FragmentWordDef.ORDER_BY);
                } else {
                    Toast.makeText(view.getContext(), getContext().getResources().getString(R.string.set_exists), Toast.LENGTH_SHORT).show();
                }
            }
            else
                new Reactor(getContext()).showShort("PLEASE INPUT A NAME!");
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
            FragmentChangeWordSet.this.dismiss();
        }
    }

}
