package com.metaplikasyon.potkal.fragments.fragment_test;

import android.view.KeyEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class FragmentListenerBackPress implements View.OnKeyListener {
    private final Fragment currentFragment;
    private final Fragment prevFragment;

    public FragmentListenerBackPress(Fragment currentFragment, Fragment prevFragment) {
        this.currentFragment = currentFragment;
        this.prevFragment=prevFragment;
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if(keyEvent.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
            ((FragmentActivity)view.getContext()).getSupportFragmentManager().popBackStackImmediate();
            return true;
        }
        return false;
    }
}
