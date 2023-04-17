package com.metaplikasyon.potkal.cloud_service.manager;

import android.content.Context;
import android.widget.ProgressBar;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.cloud_service.gDrive.task.gdrive_task.GDriveTask;
import com.metaplikasyon.potkal.cloud_service.gDrive.task.gdrive_task.GDriveTaskFactory;
import com.metaplikasyon.potkal.cloud_service.gDrive.task.gdrive_task.Token;
import com.metaplikasyon.potkal.file.controller.FileController;
import com.metaplikasyon.potkal.file.operator.FileManager;
import com.metaplikasyon.potkal.file.operator.FileOperator;
import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.file.transporter.FileTransferFactory;
import com.metaplikasyon.potkal.file.transporter.FileTransporter;
import com.metaplikasyon.potkal.fragments.fragment_wordset.FragmentWordSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.editor.UiEdtrWrdSet;
import com.metaplikasyon.potkal.other.ScannerActivity;
import com.metaplikasyon.potkal.reaction.Reactor;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.io.File;


public class GdriveManager implements CloudManager{
    private FileTransporter fileTransporter;
    private Context context;
    private final ProgressBar pb;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;

    public GdriveManager(ProgressBar pb) {
        this.pb=pb;
        this.context=pb.getContext();
        this.googleSignInOptions =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        this.googleSignInClient=GoogleSignIn.getClient(new ScannerActivity().scanForActivity(context)
                , googleSignInOptions);
    }

    @Override
    public void perform(int processType) {
        switch (processType) {
            case BACKUP:
                backup(context);
                break;
            case RESTORE:
                restore(context);
                break;
            case SIGN_OUT:
                signOut(context);
                break;
        }
    }

    private void backup(Context context) {
        String srcPath=context.getFilesDir().getPath() + File.separator + "wordset_files";
        //pb.setAlpha(1);
        changePb(context,true);
        FileController fileController=new FileController();
        fileTransporter=new FileTransferFactory().create("zip");
        String zipPath=new PathPicker(context).get(PathPicker.ZIP);

        Token token=new Token();
        Thread t1=new Thread(new Runnable() {
        @Override
            public void run() {
                fileController.controlDir(zipPath);

                if(fileTransporter.transfer(srcPath, zipPath, context) && !isLimitExceeded(context)) { // --Zipping file
                    GDriveTaskFactory taskFactory = new GDriveTaskFactory();

                    //GDriveTask silentLogin=taskFactory.create(GDriveTaskFactory.SILENT_LOGIN,context, token);
                    GDriveTask initialize = taskFactory.create(GDriveTaskFactory.INITIALIZE, context, token);
                    GDriveTask connect = taskFactory.create(GDriveTaskFactory.CONNECT, context, token);
                    GDriveTask clean = taskFactory.create(GDriveTaskFactory.CLEAN, context, token);
                    GDriveTask upload = taskFactory.create(GDriveTaskFactory.UPLOAD, context, token);

                    //initialize.perform();
                    if(initialize.perform())
                        if(connect.perform())
                            if(clean.perform())
                                if(upload.perform()) {
                                    new FileManager().operate().deleteDir(new PathPicker(context).get(PathPicker.ZIP)); // --removeZip
                                    new Reactor(context).showShort(context.getResources().getString(R.string.backed_up));
                                }
                }
            }});

        t1.start();
        try {
            t1.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        suspendPb(context);
        System.out.println("************GDRIVE MANAGER FINISHED");
    }

    private void restore(Context context) {
        //pb.setAlpha(1);
        changePb(context, true);
        Token token=new Token();
        PathPicker pathPicker=new PathPicker(context);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                GDriveTaskFactory taskFactory=new GDriveTaskFactory();
                GDriveTask login=taskFactory.create(GDriveTaskFactory.INITIALIZE, context,token);
                GDriveTask connect=taskFactory.create(GDriveTaskFactory.CONNECT, context,token);
                GDriveTask download=taskFactory.create(GDriveTaskFactory.DOWNLOAD, context,token);

                if(login.perform())
                    if(connect.perform())
                        if(download.perform()) {
                            FileOperator fileOperator=new FileManager().operate();
                            fileTransporter=new FileTransferFactory().create("unzip");
                            FileController fileController=new FileController();

                            String downloadPath=pathPicker.get(PathPicker.DOWNLOAD);
                            String wordSetPath=pathPicker.get(PathPicker.WORDSET);
                            fileController.controlDir(wordSetPath);

                            fileTransporter.transfer(downloadPath, wordSetPath, context);
                            fileOperator.deleteDir(downloadPath);

                            new Reactor(context).showShort(context.getResources().getString(R.string.restored));
                        }
            }});
        t1.start();
        try {
            t1.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        suspendPb(context);
    }

    private void signOut(Context context) {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                if(new GDriveTaskFactory().create(GDriveTaskFactory.SIGN_OUT, context, null).perform())
                    new Reactor(context).showShort(context.getResources().getString(R.string.signed_out));
            }});
        t1.start();

        try {
            t1.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        suspendPb(context);
    }

    private boolean isLimitExceeded(Context context) {
        boolean result=false;
        File file = null;
        try {
            file = new File(new PathPicker(context).get(PathPicker.ZIP)+File.separator + "Potkal.zip");

            // Get length of file in bytes
            long fileSizeInBytes = file.length();
            // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
            long fileSizeInKB = fileSizeInBytes / 1024;
            // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
            long fileSizeInMB = fileSizeInKB / 1024;

            if (fileSizeInMB > 25) {
                new Reactor(context).showShort(context.getResources().getString(R.string.gdrive_limit_exceeded));
                result=true;
            }
        }catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            result=true;
        }
        return result;
    }

    private void suspendPb(Context context) {
        new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pb.setAlpha(0);
                new UiEdtrWrdSet(context).updateScrn(FragmentWordSet.ORDER_BY);
            }
        });
    }

    private void changePb(Context context, boolean visible) {
        new ScannerActivity().scanForActivity(context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int alpha= visible ? 1:0;
                pb.setAlpha(alpha);
            }
        });
    }
}
