package com.metaplikasyon.potkal.tdk.fragment.views.container;

import android.content.Context;
import android.widget.LinearLayout;

import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.CustomDialogFragment;
import com.metaplikasyon.potkal.other.PixelConverter;
import com.metaplikasyon.potkal.tdk.fragment.FragmentTdk;
import com.metaplikasyon.potkal.tdk.process.TdkProperty;

import java.util.ArrayList;

public class TdkMainContainerLl extends LinearLayout{

    private final String  strWrd;
    private final String lang;
    private final ArrayList<TdkProperty> tdkPropertyList;
    private TdkTitleContainerLl titleContainerLl;
    private final CustomDialogFragment customDialogFragment;
    private final FragmentTdk fragmentTdk;
    private PixelConverter pc;

    public TdkMainContainerLl(Context context, String strWrd, String lang, ArrayList<TdkProperty> tdkPropertyList, CustomDialogFragment customDialogFragment, FragmentTdk fragmentTdk) {
        super(context);
        this.strWrd=strWrd;
        this.tdkPropertyList=tdkPropertyList;
        this.lang=lang;
        this.customDialogFragment=customDialogFragment;
        this.fragmentTdk=fragmentTdk;
        pc=new PixelConverter(context);
        onCreate();
    }

    void onCreate() {
        setStyle();
        addChildren();
    }

    void addChildren() {
        titleContainerLl=new TdkTitleContainerLl(getContext(), strWrd, lang);
        addView(titleContainerLl);
        addView(new TdkDefContainerLl(getContext(), tdkPropertyList, TdkMainContainerLl.this));
    }

    void setStyle() {
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(lp);
        lp.setMargins(0,pc.dp2Px(20),0,0);
        setOrientation(VERTICAL);
    }

    public String getStrWrd() {
        return strWrd;
    }

    public String getLang() {
        return lang;
    }

    public CustomDialogFragment getCustomDialogFragment() {
        return customDialogFragment;
    }

    public FragmentTdk getFragmentTdk() {
        return fragmentTdk;
    }
}