package com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView.TvWordDefSuper;
import com.metaplikasyon.potkal.other.PixelConverter;

public class TvWordGameResWord extends TvWordGameSuper {
    public TvWordGameResWord(Context context, String word) {
        super(context);
        setText(word);
    }

    @Override
    public void setStyle() {
        super.setStyle();
    }
}
