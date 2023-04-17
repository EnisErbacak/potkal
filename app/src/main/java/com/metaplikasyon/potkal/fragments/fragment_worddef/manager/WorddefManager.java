package com.metaplikasyon.potkal.fragments.fragment_worddef.manager;

import android.content.Context;

import com.metaplikasyon.potkal.fragments.fragment_worddef.process.WordDefExplorer;
import com.metaplikasyon.potkal.fragments.fragment_worddef.process.WordDefOperator;

public class WorddefManager {
    public WordDefOperator operate(Context context){
        return new WordDefOperator(context);
    }

    public WordDefExplorer explore(Context context) {
        return new WordDefExplorer();
    }
}
