package com.assignment.treineticassignment.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.treineticassignment.CallBack.OnClick;
import com.assignment.treineticassignment.R;

import java.util.ArrayList;

public class CategoryListRecycleViewAdapter extends RecyclerView.Adapter<CategoryListRecycleViewAdapter.RecycleViewHolder>{
    Context mContext;
    ArrayList<String> dataArrayList;
    private final LayoutInflater inflater;
    private int selectedPosition = 0;

    public CategoryListRecycleViewAdapter(Context context, ArrayList<String> dataArrayList ) {
        this.mContext = context;
        this.dataArrayList = dataArrayList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryListRecycleViewAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_recycle_view_item, parent, false);
        return new CategoryListRecycleViewAdapter.RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListRecycleViewAdapter.RecycleViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.cat_button.setText(dataArrayList.get(position));

        holder.cat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the background color from the previously selected item
                if (selectedPosition != -1) {
                    notifyItemChanged(selectedPosition);
                }

                // Change the background color of the clicked item
                holder.cat_button.setBackgroundColor(ContextCompat.getColor(mContext, R.color.theme_primary));
                holder.cat_button.setTextColor(ContextCompat.getColor(mContext, R.color.white));

                // Update the selected position
                selectedPosition = position;

                // Do something with the clicked position
                // ...
            }
        });

        // Set the background color of the item based on its position
        if (selectedPosition == position) {
            holder.cat_button.setBackgroundColor(ContextCompat.getColor(mContext, R.color.theme_primary));
            holder.cat_button.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            holder.cat_button.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            holder.cat_button.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    protected class RecycleViewHolder extends RecyclerView.ViewHolder {

        Button cat_button;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            cat_button = itemView.findViewById(R.id.cat_button);

        }
    }
}