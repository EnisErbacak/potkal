package com.metaplikasyon.potkal.tdk.fragment.views.container;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.CustomDialogFragment;
import com.metaplikasyon.potkal.other.PixelConverter;
import com.metaplikasyon.potkal.tdk.fragment.FragmentTdk;
import com.metaplikasyon.potkal.tdk.fragment.views.textview.TvTdkDef;
import com.metaplikasyon.potkal.tdk.fragment.views.textview.TvTdkExmp;
import com.metaplikasyon.potkal.tdk.fragment.views.textview.TvTdkKind;

public class TdkPropContainerLl extends LinearLayout {
    private final String def;
    private final String kind;
    private final String exmp;
    private final TdkMainContainerLl mainContainerLl;
    private final CustomDialogFragment customDialogFragment;
    private final FragmentTdk fragmentTdk;
    private PixelConverter pc;
    public TdkPropContainerLl(Context context, String def, String kind, String exmp, TdkMainContainerLl mainContainerLl) {
        super(context);
        this.def=def;
        this.exmp=exmp;
        this.kind=kind;
        this.mainContainerLl=mainContainerLl;
        this.customDialogFragment=mainContainerLl.getCustomDialogFragment();
        this.fragmentTdk=mainContainerLl.getFragmentTdk();
        pc=new PixelConverter(context);
        onCreate();
    }

    private void onCreate() {
        setStyle();
        addChildren();
    }

    private void addChildren() {
        if(kind!=null)  addView(new TvTdkKind(getContext(), kind));
        addView(new TvTdkDef(getContext(), def, TdkPropContainerLl.this, mainContainerLl));
        if(exmp!=null) addView(new TvTdkExmp(getContext(), exmp));
    }

    private void setStyle() {
        setOrientation(VERTICAL);
        LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,pc.dp2Px(5), 0, pc.dp2Px(5));
        setLayoutParams(lp);
        //setBackground(getContext().getResources().getDrawable(R.drawable.bg_tdk_def));
        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_tdk_def));
    }

    public void onClick() {
        customDialogFragment.removeEtWrdTxtWtcher();

        customDialogFragment.getEtWrd().setText(mainContainerLl.getStrWrd());
        customDialogFragment.getEtExmp().setText(getExmp());
        customDialogFragment.getEtLang().setText(mainContainerLl.getLang());
        customDialogFragment.getEtKind().setText(getKind());
        customDialogFragment.getEtDef().setText(getDef());

        customDialogFragment.reAttachEtWrdListener();
        fragmentTdk.dismiss();
    }

    public String getDef() {
        return def;
    }

    public String getKind() {
        return kind;
    }

    public String getExmp() {
        return exmp;
    }
}
