package com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView;


import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.popup.PopupEditWorddef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed.LlWordDefSuper;

public class TvWordDefSuper extends androidx.appcompat.widget.AppCompatTextView {
    public TvWordDefSuper(@NonNull Context context) {
        super(context);
        onCreate();
    }

    void onCreate() {
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                PopupEditWorddef menu = new PopupEditWorddef(getContext(), ((LlWordDefSuper)getParent()).getTvWord(), FragmentWordDef.setName);
//                menu.show();
                return false;
            }
        });
    }
}
