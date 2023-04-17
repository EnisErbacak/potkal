package com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView.TvWordDefSuper;

public class TvWordGameResCnt extends TvWordGameSuper {
    public TvWordGameResCnt(Context context, int count) {
        super(context);
        setText(String.valueOf(count)+" Harf");
    }
}
