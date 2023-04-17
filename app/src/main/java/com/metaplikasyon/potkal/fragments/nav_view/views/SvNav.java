package com.metaplikasyon.potkal.fragments.nav_view.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;


import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

public class SvNav extends ScrollView {
    public SvNav(Context context) {
        super(context);
        onCreate();
    }

    public SvNav(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    private void onCreate() {
        setBackgroundColor(new SPEditor().getInt(getContext(),SPEditor.COL_WORDSET_BG));
    }
}
