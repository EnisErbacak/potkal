package com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.listner;

import android.graphics.Color;
import android.view.View;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.views.btn.BtnChangeColor;
import com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.views.btn.ColorSampler;
import com.metaplikasyon.potkal.fragments.fragment_settings.fragment_color.views.FragmentRGBPicker;

public class OKListner implements View.OnClickListener{
    private final ColorSampler cs;
    private final FragmentRGBPicker dialog;
    private final String prefKey;
    private final BtnChangeColor btnChangeColor;

    public OKListner(ColorSampler cs, FragmentRGBPicker dialog, BtnChangeColor btnChangeColor, String prefKey) {
        this.cs = cs;
        this.dialog = dialog;
        this.prefKey = prefKey;
        this.btnChangeColor=btnChangeColor;
    }

    @Override
    public void onClick(View view) {
        int[] rgb=cs.getRGB();
        int hex=Color.rgb(rgb[0], rgb[1], rgb[2]);
        new SPEditor().setValues(view.getContext(), prefKey, String.valueOf(hex));
        dialog.dismiss();

        /*
        FragmentManager fm=((FragmentActivity)btnChangeColor.getContext()).getSupportFragmentManager();
        List<FragmentFirstUse0> fList=fm.getFragments();
        for(FragmentFirstUse0 f:fList) {
            FragmentTransaction fragTransaction = fm.beginTransaction();
            fragTransaction.detach(f);
            fragTransaction.attach(f);
            fragTransaction.commit();
        }

         */
        btnChangeColor.update(hex);
    }
}