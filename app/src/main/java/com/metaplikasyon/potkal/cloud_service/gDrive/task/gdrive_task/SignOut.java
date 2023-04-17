package com.metaplikasyon.potkal.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;

import com.metaplikasyon.potkal.other.ScannerActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SignOut extends GDriveTask{
    public SignOut(Context context, Token token) {
        super(context, token);
    }

    @Override
    public boolean perform() {
        boolean result=false;
        try {
            GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(new ScannerActivity().scanForActivity(context), googleSignInOptions);
            googleSignInClient.signOut();
            result=true;
        }catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }
}
