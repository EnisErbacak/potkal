package com.metaplikasyon.potkal.fragments.fragment_word_game.views.containers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_word_game.process.GameScreen;
import com.metaplikasyon.potkal.fragments.fragment_word_game.views.textviews.TvWrdGameLetter;
import com.metaplikasyon.potkal.other.PixelConverter;

public class FlTvWordGameLetterContainer extends FrameLayout {
    private TvWrdGameLetter tvWrdGameLetter;
    private char letter;
    private PixelConverter pc;
    private SPEditor se;

    public FlTvWordGameLetterContainer(@NonNull Context context, char letter, GameScreen gameScreen) {
        super(context);
        tvWrdGameLetter=new TvWrdGameLetter(context, letter,gameScreen);
        this.pc=new PixelConverter(context);
        this.letter=letter;
        this.se=new SPEditor();
        setStyle();
    }

    public FlTvWordGameLetterContainer(@NonNull Context context, @Nullable AttributeSet attrs
    , char letter) {
        super(context, attrs);
        this.pc=new PixelConverter(context);
        this.letter=letter;
        this.se=new SPEditor();
        setStyle();
    }

    public TvWrdGameLetter getTvWrdGameLetter() {
        return tvWrdGameLetter;
    }

    public void setTvWrdGameLetter(TvWrdGameLetter tvWrdGameLetter) {
        this.tvWrdGameLetter = tvWrdGameLetter;
    }

    private void setStyle() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                pc.dp2Px(80),pc.dp2Px(80));
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //setPadding(pc.dp2Px(5),0, pc.dp2Px(5),0 );
        lp.setMargins(pc.dp2Px(5),0,pc.dp2Px(5),0);
//
//        lp.width=(pc.dp2Px(60));
//        lp.height=(pc.dp2Px(60));

        lp.gravity=(Gravity.CENTER);
        setLayoutParams(lp);
        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_panel_rounded));
        getBackground().setTint(se.getInt(getContext(), SPEditor.COL_WORDGAME_COMB_PANEL_BG));

        addView(tvWrdGameLetter);
    }
}
