package com.metaplikasyon.potkal.fragments.fragment_test.test_screen.views.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.metaplikasyon.potkal.fragments.fragment_test.test_screen.test_process.Choice;

public class ButtonChoice extends androidx.appcompat.widget.AppCompatButton {
    private Choice choice;

    public ButtonChoice(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    void onCreate() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                choice.select();
            }
        });
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }
}