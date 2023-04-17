package com.metaplikasyon.potkal.file.duplication_handler;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicationSolver implements DuplicationHandler{
    @Override
    public String perform(String fileName, String dir) {

        final String regex = "\\(\\d\\)$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        File[] files=new File(dir).listFiles();
        String newName=fileName;
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            final Matcher matcher = pattern.matcher(name);

            if (name.equals(newName)) {
                if (matcher.find()) {
                    String tmp = matcher.group();
                    tmp = tmp.replace("(", "");
                    tmp = tmp.replace(")", "");
                    int val = Integer.parseInt(tmp) + 1;
                    newName = newName.replaceAll("\\(\\d\\)$", "(" + val + ")");
                    i=0;
                    //fileName=newName;
                } else {
                    newName = newName + "(" + 1 + ")";
                    i=0;
                    //fileName=newName;
                }
            }
        }
        //String newName= fileNo>0 ? fileName+ "(" + fileNo+ ")" : fileName;
        return newName;
    }
}