package com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.operator;

import android.content.Context;

import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.UiBuilder;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.UiBuilderDetailed;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.UiBuilderSimple;

public class BuilderEditor {
    private final String appearance=SPEditor.APPEARANCE;


    private String getAppearance(Context context) {
        return  new SPEditor().getString(context,appearance);
    }

    public UiBuilder getUiEditor(Context context, String setName) {
        UiBuilder result=null;

        switch (getAppearance(context).toLowerCase()) {
            case "simple":
                result= new UiBuilderSimple(context, setName);
                break;
            case "detailed":
                result= new UiBuilderDetailed(context, setName);
                break;
        }
        return result;
    }
}
