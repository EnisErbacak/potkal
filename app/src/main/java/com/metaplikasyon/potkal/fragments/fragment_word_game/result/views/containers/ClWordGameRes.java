package com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.containers;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews.TvWordGameResCnt;
import com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews.TvWordGameResLetter;
import com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews.TvWordGameResPts;
import com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews.TvWordGameResWord;
import com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews.TvWordGameResWrdGained;
import com.metaplikasyon.potkal.other.PixelConverter;

public class ClWordGameRes extends ConstraintLayout {

    private int letterCount, pts;
    private String word;
    private Context context;
    private TvWordGameResWord tvWordGameResWord;
    private TvWordGameResCnt tvWordGameResCnt;
    private TvWordGameResPts tvWordGameResPts;
    private TvWordGameResWrdGained tvWordGameResWrdGained;
    private TvWordGameResLetter tvWordGameResLetter;
    private PixelConverter pc;

    public ClWordGameRes(@NonNull Context context,String word, int letterCount, int pts, int ptsGained, int shownLetterCnt) {
        super(context);
        this.context=context;
        this.letterCount = letterCount;
        this.word=word;
        this.pts = pts;

        this.tvWordGameResWord=new TvWordGameResWord(context, word);
        this.tvWordGameResCnt=new TvWordGameResCnt(context, letterCount);
        this.tvWordGameResPts=new TvWordGameResPts(context, pts);
        tvWordGameResLetter=new TvWordGameResLetter(context, shownLetterCnt);
        this.tvWordGameResWrdGained=new TvWordGameResWrdGained(context, ptsGained);
        this.pc=new PixelConverter(context);
        onCreate();
    }

    private void onCreate() {
        this.setId(ConstraintLayout.generateViewId());
        setStyle();
    }

    private void setStyle() {
        ConstraintLayout.LayoutParams lp=new LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0,pc.dp2Px(5),0,pc.dp2Px(5));
        setLayoutParams(lp);
        locateViews();
    }

    private void locateViews() {
        ConstraintSet constraintSet=new ConstraintSet();
        addView(tvWordGameResWord);
        addView(tvWordGameResCnt);
        addView(tvWordGameResPts);
        addView(tvWordGameResWrdGained);
        addView(tvWordGameResLetter);
        //word..............letterCount - ptsGained - pts

        constraintSet.clone(this);



        constraintSet.connect(tvWordGameResWord.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(tvWordGameResWord.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResWord.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);



        constraintSet.connect(tvWordGameResCnt.getId(),ConstraintSet.END,   tvWordGameResPts.getId(),ConstraintSet.START, pc.dp2Px(7));
        constraintSet.connect(tvWordGameResCnt.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResCnt.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);


        constraintSet.connect(tvWordGameResPts.getId(),ConstraintSet.END,     tvWordGameResLetter.getId(),ConstraintSet.START, pc.dp2Px(5));
        constraintSet.connect(tvWordGameResPts.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResPts.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);



        constraintSet.connect(tvWordGameResLetter.getId(),ConstraintSet.END,     tvWordGameResWrdGained.getId(),ConstraintSet.START, pc.dp2Px(5));
        constraintSet.connect(tvWordGameResLetter.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResLetter.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(tvWordGameResWrdGained.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END);
        constraintSet.connect(tvWordGameResWrdGained.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResWrdGained.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);


        constraintSet.connect(tvWordGameResWord.getId(),ConstraintSet.END,     tvWordGameResCnt.getId(),ConstraintSet.START);
        /*
        constraintSet.connect(tvWordGameResWord.getId(), ConstraintSet.START,  this.getId(),ConstraintSet.START);
        constraintSet.connect(tvWordGameResWord.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResWord.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);


        constraintSet.connect(tvWordGameResCnt.getId(),ConstraintSet.START,   tvWordGameResWord.getId(),ConstraintSet.END, pc.dp2Px(7));
        constraintSet.connect(tvWordGameResCnt.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResCnt.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);


        constraintSet.connect(tvWordGameResPts.getId(),ConstraintSet.START,     tvWordGameResCnt.getId(),ConstraintSet.END, pc.dp2Px(5));
        constraintSet.connect(tvWordGameResPts.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResPts.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);


        constraintSet.connect(tvWordGameResWrdGained.getId(),ConstraintSet.END,     this.getId(),ConstraintSet.END);
        constraintSet.connect(tvWordGameResWrdGained.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResWrdGained.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);

        constraintSet.connect(tvWordGameResLetter.getId(),ConstraintSet.END,     tvWordGameResWrdGained.getId(),ConstraintSet.START, pc.dp2Px(5));
        constraintSet.connect(tvWordGameResLetter.getId(),ConstraintSet.BOTTOM,  this.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(tvWordGameResLetter.getId(),ConstraintSet.TOP,     this.getId(),ConstraintSet.TOP);


        constraintSet.connect(tvWordGameResWord.getId(),ConstraintSet.END,     tvWordGameResCnt.getId(),ConstraintSet.START);
        //constraintSet.connect(tvWordGameResCnt.getId(),ConstraintSet.END,     tvWordGameResPts.getId(),ConstraintSet.START);
        //constraintSet.connect(tvWordGameResPts.getId(),ConstraintSet.END,     tvWordGameResWrdGained.getId(),ConstraintSet.START);
        //constraintSet.connect(tvWordGameResWrdGained.getId(),ConstraintSet.START,     tvWordGameResPts.getId(),ConstraintSet.END);
        */

        constraintSet.applyTo(ClWordGameRes.this);
    }
}
