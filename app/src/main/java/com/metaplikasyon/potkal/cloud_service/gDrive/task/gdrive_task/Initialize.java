package com.metaplikasyon.potkal.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;

import com.metaplikasyon.potkal.other.ScannerActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Initialize extends GDriveTask{

    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;

    public Initialize(Context context, Token token) {
        super(context, token);
    }

    @Override
    public boolean perform() {
            googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            googleSignInClient = GoogleSignIn.getClient(new ScannerActivity().scanForActivity(context)
                    , googleSignInOptions);

            token.setGoogleSignInClient(googleSignInClient);
            token.setGoogleSignInOptions(googleSignInOptions);
        return  true;
    }
}
