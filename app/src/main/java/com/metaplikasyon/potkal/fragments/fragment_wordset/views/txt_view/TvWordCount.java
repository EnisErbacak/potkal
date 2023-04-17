package com.metaplikasyon.potkal.fragments.fragment_wordset.views.txt_view;

import android.content.Context;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;


public class TvWordCount extends SuperTvRght {

    public TvWordCount(Context context, int wordCnt) {
        super(context,String.valueOf(wordCnt));
        setStyle();
    }

    private void setStyle() {
        setTextColor(new SPEditor().getInt(getContext(), SPEditor.COL_WORDSET_TXT));
    }
}
