package com.metaplikasyon.potkal.tdk.fragment.views.textview;

import android.content.Context;
import android.view.View;

import com.metaplikasyon.potkal.tdk.fragment.views.container.TdkPropContainerLl;

public class TvTdkSuper extends androidx.appcompat.widget.AppCompatTextView {
    public TvTdkSuper(Context context) {
        super(context);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TdkPropContainerLl)getParent()).onClick();
            }
        });
    }
}
