package com.metaplikasyon.potkal.fragments.fragment_word_game.result.views.textviews;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class TvWordGameSuper extends androidx.appcompat.widget.AppCompatTextView {
    public TvWordGameSuper(Context context) {
        super(context);
        onCreate();
    }

    public void onCreate() {
        ConstraintLayout.LayoutParams lp=new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, LinearLayout.LayoutParams.MATCH_PARENT);
        setLayoutParams(lp);
        this.setId(generateViewId());
        setStyle();
    }

    public void setStyle() {
        setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_WORDGAME_TXT));
    }
}
