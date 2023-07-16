package com.metaplikasyon.potkal.fragments.fragment_wordset.views.container;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.manager.WorddefManager;
import com.metaplikasyon.potkal.fragments.fragment_wordset.views.txt_view.SuperTvRght;
import com.metaplikasyon.potkal.fragments.fragment_wordset.views.txt_view.TvWordCount;
import com.metaplikasyon.potkal.fragments.fragment_wordset.views.txt_view.TvWordsetLeft;
import com.metaplikasyon.potkal.other.PixelConverter;

import java.io.File;

//********REFACTORED
public class ContainerWrdset extends ConstraintLayout {
    private final PixelConverter pixelConverter;
    // Margin values
    private final int VAL_MRGN_LFT= 10;
    private final int VAL_MRGN_TOP=7;
    private final int VAL_MRGN_RGHT= 10;
    private final int VAL_MRGN_BTTM=7;

    // Padding values
    private final int VAL_PAD_LFT= 5;
    private final int VAL_PAD_TOP= 7;
    private final int VAL_PAD_RGHT= 0;
    private final int VAL_PAD_BTTM= 7;

    private  int COL_BG;

    private final int VAL_CRNR_RDS= 15;

    private ConstraintSet constraintSet;


    private TvWordsetLeft txtViewWrdSet;
    private ContainerInnerRght containerInnerRght;
    private ContainerInnerLft containerInnerLft;
    private final String setName;
    private int countWord;
    private final boolean attachListener;

    public ContainerWrdset(final Context context, final String setName, boolean attachListener) {
        super(context);
        this.setName=setName;
        this.attachListener=attachListener;
        pixelConverter=new PixelConverter(context);
        onCreate(context);
    }

    public ContainerWrdset(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setName="Kitap1";
        this.attachListener=false;
        pixelConverter=new PixelConverter(context);
        onCreate(context);
    }

    private void onCreate(Context context) {
        this.setId(ConstraintLayout.generateViewId());
        COL_BG= new SPEditor().getInt(context, SPEditor.COL_WORDSET);

        this.constraintSet=new ConstraintSet();
        txtViewWrdSet=new TvWordsetLeft(getContext(),setName, attachListener);

        containerInnerLft =new ContainerInnerLft(getContext(),new View[] {txtViewWrdSet});
        countWord= new WorddefManager().explore(context).getCount(new PathPicker(context).get(PathPicker.WORDSET)+ File.separator +setName );
        containerInnerRght =new ContainerInnerRght(getContext(),new SuperTvRght[]{new TvWordCount(getContext(),countWord)});
        setStyle();
    }

    public void setStyle() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
                //ConstraintLayout.LayoutParams.MATCH_PARENT);
                LayoutParams.WRAP_CONTENT);
        lp.setMargins(pixelConverter.dp2Px(VAL_MRGN_LFT), pixelConverter.dp2Px(VAL_MRGN_TOP)
                , pixelConverter.dp2Px(VAL_MRGN_RGHT), pixelConverter.dp2Px(VAL_MRGN_BTTM));
        setPadding(pixelConverter.dp2Px(VAL_PAD_LFT), pixelConverter.dp2Px(VAL_PAD_TOP)
                , pixelConverter.dp2Px(VAL_PAD_RGHT), pixelConverter.dp2Px(VAL_PAD_BTTM));
        setLayoutParams(lp);
        setBackground(getGradientDrawable(COL_BG, pixelConverter.dp2Px(VAL_CRNR_RDS)));
        locateSubPanels();
        setElevation(0);
    }

    private void locateSubPanels() {
        this.addView(containerInnerLft);
        this.addView(containerInnerRght);
        constraintSet.clone(this);

        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(containerInnerLft.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(containerInnerLft.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(containerInnerRght.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END,pixelConverter.dp2Px(10));
        constraintSet.connect(containerInnerRght.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(containerInnerRght.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP,0);


        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.END,   containerInnerRght.getId(), ConstraintSet.START);
        constraintSet.connect(containerInnerRght.getId(), ConstraintSet.START, containerInnerLft.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }


    private Drawable getGradientDrawable(int color,int radius) {
        GradientDrawable gradientDrawable=new GradientDrawable();

        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    public ContainerInnerRght getContainerInnerRght() {
        return containerInnerRght;
    }

    public ContainerInnerLft getContainerInnerLft() {
        return containerInnerLft;
    }

    public String getSetName() {
        return setName;
    }
}
