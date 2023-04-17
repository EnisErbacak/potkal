package com.metaplikasyon.potkal.file.path_picker;

import android.content.Context;

import java.io.File;

public class PathZip implements CustomPath {

    private final String FOLDERNAME="zip_files";

    @Override
    public String get(Context context) {
        return context.getFilesDir().getPath() + File.separator + FOLDERNAME;
    }
}