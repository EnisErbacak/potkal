package com.metaplikasyon.potkal.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;
import android.content.Intent;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.other.ScannerActivity;
import com.metaplikasyon.potkal.reaction.Reactor;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;

public class Clean extends GDriveTask {
    public Clean(Context context, Token token) {
        super(context, token);
    }

    @Override
    public boolean perform() {
        return clean();
    }

    private Boolean clean() {
        boolean result;

        try {
            FileList fileList=token.getDriveService().files().list().setSpaces("appDataFolder").execute();

            for(File file: fileList.getFiles()) {
                if(file.getName().equals("Potkal.zip")) {
                    token.getDriveService().files().delete(file.getId()).execute();
                    token.getDriveService().files().list().setSpaces("appDataFolder").execute().clear();
                    System.out.println("CLEAANEDDD");
                }
            }
            result= true;
        }// This exception is caught in Connect class
        catch (UserRecoverableAuthIOException userRecoverableAuthIOException) {
            userRecoverableAuthIOException.printStackTrace();
            new Reactor(context).showShort(context.getResources().getString(R.string.try_after_this));
            Intent requestAgainGDrive = userRecoverableAuthIOException.getIntent();
            new ScannerActivity().scanForActivity(context).startActivity(requestAgainGDrive);
            result= false;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            ioException.printStackTrace();
            result = false;
        }
        return result;
    }
}
