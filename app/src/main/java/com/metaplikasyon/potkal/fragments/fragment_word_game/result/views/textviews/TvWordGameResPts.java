package com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView.TvWordDefSuper;


public class TvWordGameResPts extends TvWordGameSuper {
    public TvWordGameResPts(Context context, int pts) {
        super(context);
        setText(String.valueOf(pts)+" Puan");
    }
}

