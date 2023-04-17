package com.metaplikasyon.potkal.reaction;

import android.content.Context;
import android.widget.Toast;

import com.metaplikasyon.potkal.other.ScannerActivity;

public class Reactor {
    private final Context context;

    public Reactor(Context context) {
        this.context = context;
    }

    public void showShort(String msg) {
        new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showLong(String msg) {
        new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
            }
        });
    }
}