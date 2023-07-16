package com.metaplikasyon.potkal.fragments.fragment_worddef.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.ui.editor.UiEditorWorddef;
import com.metaplikasyon.potkal.other.ScannerActivity;

public class PopupWorddefOpt extends PopupMenu
{
    private final Context context;
    public PopupWorddefOpt(Context context, View anchor) {
        super(context, anchor);
        this.context=context;
        onCreate();


        setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

//            final LinearLayout ll=new ScannerActivity().scanForActivity(context).findViewById(R.id.pnlWordDefVrt);
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case 0:
//                        new UiEditorWorddef().hide(ll);
                        break;
                    case 1:
//                        new UiEditorWorddef().show(ll);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
    }

    public void onCreate() {
        getMenu().add(0, 0, Menu.NONE,context.getResources().getString(R.string.hide_def));
        getMenu().add(1,1,Menu.NONE,context.getResources().getString(R.string.show_def));
    }
}