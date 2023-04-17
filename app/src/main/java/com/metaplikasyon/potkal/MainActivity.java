package com.metaplikasyon.potkal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.metaplikasyon.potkal.cloud_service.manager.CloudManager;
import com.metaplikasyon.potkal.cloud_service.manager.CloudManagerFactory;
import com.metaplikasyon.potkal.file.operator.FileManager;
import com.metaplikasyon.potkal.file.path_picker.PathPicker;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_first_use.FragmentFirstUse0;
import com.metaplikasyon.potkal.fragments.fragment_wordset.FragmentWordSet;

public class MainActivity extends FragmentActivity {
    private Context context;
    private boolean firstStart;
    private SPEditor se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getApplicationContext();
        se=new SPEditor();
        firstStart=se.firstStart(getApplicationContext(), getSupportFragmentManager());
        setContentView(R.layout.activity_main);
        setCondition();
        startFragment(savedInstanceState);
    }

    private void setCondition() {
        new FileManager().operate().createDir(new PathPicker(getApplicationContext()).get(PathPicker.WORDSET));
    }

    private void startFragment(Bundle savedInstanceState) {
        setStyle();
        if(savedInstanceState==null) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction fragTransacion = fragManager.beginTransaction();

            fragTransacion.add(R.id.containerActivityMain, FragmentWordSet.getInstance());

            if(firstStart)
                fragTransacion.add(R.id.containerActivityMain, new FragmentFirstUse0());
            se.update(context);


            fragTransacion.commit();
        }
    }



    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) endApp();
        else getSupportFragmentManager().popBackStack();
    }

    public void endApp() {
        Toast.makeText(getBaseContext(), getBaseContext().getResources().getString(R.string.goodbye), Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        finish();
    }

    private void setStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(new SPEditor().getInt(context, SPEditor.COL_TOP_STATUSBAR));
        }
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> backupLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        CloudManager cm = new CloudManagerFactory().create(CloudManagerFactory.GDRIVE, findViewById(R.id.pBarWordSet));

                        Thread t1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                cm.perform(CloudManager.BACKUP);
                            }
                        });
                        t1.start();
                    } else {
                        System.out.println("--------------LOGGED FAILED");
                    }
                }});


    ActivityResultLauncher<Intent> restoreLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        CloudManager cm = new CloudManagerFactory().create(CloudManagerFactory.GDRIVE, findViewById(R.id.pBarWordSet));
                        Thread t2 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                cm.perform(CloudManager.RESTORE);
                            }
                        });
                        t2.start();

                    } else {
                        System.out.println("--------------LOGGED FAILED");
                    }
                }
            });

    public void openActivityForResult(Intent intent) {
        //Intent intent = new Intent(this, MainActivity.class);
        switch (intent.getExtras().getInt(CloudManager.PROCESS_TYPE)) {
            case CloudManager.BACKUP:
                backupLauncher.launch(intent);
                break;
            case CloudManager.RESTORE:
                restoreLauncher.launch(intent);
                break;
        }
    }

/*
    @Override
    protected void attachBaseContext(Context newBase) {
        new SPEditor().firstStart(newBase);

        String lang=newBase.getSharedPreferences("SharedPref", newBase.MODE_PRIVATE).getString(SPEditor.APP_LANG,"");
        Locale myLocale = new Locale(lang);
        Resources res = newBase.getResources();
        Configuration configuration = res.getConfiguration();
        configuration.setLocale(myLocale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(myLocale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            newBase = newBase.createConfigurationContext(configuration);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(myLocale);
            newBase = newBase.createConfigurationContext(configuration);
        } else {
            configuration.locale = myLocale;
            res.updateConfiguration(configuration, res.getDisplayMetrics());
        }
        super.attachBaseContext(newBase);
    }
     */
}