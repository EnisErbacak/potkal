package com.metaplikasyon.potkal.fragments.fragment_worddef.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.metaplikasyon.potkal.R;
import com.metaplikasyon.potkal.fragments.fragment_worddef.FragmentWordDef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.builder.data.Word;
import com.metaplikasyon.potkal.fragments.fragment_worddef.popup.PopupEditWorddef;
import com.metaplikasyon.potkal.fragments.fragment_worddef.views.txtView.TvWord;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    private ArrayList<Word> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout parentContainer;
        private final TextView tvWord, tvPoint, tvDef;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            parentContainer = (ConstraintLayout) view.findViewById(R.id.clWordDefSimpleParent);
            tvWord = view.findViewById(R.id.tvWord);
            tvPoint = view.findViewById(R.id.tvPoint);
            tvDef = view.findViewById(R.id.tvDef);
        }

        private void attachLongClickListener(ConstraintLayout constraintLayout) {

        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public SimpleRecyclerAdapter(ArrayList<Word> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.word_def_element_simple, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Context context = viewHolder.parentContainer.getContext();
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvWord.setText(localDataSet.get(position).getWrd());
        viewHolder.tvDef.setText(localDataSet.get(position).getDef());
        viewHolder.tvPoint.setText(String.valueOf(localDataSet.get(position).getPts()));

        viewHolder.parentContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupEditWorddef menu = new PopupEditWorddef(context, viewHolder.parentContainer
                        , FragmentWordDef.setName, position);
                menu.show();
                return false;
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

