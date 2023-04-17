package com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.popup.PopupEditWorddef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView.TvWord;
import com.metaplikasyon.potkal.other.PixelConverter;

public class LlContainerMain extends LinearLayout {
    private final ClContainerUpper clContainerUpper;
    private final LlContainerLower containerLower;
    private final PixelConverter pixelConverter;

    private final int WDTH= ViewGroup.LayoutParams.MATCH_PARENT;
    private final int HGHT=ViewGroup.LayoutParams.WRAP_CONTENT;

    // Values in Dp
    private final int VAL_MRGN_LFT= 10;
    private final int VAL_MRGN_TOP=7;
    private final int VAL_MRGN_RGHT=10;
    private final int VAL_MRGN_BTTM=7;

    private final int VAL_PADDING=7;

    private final int VAL_CRNR_RDS=15;
    //private final int COL_BG= Color.parseColor("#C7E6FB");
    private  int colBg;

    public LlContainerMain(Context context, ClContainerUpper clContainerUpper, LlContainerLower containerLower) {
        super(context);
        this.clContainerUpper = clContainerUpper;
        this.containerLower=containerLower;
        pixelConverter=new PixelConverter(context);
        onCreate();
    }

    public void onCreate() {
        setStyle();
        addChilren();

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupEditWorddef menu = new PopupEditWorddef(getContext(), (TvWord) clContainerUpper.getTvWord(), FragmentWordDef.setName);
                menu.show();
                return false;
            }
        });
    }

    public void setStyle() {
        setOrientation(VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(WDTH, HGHT);
        lp.setMargins(pixelConverter.dp2Px(VAL_MRGN_LFT), pixelConverter.dp2Px(VAL_MRGN_TOP), pixelConverter.dp2Px(VAL_MRGN_RGHT), pixelConverter.dp2Px(VAL_MRGN_BTTM));
        setPadding(pixelConverter.dp2Px(VAL_PADDING), pixelConverter.dp2Px(VAL_PADDING), pixelConverter.dp2Px(VAL_PADDING), pixelConverter.dp2Px(VAL_PADDING));
        setLayoutParams(lp);
        colBg= new SPEditor().getInt(getContext(), SPEditor.COL_WORDDEF);
        setBackground(getGradientDrawable(colBg, pixelConverter.dp2Px(15)));
    }

    void addChilren() {
        addView(clContainerUpper);
        addView(containerLower);
    }

    public TvWord getTvWord() {
        return (TvWord) clContainerUpper.getTvWord();
    }

    public void showLong() {
        TvWord tvWord= (TvWord) clContainerUpper.getTvWord();
        PopupEditWorddef menu = new PopupEditWorddef(getContext(), (TvWord) tvWord, FragmentWordDef.setName);
        menu.show();
    }

    public ClContainerUpper getLlContainerUpper() {
        return clContainerUpper;
    }

    public TextView getTvDef() {
        return containerLower.getTvDef();
    }

    private Drawable getGradientDrawable(int color, int radius) {
        GradientDrawable gradientDrawable=new GradientDrawable();

        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }


}