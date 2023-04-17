package com.metaplikasyon.potkal.fragments.fragment_settings.spinners.other;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_settings.element.spinners.ListenerSpinnerOnSelected;

public class SpinnerOther extends androidx.appcompat.widget.AppCompatSpinner{
    private final Context context;
    private final SPEditor spEditor;
    private final String sharedPrefKey;
    private final String[] codes={"current", "all"};
    private final String[] langs;

    public SpinnerOther(@NonNull Context context, String sharedPrefKey) {
        super(context);
        this.context=context;
        spEditor=new SPEditor();
        this.sharedPrefKey=sharedPrefKey;
        langs=new String[]{context.getResources().getString(R.string.only_on_current_wordset), context.getResources().getString(R.string.on_all_wordset)};
        onCreate();
    }

    public String getLastValue() {
        String value="";
        String last=spEditor.getString(context, sharedPrefKey);

        for(int i=0;i<codes.length;i++) {

            if(codes[i].equals(last)){
                value=langs[i];
                break;
            }
        }
        return  value;
    }

    public void setLastSelecedItem(ArrayAdapter<Object> arrayAdapter) {
        setSelection(arrayAdapter.getPosition(getLastValue()));
    }

    void onCreate() {
        initialize();
        setOnItemSelectedListener(new ListenerSpinnerOnSelected(sharedPrefKey,codes));
    }

    public void initialize() {
        ArrayAdapter<Object> arrayAdapter=new ArrayAdapter<Object>(context, android.R.layout.simple_spinner_item, langs);
        setAdapter(arrayAdapter);
        setLastSelecedItem(arrayAdapter);
    }
}
