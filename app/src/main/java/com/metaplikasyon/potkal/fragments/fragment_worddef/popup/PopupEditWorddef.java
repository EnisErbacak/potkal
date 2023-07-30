package com.metaplikasyon.potkal.fragments.fragment_worddef.popup;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.metaplikasyon.potkal.MainActivity;
import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.cloud_service.manager.CloudManager;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.Word;
import com.metaplikasyon.potkal.fragments.fragment_worddef.dialog.dialog.FragmentDialogChngWrdDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.operator.BuilderEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.manager.WorddefManager;
import com.metaplikasyon.potkal.other.ScannerActivity;
import com.metaplikasyon.potkal.reaction.Reactor;

import java.util.ArrayList;

public class PopupEditWorddef extends PopupMenu {
    private final String setName;
    private final int position;
    private final Context context;
    public PopupEditWorddef(Context context, View anchor, String setName, int position) {
        super(context, anchor);
        this.setName=setName;
        this.position = position;
        this.context=context;
        onCreate();
        setOnMenuItemClickListener(new PopupEditWordDefLstner(anchor, setName));
    }


    private Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }

    public void onCreate() {
        getMenu().add(0, 0, Menu.NONE, context.getResources().getString(R.string.change));
        getMenu().add(0, 1, Menu.NONE, context.getResources().getString(R.string.delete));
    }


    private class PopupEditWordDefLstner implements PopupMenu.OnMenuItemClickListener {
        private final View viewAnchor;
        private final FragmentActivity fragmentActivity;

        public PopupEditWordDefLstner(View viewAnchor, String setName) {
            this.viewAnchor = viewAnchor;
            fragmentActivity = (FragmentActivity) scanForActivity(context);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                //  CHANGE WORD
                case 0:
//                    FragmentDialogChngWrdDef fragmentDialogChngWrdDef = new FragmentDialogChngWrdDef(viewAnchor.getRootView().findViewById(R.id.pnlWordDefVrt), viewAnchor, setName);
//                    fragmentDialogChngWrdDef.show(fragmentActivity.getSupportFragmentManager(), context.getResources().getString(R.string.change));

                    break;

                //  DELETE WORD
                case 1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.CustomDialog);
                    AlertDialog removeDialog = builder.create();

                    View v=View.inflate(context, R.layout.dialog_remove,null);
                    removeDialog.setView(v);
                    Button positive= v.findViewById(R.id.btnDlgRemoveOk);
                    Button negative= v.findViewById(R.id.btnDlgRemoveCancel);
                    TextView tvDDlgRemove=v.findViewById(R.id.tvDlgRemove);
                    tvDDlgRemove.setText(R.string.word_will_be_deleted);
                    positive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            new WorddefManager().operate(context).remove(viewAnchor.getText().toString());
                            //editor.delete();
                            //new BuilderEditor().getUiEditor(context, setName).updateScreen();

                            ArrayList<Word> words = FragmentWordDef.getWords();
                            new WorddefManager().operate(context).remove(words.get(position).getWrd());
                            words.remove(position);

                            FragmentWordDef.updateUi();
                            new Reactor(context).showShort(context.getResources().getString(R.string.deleted));
                            removeDialog.dismiss();
                        }
                    });
                    negative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            removeDialog.dismiss();
                        }
                    });
                    removeDialog.show();
                    return false;
            }
            return false;
        }
    }
}
