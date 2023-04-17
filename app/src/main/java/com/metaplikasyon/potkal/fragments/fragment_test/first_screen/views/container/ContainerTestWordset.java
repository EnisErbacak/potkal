package com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.container;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.view.View;
import android.widget.CheckBox;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.ChckBoxTest;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.textviews.TvTestFirstSetName;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.textviews.TvTestFirstWordCount;
import com.metaplikasyon.potkal.fragments.fragment_worddef.manager.WorddefManager;
import com.metaplikasyon.potkal.fragments.fragment_wordset.views.container.ContainerInnerLft;
import com.metaplikasyon.potkal.fragments.fragment_wordset.views.container.ContainerInnerRght;

import java.io.File;

public class ContainerTestWordset extends ConstraintLayout {
    private int COL_BG;

    private ConstraintSet constraintSet;
    private LayoutParams lp;

    private ChckBoxTest checkBox;
    private TvTestFirstSetName txtViewWrdSet;
    private ContainerInnerRght containerInnerRght;
    private ContainerInnerLft containerInnerLft;
    private final String setName;
    private int countWord;
    private TvTestFirstWordCount tvTestFirstWordCount;
    private int color;

    public ContainerTestWordset(final Context context, final String setName, int color) {
        super(context);
        this.setName = setName;
        this.color=color;
        onCreate(context);
    }

    private void onCreate(Context context) {
        this.setId(ConstraintLayout.generateViewId());
        COL_BG = new SPEditor().getInt(context, SPEditor.COL_WORDSET);

        this.constraintSet = new ConstraintSet();
        txtViewWrdSet = new TvTestFirstSetName(getContext(), setName);


        checkBox=new ChckBoxTest(context);
        containerInnerLft = new ContainerInnerLft(getContext(), new View[] {checkBox, txtViewWrdSet});
        countWord = new WorddefManager().explore(context).getCount(new PathPicker(context).get(PathPicker.WORDSET) + File.separator + setName);

        tvTestFirstWordCount=new TvTestFirstWordCount(getContext(), countWord);
        containerInnerRght = new ContainerInnerRght(getContext(), new View[]{tvTestFirstWordCount });
        setStyle();
    }

    public void setStyle() {
        lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);


        if(Build.VERSION.SDK_INT >= 21)
        {
            SPEditor sp=new SPEditor();
            ColorStateList colorStateList = new ColorStateList(
                    new int[][]
                            {
                                    new int[]{-android.R.attr.state_enabled}, // Disabled
                                    new int[]{android.R.attr.state_enabled}   // Enabled
                            },
                    new int[]
                            {
                                    color, // disabled
                                    color   // enabled
                            }
            );

            checkBox.setButtonTintList(colorStateList); // set the color tint list
        }

        setLayoutParams(lp);
        locateSubPanels();
    }

    private void locateSubPanels() {
        this.addView(containerInnerLft);
        this.addView(containerInnerRght);
        constraintSet.clone(this);

        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.START, this.getId(), ConstraintSet.START);
        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.BOTTOM, this.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP);

        constraintSet.connect(containerInnerRght.getId(), ConstraintSet.END, this.getId(), ConstraintSet.END, 0);
        constraintSet.connect(containerInnerRght.getId(), ConstraintSet.BOTTOM, this.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(containerInnerRght.getId(), ConstraintSet.TOP, this.getId(), ConstraintSet.TOP, 0);


        constraintSet.connect(containerInnerLft.getId(), ConstraintSet.END, containerInnerRght.getId(), ConstraintSet.START);
        constraintSet.connect(containerInnerRght.getId(), ConstraintSet.START, containerInnerLft.getId(), ConstraintSet.END);

        constraintSet.applyTo(this);
    }

    public String getSetName() {
        return setName;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TvTestFirstSetName getTxtViewWrdSet() {
        return txtViewWrdSet;
    }

    public TvTestFirstWordCount getTvTestFirstWordCount() {
        return tvTestFirstWordCount;
    }
}
