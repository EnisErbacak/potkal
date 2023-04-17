package com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.containers.ClWordGameRes;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView.TvLang;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView.TvPts;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView.TvWord;
import com.metaplikasyon.potkal.other.PixelConverter;

public class ClContainerUpper extends ConstraintLayout implements LlWordDefSuper{
    private String strWord, strLang;
    private int ptsInt;
    TextView tvWord, tvLang, tvPts;
    private final Context context;

    public ClContainerUpper(Context context, String strWord, String  strLang, int pts) {
        super(context);
        this.context=context;
        this.strWord=strWord;
        this.strLang=strLang;
        this.ptsInt=pts;
        onCreate();
    }

    public ClContainerUpper(Context context, String strWord) {
        super(context);
        this.context=context;
        this.strWord=strWord;
        onCreate();
    }

    void onCreate() {
        this.setId(generateViewId());
        setStyle();
        addChildren();
        locateChildren();
    }

    void setStyle() {
    }

    void addChildren() {
        createChild();
        TextView[] tvArr={tvWord, tvLang, tvPts};
        for(int i=0;i<tvArr.length;i++) {
            if(tvArr[i]!=null) {
                addView(tvArr[i]);
                tvArr[i].setId(generateViewId());
            }
        }
    }

    void createChild() {
        tvPts=new TvPts(context, ptsInt);
        if(strWord!=null) tvWord=new TvWord(context, strWord);
        if(strLang!=null && !strLang.equals("")) {
            tvLang=new TvLang(context, strLang);
        }
    }

    private void locateChildren() {
        ConstraintSet constraintSet=new ConstraintSet();
        PixelConverter pc=new PixelConverter(context);
            //word..............lang - pts

            constraintSet.clone(this);



            constraintSet.connect(tvWord.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
            constraintSet.connect(tvWord.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
            constraintSet.connect(tvWord.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);


        if(tvLang!=null) {
            constraintSet.connect(tvLang.getId(), ConstraintSet.END, tvPts.getId(), ConstraintSet.START, pc.dp2Px(5));
            constraintSet.connect(tvLang.getId(), ConstraintSet.TOP, tvWord.getId(), ConstraintSet.TOP);
            constraintSet.connect(tvWord.getId(),ConstraintSet.END,     tvLang.getId(),ConstraintSet.START);
        } else
        {
            constraintSet.connect(tvWord.getId(),ConstraintSet.END,     tvPts.getId(),ConstraintSet.START);
        }

            constraintSet.connect(tvPts.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END);
            constraintSet.connect(tvPts.getId(),ConstraintSet.TOP,     tvWord.getId(),ConstraintSet.TOP);



        //if(tvLang!=null) constraintSet.connect(tvWord.getId(),ConstraintSet.END,     tvLang.getId(),ConstraintSet.START);
        //else constraintSet.connect(tvWord.getId(),ConstraintSet.END,     tvPts.getId(),ConstraintSet.START);

            constraintSet.applyTo(this);
    }

    public TvWord getTvWord() {
        return (TvWord)tvWord;
    }

    public String getStrWord() {
        return strWord;
    }
}