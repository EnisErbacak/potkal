package com.metaplikasyon.potkal.cloud_service.gDrive.task.gdrive_task;

import android.content.Context;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAuthIOException;
import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.other.ScannerActivity;
import com.metaplikasyon.potkal.reaction.Reactor;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.IOException;
import java.util.Collections;

public class Connect extends GDriveTask {
    public Connect(Context context, Token token) {
        super(context, token);
    }

    @Override
    public boolean perform() {
        boolean result=false;
        Reactor reactor =new Reactor(context);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(new ScannerActivity().scanForActivity(context));

        GoogleAccountCredential googleAccountCredential = GoogleAccountCredential
                .usingOAuth2(context, Collections.singleton(DriveScopes.DRIVE_APPDATA));

        boolean loggedIn=false;
        try {
            account.getAccount(); // If it's null then signin.xml is not available
            loggedIn=true;
        }
        catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            //new ScannerActivity().scanForActivity(context)
            //        .startActivityForResult(token.getGoogleSignInClient().getSignInIntent(),1);
            reactor.showShort( context.getResources().getString(R.string.error_on_signin));
            loggedIn=false;
            result=false;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            reactor.showShort(context.getResources().getString(R.string.something_went_wrong));
            loggedIn=false;
            result=false;
        }

        if(loggedIn==true) {
            Drive drive=getDrive(googleAccountCredential,account);
            if(drive !=null) {
                try {
                    drive.files().list().setSpaces("appDataFolder").execute();
                    result=true;
                }
                catch (UserRecoverableAuthIOException userRecoverableAuthIOException) {
                    userRecoverableAuthIOException.printStackTrace();
                    new ScannerActivity().scanForActivity(context).startActivity(userRecoverableAuthIOException.getIntent());
                    reactor.showLong(context.getResources().getString(R.string.try_after_this));
                    result=false;
                }catch(GoogleAuthIOException googleAuthIOException) {
                    System.out.println("******"+googleAuthIOException.getCause());
                    googleAuthIOException.printStackTrace();
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                token.setDriveService(drive);
                System.out.println("--------CONNECTED!");
            }
        }
        if(loggedIn==false) {
            result=false;
        }
        return result;
    }

    private Drive getDrive(GoogleAccountCredential googleAccountCredential, GoogleSignInAccount account) {
        googleAccountCredential.setSelectedAccount(account.getAccount());
        return  new Drive.Builder(
                new NetHttpTransport()
                ,new GsonFactory()
                ,googleAccountCredential)
                .setApplicationName("Sign In GDrive")
                .build();
    }
}