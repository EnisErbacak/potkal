package com.metaplikasyon.potkal.fragments.fragment_worddef.process;

import android.content.Context;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.operator.FileManager;
import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.fragments.fragment_wordset.process.WordSetExplorer;
import com.metaplikasyon.potkal.reaction.Reactor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class WordDefExplorer {
    private final FileManager fileManager;
    public WordDefExplorer() {
        this.fileManager=new FileManager();
    }


    public int getCount(String path) {
        int result=-1;
        try {
            JSONObject jObj=new JSONObject(fileManager.operate().read(path));
            result=jObj.length();
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
            result=-1;
        }
        return result;
    }

    public boolean checkDuplication(Context context, String dirOrFile, String name) {
        boolean result=true;
        try {
            JSONObject jObj=new JSONObject(fileManager.operate().read(dirOrFile));
            if(! jObj.isNull(name)) {
                new Reactor(context).showShort(context.getResources().getString(R.string.word_exists));
                result=false;
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> getNames(String dirOrFile) {
        ArrayList<String> names=new ArrayList<String>();
        try {
            JSONObject jObj=new JSONObject(fileManager.operate().read(dirOrFile));
            Iterator<String> iterator=jObj.keys();
            while (iterator.hasNext()) {
                names.add(iterator.next());
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return names;
    }

    public boolean checkDuplicationForAll(Context context, String name) {
        boolean result=true;
        PathPicker pathPicker=new PathPicker(context);
        ArrayList<String> wordsetList=new WordSetExplorer().getNames(pathPicker.get(PathPicker.WORDSET));
        String dir=pathPicker.get(PathPicker.WORDSET);
        for(String str: wordsetList) {

            for(String str2: getNames(dir + File.separator+ str)) {
                if(str2.equals(name)) {
                        new Reactor(context).showShort(context.getResources().getString(R.string.word_exists_in)+ "\""+str+"\"");
                        result=false;
                    }
            }
        }
        return result;
    }
}
