package com.metaplikasyon.potkal.fragments.fragment_word_game.views.containers;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

public class LlWrdGameClue extends LinearLayout {

    public LlWrdGameClue(Context context) {
        super(context);
        setStyle();
    }

    private void setStyle() {
        LinearLayout.LayoutParams lp=new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,5,0,5);
        setOrientation(HORIZONTAL);
        setLayoutParams(lp);
        setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
    }
}
