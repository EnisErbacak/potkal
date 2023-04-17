package com.metaplikasyon.potkal.tdk;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.CustomDialogFragment;

public class TdkTxtWatcher implements TextWatcher {

    private final CustomDialogFragment customDialogFragment;
    private final Button btnDsply;
    private final TextView txtViewDef;
    private String wrd;
    private Thread fetcher1,fetcher2;
    private boolean turn1=true;

    private final ProgressBar pbTdk;

    public TdkTxtWatcher(CustomDialogFragment customDialogFragment, Button btnDsply, ProgressBar pbTdk, TextView txtViewDef) {
        this.customDialogFragment = customDialogFragment;
        this.btnDsply = btnDsply;
        this.txtViewDef=txtViewDef;
        this.pbTdk=pbTdk;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        pbTdk.setAlpha(1);
        btnDsply.setEnabled(false);
        btnDsply.setClickable(false);
        btnDsply.setText(btnDsply.getContext().getResources().getString(R.string.tdk_results));
    }

    @Override
    public void afterTextChanged(Editable editable) {

        wrd=editable.toString();
        if(!wrd.equals("")) {
            if (turn1) {
                if(fetcher2!=null)
                    if(! fetcher2.isInterrupted())
                        fetcher2.interrupt();

                fetcher1 = new Fetcher1(customDialogFragment, btnDsply, txtViewDef, wrd);
                fetcher1.start();
                turn1=false;
                //break;
            } else {
                if(fetcher1!=null)
                    if(! fetcher1.isInterrupted())
                        fetcher1.interrupt();

                fetcher2 = new Fetcher2(customDialogFragment, btnDsply, txtViewDef, wrd);
                fetcher2.start();
                turn1=true;
                //break;
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class Fetcher1 extends Thread {
        private final CustomDialogFragment customDialogFragment;
        private final Button btnDsply;
        private final TextView txtViewDef;
        private final String wrd;

        public Fetcher1(CustomDialogFragment customDialogFragment,Button btnDsply, TextView txtViewDef, String wrd) {
            this.customDialogFragment = customDialogFragment;
            this.btnDsply = btnDsply;
            this.txtViewDef = txtViewDef;
            this.wrd = wrd;
        }

        @Override
        public void run() {
            try {
                System.out.println("++++++T1 STARTED");
                sleep(1000);
                new TdkManager(wrd, customDialogFragment,btnDsply,pbTdk).search();
                System.out.println("++++++1  DONE");
                this.interrupt();
            } catch (InterruptedException e) {
                System.out.println("++++++1  Dead");
                this.interrupt();
            }
        }
    }

    private class Fetcher2 extends Thread {
        private final CustomDialogFragment customDialogFragment;
        private final Button btnDsply;
        private final TextView txtViewDef;
        private final String wrd;

        public Fetcher2(CustomDialogFragment customDialogFragment,Button btnDsply, TextView txtViewDef, String wrd) {
            this.customDialogFragment = customDialogFragment;
            this.btnDsply = btnDsply;
            this.txtViewDef = txtViewDef;
            this.wrd = wrd;
        }

        @Override
        public void run() {
            try {
                System.out.println("------T2 STARTED");
                sleep(1000);
                new TdkManager(wrd, customDialogFragment,btnDsply,pbTdk).search();
                System.out.println("------T2   DONE");
                this.interrupt();
            } catch (InterruptedException e) {
                System.out.println("------T2  Dead");
                this.interrupt();
            }
        }
    }
}