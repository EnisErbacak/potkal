package com.metaplikasyon.potkal.tdk;

import android.content.Context;
import android.widget.Button;
import android.widget.ProgressBar;

import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.CustomDialogFragment;
import com.metaplikasyon.potkal.other.ScannerActivity;
import com.metaplikasyon.potkal.tdk.process.Fetch;
import com.metaplikasyon.potkal.tdk.process.Parse;
import com.metaplikasyon.potkal.tdk.process.TdkWord;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TdkManager {

    private final CustomDialogFragment customDialogFragment;
    private final Button btnDisplayTdk;
    private final ProgressBar pbTdk;
    private final String keyWord;
    private final Context context;

    private ArrayList<TdkWord> tdkWordList;

    public TdkManager(String keyWord,CustomDialogFragment customDialogFragment, Button btnDisplayTdk, ProgressBar pbTdk) {
        this.customDialogFragment=customDialogFragment;
        this.keyWord=keyWord;
        this.btnDisplayTdk=btnDisplayTdk;
        this.context=btnDisplayTdk.getContext();
        this.pbTdk=pbTdk;
    }

    public void search() {
        ExecutorService executor= Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Fetch fetch=new Fetch();
                Parse parse=new Parse();

                tdkWordList=parse.parseResult(fetch.fetchWord(keyWord));

                if(tdkWordList.size()!=0) {
                    setView(context, true);
                    customDialogFragment.setTdkWordList(tdkWordList);
                }else {
                    setView(context, false);
                }
            }
        });
    }

    private void setView(Context context, boolean isFound) {
        new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String btnTxt= isFound?"TDK Sonucu":"";
                btnDisplayTdk.setClickable(isFound);
                btnDisplayTdk.setEnabled(isFound);
                btnDisplayTdk.setText(btnTxt);
                pbTdk.setAlpha(0);
            }
        });
    }
}
