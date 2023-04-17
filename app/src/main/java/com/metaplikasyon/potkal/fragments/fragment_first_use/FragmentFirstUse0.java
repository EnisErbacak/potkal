package com.metaplikasyon.potkal.fragments.fragment_first_use;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.Word;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.operator.WordOperator;
import com.metaplikasyon.potkal.fragments.fragment_worddef.manager.WorddefManager;
import com.metaplikasyon.potkal.fragments.fragment_wordset.FragmentWordSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.editor.UiEdtrWrdSet;
import com.metaplikasyon.potkal.fragments.fragment_wordset.manager.WordsetManager;

import org.json.JSONObject;

public class FragmentFirstUse0 extends Fragment {
    private final int MTCH_PRNT=WindowManager.LayoutParams.MATCH_PARENT;
    private Button btn;
    private ConstraintLayout clFirtUse;
    private View[] tutElements;
    private TextView tvPotkalWordsetFU;

    private class CListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CListener cListener=new CListener();
        View view= inflater.inflate(R.layout.fragment_first_use0,container,false);
        clFirtUse= view.findViewById(R.id.clFirtUse);
        tvPotkalWordsetFU=view.findViewById(R.id.tvPotkalWordsetFU);

        btn=view.findViewById(R.id.btnFU);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentFirstUse0.this.onDestroyView();

                FragmentManager fragManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                FragmentTransaction fragTransacion = fragManager.beginTransaction();

                //fragTransacion.add(R.id.containerActivityMain, new Fragment1());
                fragTransacion.commit();
                new WordsetManager().operate(getContext()).add("Kitap1", new JSONObject());
                new FragmentWordDef("Kitap1");
                Word potkal=new Word();
                potkal.setWrd("potkal");
                potkal.setDef("Kaza veya başka bir olayı karadakilere bildirmek için gemilerden denize salınan, içinde mektup olan şişe");
                potkal.setLang("İtalyanca boccale");
                potkal.setPts(5);
                potkal.setKind("isim");
                new WorddefManager().operate(getContext()).add(potkal.getWrd(), new WordOperator().convert2Json(potkal));
                new UiEdtrWrdSet(getContext()).updateScrn(FragmentWordSet.ORDER_BY);

                fragTransacion.remove(FragmentFirstUse0.this);
            }
        });
        return view;
    }
}
