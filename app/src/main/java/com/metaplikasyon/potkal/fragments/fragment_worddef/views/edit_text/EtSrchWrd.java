package com.metaplikasyon.potkal.fragments.fragment_worddef.views.edit_text;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.file.shared_preferences.SPEditor;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.container.detailed.LlContainerMain;
import com.metaplikasyon.potkal.other.PixelConverter;

public class EtSrchWrd extends androidx.appcompat.widget.AppCompatEditText {
    private LinearLayout pnlWordDefVrt;
    public EtSrchWrd(Context context, AttributeSet attrs) {
        super(context, attrs);
        setVisibility(INVISIBLE);
        setInputType(InputType.TYPE_NULL);
        onCreate();
    }

    private void onCreate() {
        LinearLayout pnlWordDefVrt=findViewById(R.id.pnlWordDefVrt);
        this.addTextChangedListener(new TxtWatcher());
        setStyle();
    }

    private void setStyle() {
        SPEditor sp=new SPEditor();
        PixelConverter pc=new PixelConverter(getContext());

        getBackground().setTint(sp.getInt(getContext(), SPEditor.COL_WORDSET_BG));
        setTextColor(sp.getInt(getContext(), SPEditor.COL_WORDGAME_PANEL_BG));

        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(pc.dp2Px(10),0,pc.dp2Px(10),0);
        setPadding(pc.dp2Px(10),0,pc.dp2Px(10),0);
        setLayoutParams(lp);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_UP) {
            EtSrchWrd.this.setVisibility(INVISIBLE);
            EtSrchWrd.this.setInputType(InputType.TYPE_NULL);
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

    public LinearLayout getPnlWordDefVrt() {
        return pnlWordDefVrt;
    }

    public void setPnlWordDefVrt(LinearLayout pnlWordDefVrt) {
        this.pnlWordDefVrt = pnlWordDefVrt;
    }

    private class TxtWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            hideViews(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

        private void hideViews(String key) {
                for(int i=0;i<getPnlWordDefVrt().getChildCount(); i++) {
                    LlContainerMain child= (LlContainerMain)getPnlWordDefVrt().getChildAt(i);
                    String str=child.getLlContainerUpper().getStrWord();

                if(str.contains(key)) {
                    if(pnlWordDefVrt.getChildAt(i).getVisibility()==GONE || pnlWordDefVrt.getChildAt(i).getVisibility()==INVISIBLE)
                        pnlWordDefVrt.getChildAt(i).setVisibility(VISIBLE);
                    continue;
                }
                else {
                    //pnlWordDefVrt.removeViewAt(i);
                    pnlWordDefVrt.getChildAt(i).setVisibility(GONE);
                }
            }
            return;
        }
    }
}


