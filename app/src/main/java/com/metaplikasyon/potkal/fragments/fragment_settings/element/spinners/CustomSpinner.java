package com.metaplikasyon.potkal.fragments.fragment_settings.element.spinners;


import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.other.PixelConverter;

import java.util.ArrayList;

public abstract class CustomSpinner extends androidx.appcompat.widget.AppCompatSpinner {
    private Context context;
    private SPEditor sp;
    private PixelConverter pc;
    public CustomSpinner(Context context) {
        super(context);
        this.context=context;
        sp=new SPEditor();
        pc=new PixelConverter(context);
        onCreate();
    }

    private void onCreate() {
        setLayoutParams(new LinearLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, LayoutParams.WRAP_CONTENT));
        setTextAlignment(TEXT_ALIGNMENT_TEXT_END);
        setStyle();
    }

    public abstract void setLastSelecedItem(ArrayAdapter<Object> arrayAdapter);
    public abstract void initialize();



    public ArrayAdapter<Object> getNumericArrayadapter(Context context, int start, int end, int by) {
        ArrayList<Object> items= new ArrayList<Object>();
        for(int i=start;i<end;i+=by)
            items.add(i);
        ArrayAdapter<Object> arrayAdapter= new ArrayAdapter<Object>(context, android.R.layout.simple_spinner_item,items) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v=super.getView(position,convertView,parent);

                ((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                ((TextView) v).setTextColor(sp.getInt(context, SPEditor.COL_SETTINGS_PANEL));

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                v.setTextAlignment(TEXT_ALIGNMENT_TEXT_END);

                v.setBackgroundResource(R.drawable.bg_popup);
                ((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

                ((TextView) v).setTextColor(ContextCompat.getColor(context,R.color.beige));
                ((TextView) v).setTextAlignment(TEXT_ALIGNMENT_CENTER);

                return v;
            }
        };

        return arrayAdapter;
    }

    public ArrayAdapter<Object> getStringArrayAdapter(Context context, String[] arrayStr) {
        return new ArrayAdapter<Object>(context, android.R.layout.simple_spinner_item, arrayStr);
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        setPopupBackgroundResource(R.drawable.bg_popup);
        getBackground().setTint(ContextCompat.getColor(context,R.color.dark_blue));

        setGravity(Gravity.END);
    }
}
