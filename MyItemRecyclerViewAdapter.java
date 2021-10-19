package com.example.experiment2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.experiment2.databinding.FragmentItemBinding;
import com.example.experiment2.word.WordContent;
import com.example.experiment2.word.WordContent.WordItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link WordItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    ItemFragment.OnItemClickListener mOnItemClickListener;
    private final List<WordItem> mValues;

    public MyItemRecyclerViewAdapter(List<WordItem> items) {
        mValues = items;
    }

    public void setOnItemClickListener(ItemFragment.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mWordView.setText(mValues.get(position).word);
        holder.mWordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(mValues.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mWordView;
        public WordContent.WordItem mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mWordView = binding.content;
        }



    }
}