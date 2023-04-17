package com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.popup.PopupEditWorddef;

public class TvWord extends androidx.appcompat.widget.AppCompatTextView {

    private final String setName;

    private final String word;
    public TvWord(@NonNull Context context, String word) {
        super(context);
        this.word=word;
        this.setName= FragmentWordDef.setName;
        onCreate();
    }

    void onCreate() {
        setText(word);
        setOnLongClickListener(new ViewWordLongPressListener());
        setStyle();
    }

    void setStyle() {
        SPEditor sp=new SPEditor();
        setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        //((LinearLayout.LayoutParams) getLayoutParams()).setMargins(0,0,getDip(5),0);

        setTypeface(Typeface.DEFAULT_BOLD);
        setPaintFlags(getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        setTextSize(sp.getInt(getContext(), SPEditor.TXT_SIZE_WORD));
        setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDDEF_WORD_TXT));
    }

    private int getDip(int value){return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics()));}

    private class ViewWordLongPressListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            PopupEditWorddef menu = new PopupEditWorddef(getContext(), (TvWord) view, setName);
            menu.show();
            return false;
        }
    }
}
