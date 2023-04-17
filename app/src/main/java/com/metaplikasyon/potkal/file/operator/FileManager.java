package com.metaplikasyon.potkal.file.operator;

public class FileManager {
    private final BasicFileOperator basicFileOperator;
    private final BasicFileExplorer basicFileExplorer;

    public FileManager() {
        this.basicFileOperator = new BasicFileOperator();
        this.basicFileExplorer = new BasicFileExplorer();
    }

    public BasicFileOperator operate() {
        return basicFileOperator;
    }

    public BasicFileExplorer explore() {
        return basicFileExplorer;
    }
}
