package com.foodproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.foodproject.R;
import com.foodproject.model.Food;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private ArrayList<Food> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Food> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_card, parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage())
                .into(holder.place_profile);
        holder.place_product.setText(arrayList.get(position).getProduct());
        holder.place_price.setText(String.valueOf(arrayList.get(position).getPrice()));
        holder.place_location.setText(arrayList.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView place_profile;
        TextView place_product;
        TextView place_price;
        TextView place_location;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            place_profile = itemView.findViewById(R.id.place_profile);
            place_product = itemView.findViewById(R.id.place_product);
            place_price = itemView.findViewById(R.id.place_price);
            place_location = itemView.findViewById(R.id.place_location);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int currentPos = getAdapterPosition(); // click position
                    Food food = arrayList.get(currentPos);


                }
            });
        }
    }
}
