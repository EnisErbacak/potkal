package com.metaplikasyon.potkal.fragments.fragment_wordset.popup;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_wordset.FragmentWordSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.dialog.FragmentChangeWordSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.editor.UiEdtrWrdSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.manager.WordsetManager;
import com.metaplikasyon.potkal.fragments.fragment_wordset.views.txt_view.TvWordsetLeft;

public class PopupWordSetEdit extends PopupMenu {
    private final Context context;

    public PopupWordSetEdit(Context context, View anchor) {
        super(context, anchor);
        this.context = context;
        setOnMenuItemClickListener(new PopupWordSetEditLstnr(anchor));
        onCreate();
    }

    public void onCreate() {
        getMenu().add(0, 0, Menu.NONE, context.getResources().getString(R.string.change));
        getMenu().add(0, 1, Menu.NONE, context.getResources().getString(R.string.delete));
    }


    private class PopupWordSetEditLstnr implements OnMenuItemClickListener {
        private final TvWordsetLeft tvWordsetLeft;

        public PopupWordSetEditLstnr(View TvWordsetLeft) {
            this.tvWordsetLeft = (TvWordsetLeft) TvWordsetLeft;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                //Change Set Name
                case 0:
                    /*
                    AlertDialog dialogChangeSetName = new AlertDialog.Builder(view.getContext()).create();
                    dialogChangeSetName.setTitle(context.getResources().getString(R.string.change));
                    final EditText editTextsetName = new EditText(view.getContext());
                    editTextsetName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    dialogChangeSetName.setView(editTextsetName);
                    dialogChangeSetName.setButton(AlertDialog.BUTTON_POSITIVE, context.getResources().getString(R.string.change),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    String oldWordSetName = view.getWordsetName();
                                    String newWordSetName = editTextsetName.getText().toString();

                                    if (!new FileManager().explore().checkDuplication(new PathPicker(view.getContext()).get(PathPicker.WORDSET), newWordSetName)) {
                                        new WordsetManager().operate(view.getContext())
                                                .rename(oldWordSetName, newWordSetName);
                                        view.setText(editTextsetName.getText().toString());
                                    } else {
                                        Toast.makeText(view.getContext(), context.getResources().getString(R.string.set_exists), Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.dismiss();
                                }
                            });
                    dialogChangeSetName.setButton(AlertDialog.BUTTON_NEGATIVE, context.getResources().getString(R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    dialogChangeSetName.show();
                     */

                    new FragmentChangeWordSet(tvWordsetLeft.getWordsetName()).show(
                            ((FragmentActivity) tvWordsetLeft.getContext()).getSupportFragmentManager(),"ADD NEW SET"
                    );
                    break;

                //Delete Set
                case 1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.CustomDialog);
                    AlertDialog removeDialog = builder.create();

                    View v=View.inflate(context, R.layout.dialog_remove,null);
                    removeDialog.setView(v);
                    Button positive= v.findViewById(R.id.btnDlgRemoveOk);
                    Button negative= v.findViewById(R.id.btnDlgRemoveCancel);
                    TextView tvDDlgRemove=v.findViewById(R.id.tvDlgRemove);
                    tvDDlgRemove.setText(R.string.set_will_be_deleted);
                    positive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new WordsetManager().operate(view.getContext()).remove(tvWordsetLeft.getWordsetName());
                            new UiEdtrWrdSet(view.getContext()).updateScrn(FragmentWordSet.ORDER_BY);
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



//                    AlertDialog dialogDeleteSet = new AlertDialog.Builder(tvWordsetLeft.getContext()).create();
//                    dialogDeleteSet.setMessage(context.getResources().getString(R.string.set_will_be_deleted));
//                    dialogDeleteSet.setButton(AlertDialog.BUTTON_POSITIVE, context.getResources().getString(R.string.delete),
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    new WordsetManager().operate(tvWordsetLeft.getContext()).remove(tvWordsetLeft.getWordsetName());
//                                    //editor.buildByCrtdDateDsc();
//                                    new UiEdtrWrdSet(tvWordsetLeft.getContext()).updateScrn(FragmentWordSet.ORDER_BY);
//                                }
//                            });
//                    dialogDeleteSet.setButton(AlertDialog.BUTTON_NEGATIVE, context.getResources().getString(R.string.cancel),
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    dialogDeleteSet.show();

                    break;
            }
            return false;
        }
    }
}