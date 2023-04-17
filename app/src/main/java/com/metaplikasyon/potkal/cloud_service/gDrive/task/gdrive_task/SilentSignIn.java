package com.metaplikasyon.potkal.cloud_service.gDrive.task.gdrive_task;

import android.app.Activity;
import android.content.Context;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.reaction.Reactor;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SilentSignIn extends GDriveTask{
    GDriveTask login;
    public SilentSignIn(Context context, Token token) {
        super(context, token);
    }

    @Override
    public boolean perform() {
        boolean result=false;
        login=new GDriveTaskFactory().create(GDriveTaskFactory.INITIALIZE, context, token);
        token.setGoogleSignInOptions( new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build());
        try {
            token.setGoogleSignInClient(GoogleSignIn.getClient((Activity)context, token.getGoogleSignInOptions()));

            result=true;
        }catch (NullPointerException nex){
            new Reactor(context).showShort(context.getResources().getString(R.string.please_sig_in_first));
            login.perform();
            result=false;
        }
        return result;
    }
}
