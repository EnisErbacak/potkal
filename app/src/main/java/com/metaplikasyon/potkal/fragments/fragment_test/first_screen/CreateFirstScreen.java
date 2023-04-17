package com.metaplikasyon.potkal.fragments.fragment_test.first_screen;

import android.content.Context;
import android.widget.LinearLayout;

import com.metaplikasyon.potkal.file.operator.FileManager;
import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_test.first_screen.views.container.ContainerTestWordset;

import java.util.ArrayList;

public class CreateFirstScreen {
    private String dir;
    FileManager fileManager;
    public void create(Context context, LinearLayout ll, int txtColor) {
        fileManager=new FileManager();
        ArrayList<String> fileName=fileManager.explore().getFileNames(new PathPicker(context).get(PathPicker.WORDSET));
        SPEditor sp=new SPEditor();
        for(String str:fileName) {
            ContainerTestWordset c=new ContainerTestWordset(context,str,txtColor);
            ll.addView(c);
            c.getTxtViewWrdSet().setTextColor(txtColor);
            c.getTvTestFirstWordCount().setTextColor(txtColor);
        }
    }
}