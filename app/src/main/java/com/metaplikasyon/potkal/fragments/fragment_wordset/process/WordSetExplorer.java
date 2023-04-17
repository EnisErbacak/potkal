package com.metaplikasyon.potkal.fragments.fragment_wordset.process;

import android.content.Context;

import com.metaplikasyon.potkal.file.operator.FileManager;

import java.util.ArrayList;

public class WordSetExplorer  {
    private final FileManager fileManager;
    private Context context;
    private String dirWordSet;

    public WordSetExplorer() {
        fileManager=new FileManager();
    }

    public int getCount(String path) {
        return fileManager.explore().getFileCount(path);
    }

    public boolean checkDuplication(String dir, String name) {
        return !fileManager.explore().checkDuplication(dir, name);
    }

    public ArrayList<String> getNames(String dir) {
        return fileManager.explore().getFileNames(dir);
    }
}
