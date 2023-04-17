package com.metaplikasyon.potkal.cloud_service.manager;

public interface CloudManager {
    public static final String PROCESS_TYPE="processType";

    public final int BACKUP=0;
    public final int RESTORE=1;
    public final int SIGN_OUT=2;

    void perform(int processType);
}
