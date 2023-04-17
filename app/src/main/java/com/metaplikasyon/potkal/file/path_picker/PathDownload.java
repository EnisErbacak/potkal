package com.metaplikasyon.potkal.file.path_picker;

import android.content.Context;

import java.io.File;

public class PathDownload implements CustomPath{
    private final String FOLDERNAME="download";

    @Override
    public String get(Context context) {
        return context.getFilesDir().getPath() + File.separator + FOLDERNAME;
    }
}
