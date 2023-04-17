package com.metaplikasyon.potkal.fragments.fragment_word_game.views.textviews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_word_game.process.GameScreen;
import com.metaplikasyon.potkal.other.PixelConverter;

public class TvWrdGameLetter extends androidx.appcompat.widget.AppCompatTextView {
    private char letter;
    private boolean isShown;
    private PixelConverter pc;
    private SPEditor se;
    private GameScreen gameScreen;
    public TvWrdGameLetter(Context context, char letter, GameScreen gs) {
        super(context);
        this.gameScreen=gs;
        this.pc=new PixelConverter(context);
        this.letter=letter;
        this.se=new SPEditor();
        setStyle();
    }

    private void setStyle() {

        setTextColor(Color.TRANSPARENT);

        setTypeface(Typeface.DEFAULT_BOLD);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setPadding(pc.dp2Px(5),0, pc.dp2Px(5),0 );
        setTextSize(pc.dp2Px(16));
        setWidth(pc.dp2Px(60));
        setHeight(pc.dp2Px(60));

        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setGravity(Gravity.CENTER);
        setLayoutParams(lp);

        if(String.valueOf(letter).equals(" ")) {
            setText("_");
            showLetter();
            gameScreen.setUnShownCnt(gameScreen.getUnShownCnt()-1);
            gameScreen.setShownLetterCnt(gameScreen.getShownLetterCnt()+1);
            gameScreen.setPts(gameScreen.getPts()-gameScreen.getPtsLetter());
        }
        else {
            setText(String.valueOf(letter));
        }
    }

    public void showLetter() {
        setTextColor(se.getInt(getContext(), SPEditor.COL_WORDGAME_COMB_LETTER));
        isShown=true;
    }

    @Override
    public boolean isShown() {
        return isShown;
    }
}
