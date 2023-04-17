package com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.Word;
import com.metaplikasyon.potkal.tdk.process.TdkWord;

import java.util.ArrayList;

public interface CustomDialogFragment {
    Context getContext();
    EditText getEtWrd();
    EditText getEtDef();
    EditText getEtLang();
    EditText getEtKind();
    EditText getEtExmp();
    Button getBtnDsplyTdk();
    void setTdkWordList(ArrayList<TdkWord> tdkWordList);
    ArrayList<TdkWord> getTdkWordList();
    ProgressBar getPbTdk();

    Word getWordObj();
    void setWordObj(Word wordObj);

    void removeEtWrdTxtWtcher();
    void reAttachEtWrdListener();

}
