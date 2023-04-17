package com.metaplikasyon.potkal.fragments.fragment_wordset.popup;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import android.widget.PopupMenu;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.MainActivity;
import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.cloud_service.manager.CloudManager;
import com.metaplikasyon.potkal.fragments.fragment_settings.FragmentSettings;
import com.metaplikasyon.potkal.cloud_service.manager.CloudManagerFactory;
import com.metaplikasyon.potkal.fragments.fragment_wordset.FragmentWordSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.editor.UiEdtrWrdSet;
import com.metaplikasyon.potkal.other.ScannerActivity;
import com.metaplikasyon.potkal.reaction.Reactor;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class PopupTopMenu extends PopupMenu {
    private final Context context;
    private final View anchor;
    private ProgressBar pb;

    public PopupTopMenu(Context context, View anchor) {
        super(context, anchor);
        this.context = context;
        this.anchor = anchor;
        setOnMenuItemClickListener(new PopupMainOptsLstnr());
        onCreate();
    }

    public void onCreate() {
        //	add(int groupId, int itemId, int order, CharSequence title)
        getMenu().add(0, 0, Menu.NONE, context.getResources().getString(R.string.settings));
        getMenu().add(0, 1, Menu.NONE, context.getResources().getString(R.string.backup));
        getMenu().add(0, 2, Menu.NONE, context.getResources().getString(R.string.restore));
        getMenu().add(0, 3, Menu.NONE, context.getResources().getString(R.string.sign_out_google_account));
    }

    private class PopupMainOptsLstnr implements PopupMenu.OnMenuItemClickListener {
        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(new ScannerActivity().scanForActivity(context)
                , googleSignInOptions);

        @Override
        public boolean onMenuItemClick(MenuItem itm) {
            pb=new ScannerActivity().scanForActivity(anchor.getContext()).findViewById(R.id.pBarWordSet);
            Reactor reactor=new Reactor(context);
            switch (itm.getItemId()) {
                case 0:
                    FragmentManager fragManager = ((FragmentActivity)context).getSupportFragmentManager();
                    FragmentTransaction fragTransacion = fragManager.beginTransaction();
                    fragTransacion.replace(R.id.containerActivityMain, new FragmentSettings());
                    fragTransacion.commit();
                    fragTransacion.addToBackStack(null);
                    break;
                case 1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.CustomDialog);
                    AlertDialog backupDialog = builder.create();

                    View v=View.inflate(context, R.layout.backup,null);
                    backupDialog.setView(v);
                    Button positive= v.findViewById(R.id.btnBackupOk);
                    Button negative= v.findViewById(R.id.btnBackupCancel);
                    positive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            reactor.showShort(context.getResources().getString(R.string.connecting_google_account));
                            Intent signInIntent = googleSignInClient.getSignInIntent();
                            signInIntent.putExtra(CloudManager.PROCESS_TYPE, CloudManager.BACKUP);
                            signInIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            ((MainActivity)new ScannerActivity().scanForActivity(context)).openActivityForResult(signInIntent);
                            backupDialog.dismiss();
                        }
                    });
                    negative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            backupDialog.dismiss();
                        }
                    });
                    backupDialog.show();
                    break;
                case 2:
                    //String srcPath2=anchor.getContext().getFilesDir().getPath() + File.separator + "unzip_files";
                    reactor.showShort(context.getResources().getString(R.string.connecting_google_account));
                    Intent signInIntent = googleSignInClient.getSignInIntent();
                    signInIntent.putExtra(CloudManager.PROCESS_TYPE, CloudManager.RESTORE);
                    int asdf=signInIntent.getExtras().getInt(CloudManager.PROCESS_TYPE);
                    ((MainActivity)new ScannerActivity().scanForActivity(context)).openActivityForResult(signInIntent);
                    new UiEdtrWrdSet(context).updateScrn(FragmentWordSet.ORDER_BY);
                    break;
                case 3:
                    new CloudManagerFactory().create(CloudManagerFactory.GDRIVE, pb).perform(CloudManager.SIGN_OUT);
                    break;
            }
            return false;
        }
    }
}
