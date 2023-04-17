package com.metaplikasyon.potkal.tdk.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.CustomDialogFragment;
import com.metaplikasyon.potkal.tdk.fragment.views.container.TdkMainContainerLl;
import com.metaplikasyon.potkal.tdk.process.TdkWord;

import java.util.ArrayList;

public class FragmentTdk extends DialogFragment {
    private LinearLayout llTdkMain;
    private final CustomDialogFragment customDialogFragment;
    private final ArrayList<TdkWord> tdkWordList;

    public FragmentTdk(CustomDialogFragment customDialogFragment) {
        this.customDialogFragment = customDialogFragment;
        this.tdkWordList=customDialogFragment.getTdkWordList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tdk,container);

        llTdkMain=view.getRootView().findViewById(R.id.llTdkMain);
        llTdkMain.removeAllViews();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addResult();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void addResult() {
        for(TdkWord tdkWord: tdkWordList) {
            llTdkMain.addView(new TdkMainContainerLl(getContext(),tdkWord.getWordStr(), tdkWord.getLang(), tdkWord.getPropertyList(), customDialogFragment, FragmentTdk.this));
        }
    }
}