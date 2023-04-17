package com.metaplikasyon.potkal.file.operator;

import java.io.File;
import java.util.ArrayList;

public class BasicFileExplorer implements FileExplorer {
    @Override
    public ArrayList<String> getFileNames(String dir) {
        ArrayList<String> listFileName=new ArrayList<>();
        File[] files=new File(dir).listFiles();
        if(files!=null) {
            for (int i = 0; i < files.length; i++) {
                listFileName.add(files[i].getName());
            }
        }
        return  listFileName;
    }

    @Override
    public int getFileCount(String dir) {
        int result=0;
        try {
            result=new File(dir).listFiles().length;
        }catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean checkDuplication(String dir, String fileName) {
        boolean result=false;
        File directory = new File(dir);
        File[] files = directory.listFiles();
        if(files!=null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().toLowerCase().equals(fileName.toLowerCase())) {
                    result= true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean checkDir(String dir) {
        boolean result=true;
        File file=new File(dir);
        if(!file.exists() && !file.isDirectory())
            result= false;
        return result;
    }

    @Override
    public boolean checkFile(String dir, String fileName) {
        return new File(dir + File.separator+ fileName).exists();
    }
}