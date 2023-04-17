package com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews;

import android.content.Context;

public class TvWordGameResLetter extends TvWordGameSuper{
    public TvWordGameResLetter(Context context, int count) {
        super(context);
        setText("-"+String.valueOf(count)+" Harf");
    }
}
