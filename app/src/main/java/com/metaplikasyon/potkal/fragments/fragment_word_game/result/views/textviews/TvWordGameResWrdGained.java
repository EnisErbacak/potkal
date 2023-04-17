package com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews;

import android.content.Context;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView.TvWordDefSuper;

public class TvWordGameResWrdGained extends TvWordGameSuper {

    public TvWordGameResWrdGained(Context context, int pts) {
        super(context);
        String symbol=pts>=0 ? "+":"";
        setText(symbol+String.valueOf(pts));
    }
}