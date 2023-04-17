package com.metaplikasyon.potkal.file.operator;

public interface FileOperator {
    String read(String path);
    boolean write(String path, String content);
    boolean create(String path);
    boolean createDir(String path);
    boolean delete(String path);
    boolean deleteDir(String dir);
    boolean rename(String dir, String newName, String oldName);
}
