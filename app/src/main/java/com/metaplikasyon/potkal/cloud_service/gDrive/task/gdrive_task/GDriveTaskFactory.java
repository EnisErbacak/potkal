package com.metaplikasyon.potkal.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;

public class GDriveTaskFactory {

    public static final int INITIALIZE =1;
    public static final int SIGN_OUT=2;
    public static final int CONNECT=3;
    public static final int DOWNLOAD=4;
    public static final int UPLOAD=5;
    public static final int CLEAN=6;
    public GDriveTask create(int task, Context context, Token token) {
        GDriveTask returnTask;
        switch (task) {
            case INITIALIZE:
                returnTask= new Initialize(context, token);
                break;
            case SIGN_OUT:
                returnTask= new SignOut(context, token);
                break;
            case CONNECT:
                returnTask= new Connect(context, token);
            break;
            case DOWNLOAD:
                returnTask= new Download(context, token);
            break;
            case UPLOAD:
                returnTask= new Upload(context, token);
                break;
            case  CLEAN:
                returnTask= new Clean(context,token);
            break;
            default:
                returnTask= null;
        }
        return  returnTask;
    }
}
