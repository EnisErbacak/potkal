package com.metaplikasyon.potkal.fragments.fragment_worddef.views.spinners;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;


import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;

import java.util.ArrayList;

public class SpinnerPts extends androidx.appcompat.widget.AppCompatSpinner {
    private Context context;

    public SpinnerPts(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        Integer colors[] = {1,2,3,4,5};
        initialize();
        setStyle();

    // Application of the Array to the Spinner
        //ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(context,   android.R.layout.simple_spinner_item, colors);
        //spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
      //  setAdapter(spinnerArrayAdapter);
    }

    public SpinnerPts(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initialize();
        setStyle();
    }

    public void setLastSelecedItem(ArrayAdapter<Object> arrayAdapter) {

    }

    public ArrayAdapter<Object> getNumericArrayadapter(Context context, int start, int end, int by) {
        ArrayList<Object> items= new ArrayList<Object>();
        SPEditor sp=new SPEditor();

        for(int i=start;i<end;i+=by)
            items.add(i);
        ArrayAdapter<Object> arrayAdapter= new ArrayAdapter<Object>(context, android.R.layout.simple_spinner_item, items) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v=super.getView(position,convertView,parent);

                ((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                ((TextView) v).setTextColor(sp.getInt(context, SPEditor.COL_WORDDEF_TXT));
                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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

    public void initialize() {
        ArrayAdapter<Object> arrayAdapter= getNumericArrayadapter(context,1,6,1);
        setAdapter(arrayAdapter);
        setLastSelecedItem(arrayAdapter);
    }

    private void setStyle() {
        setPopupBackgroundResource(R.drawable.bg_popup);
        getBackground().setTint(ContextCompat.getColor(context,R.color.dark_blue));
    }
}
