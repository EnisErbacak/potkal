package com.metaplikasyon.potkal.tdk;

import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.CustomDialogFragment;
import com.metaplikasyon.potkal.tdk.fragment.FragmentTdk;

public class TdkBtnLstner implements View.OnClickListener {

    private final CustomDialogFragment customDialogFragment;

    public TdkBtnLstner(CustomDialogFragment customDialogFragment) {
        this.customDialogFragment=customDialogFragment;
    }

    @Override
    public void onClick(View view) {
            new FragmentTdk(customDialogFragment).show( ((AppCompatDialogFragment)customDialogFragment).getActivity().getSupportFragmentManager(), "Anlamlar");
    }
}
