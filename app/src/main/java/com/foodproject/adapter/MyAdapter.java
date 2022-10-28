package com.foodproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodproject.R;
import com.foodproject.model.Chart;

import java.text.CollationElementIterator;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Chart> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public MyAdapter(Context context, ArrayList<Chart> itmes) {
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.itme, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //binding
        holder.item_name.setText(mList.get(position).item_name);
        holder.kind_name.setText(mList.get(position).kind_name);
        holder.rank.setText(mList.get(position).rank);
        holder.unit.setText(mList.get(position).unit);
        holder.day1.setText(mList.get(position).day1);
        holder.dpr1.setText(mList.get(position).dpr1);
        holder.day2.setText(mList.get(position).day2);
        holder.dpr2.setText(mList.get(position).dpr2);
        holder.day3.setText(mList.get(position).day3);
        holder.dpr3.setText(mList.get(position).dpr3);
        holder.day4.setText(mList.get(position).day4);
        holder.dpr4.setText(mList.get(position).dpr4);
        holder.day5.setText(mList.get(position).day5);
        holder.dpr5.setText(mList.get(position).dpr5);
        holder.day6.setText(mList.get(position).day6);
        holder.dpr6.setText(mList.get(position).dpr6);
        holder.day7.setText(mList.get(position).day7);
        holder.dpr7.setText(mList.get(position).dpr7);

        //Click event
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name;
        public TextView kind_name;
        public TextView rank;
        public TextView unit;
        public TextView day1;
        public TextView dpr1;
        public TextView day2;
        public TextView dpr2;
        public TextView day3;
        public TextView dpr3;
        public TextView day4;
        public TextView dpr4;
        public TextView day5;
        public TextView dpr5;
        public TextView day6;
        public TextView dpr6;
        public TextView day7;
        public TextView dpr7;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_name = itemView.findViewById(R.id.tv_item_name);
            kind_name = itemView.findViewById(R.id.tv_kind_name);
            rank = itemView.findViewById(R.id.tv_rank);
            unit = itemView.findViewById(R.id.tv_unit);
            day1 = itemView.findViewById(R.id.tv_day1);
            dpr1 = itemView.findViewById(R.id.tv_dpr1);
            day2 = itemView.findViewById(R.id.tv_day2);
            dpr2 = itemView.findViewById(R.id.tv_dpr2);
            day3 = itemView.findViewById(R.id.tv_day3);
            dpr3 = itemView.findViewById(R.id.tv_dpr3);
            day4 = itemView.findViewById(R.id.tv_day4);
            dpr4 = itemView.findViewById(R.id.tv_dpr4);
            day5 = itemView.findViewById(R.id.tv_day5);
            dpr5 = itemView.findViewById(R.id.tv_dpr5);
            day6 = itemView.findViewById(R.id.tv_day6);
            dpr6 = itemView.findViewById(R.id.tv_dpr6);
            day7 = itemView.findViewById(R.id.tv_day7);
            dpr7 = itemView.findViewById(R.id.tv_dpr7);

        }
    }

}
