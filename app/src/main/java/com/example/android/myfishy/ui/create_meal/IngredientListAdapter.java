package com.example.android.myfishy.ui.create_meal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.myfishy.R;

import java.util.List;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientsViewHolder> {

    private final LayoutInflater mInflater;
    private List<String> mWords; // Cached copy of words
    private OnNutritionListener mOnNutritionListener;

    public IngredientListAdapter(Context context, OnNutritionListener onNutritionListener) {
        this.mInflater = LayoutInflater.from(context);
        this.mOnNutritionListener = onNutritionListener;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_item_ingredient, parent, false);
        return new IngredientsViewHolder(itemView, mOnNutritionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        if (mWords != null) {
            String current = mWords.get(position);
            holder.wordItemView.setText(current);
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText(R.string.tag_loading);
        }
    }

    public void setNutritionNames(List<String> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    public interface OnNutritionListener {
        void onNutritionListener(int position);
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView wordItemView;
        private OnNutritionListener onNutritionListener;

        private IngredientsViewHolder(View itemView, OnNutritionListener onNutritionListener) {
            super(itemView);
            this.wordItemView = itemView.findViewById(R.id.textView_ingredient_recyclerView_item);
            this.onNutritionListener = onNutritionListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNutritionListener.onNutritionListener(getLayoutPosition());
        }
    }
}
