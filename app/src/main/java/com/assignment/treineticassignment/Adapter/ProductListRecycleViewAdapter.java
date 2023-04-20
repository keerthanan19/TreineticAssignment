package com.assignment.treineticassignment.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.treineticassignment.CallBack.OnClick;
import com.assignment.treineticassignment.Object.Data;
import com.assignment.treineticassignment.R;
import com.assignment.treineticassignment.Utils.Controller;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductListRecycleViewAdapter extends RecyclerView.Adapter<ProductListRecycleViewAdapter.RecycleViewHolder>{
    Context mContext;
    ArrayList<Data> dataArrayList;
    private final LayoutInflater inflater;

    OnClick onClick;

    public ProductListRecycleViewAdapter(Context context, ArrayList<Data> dataArrayList , OnClick onClick) {
        this.mContext = context;
        this.dataArrayList = dataArrayList;
        inflater = LayoutInflater.from(context);

        this.onClick = onClick;

    }

    @NonNull
    @Override
    public ProductListRecycleViewAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_recycle_view_item, parent, false);
        return new ProductListRecycleViewAdapter.RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListRecycleViewAdapter.RecycleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String imageUrl = dataArrayList.get(position).getImages();
        Picasso.get().load(imageUrl).into(holder.product_image);

        holder.product_tittle.setText(dataArrayList.get(position).getTitle());
        holder.product_price.setText(dataArrayList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onClick(String.valueOf(dataArrayList.get(position).getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    protected class RecycleViewHolder extends RecyclerView.ViewHolder {

        ImageView product_image;
        TextView product_tittle,product_price;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            product_tittle = itemView.findViewById(R.id.product_tittle);
            product_price = itemView.findViewById(R.id.product_price);

        }
    }
}
