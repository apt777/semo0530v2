package com.foodproject.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodproject.R;
import com.foodproject.model.Favorit;
import com.foodproject.model.Product;

import java.util.ArrayList;
import java.util.List;
/* 프로필 내 찜 목록 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder>{

    private List<Favorit> products = new ArrayList<>();
    private Context context;
    private final OnProductClickListener mListener;

    public ProductAdapter(Context context){
        this.context = context;

        try {
            this.mListener = ((OnProductClickListener) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnPlaceClickListener.");
        }

        String[] favoriteFoodNames = {"청송 사과", "나주 배", "영동 포도",
                "양산 당근", "제주 감귤","홍천 가지","안동 고등어","고창 수박","진주 멜론","성주 참외","평택 오이","거창 딸기"};

        String[] favoriteFoodPlaces = {"바로 수확한 사과팔아요~", "달달하고 맛있는 배 팔아요~", "달달한 포도 팔아요~",
                "싱싱한 당근 팔아요~", "달고 맛있는 제주감귤팔아요~","싱싱한 가지 팔아요~","싱싱한 고등어 팔아요~","달달하고 싱싱한 수박 팔아요~","달달한 멜론 팔아요~",
                "달달하고 맛있는 참외 팔아요~","아삭하고 싱싱한 오이 팔아요~","달달한 딸기 팔아요~"};

        String[] favoriteFoodPrices = {"10,000원", "12,000원", "12,000원",
                "13,000원", "15,000원","10,000원","11,000원","16,000원","14,000원","11,000원","10,000원","10,000원"};


        int images_array[] = {
                R.drawable.category_fruit_apple,
                R.drawable.category_pear,
                R.drawable.category_grape,
                R.drawable.category_carrot,
                R.drawable.category_mandarin,
                R.drawable.category_eggplants,
                R.drawable.category_mackerel,
                R.drawable.category_watermelon,
                R.drawable.category_melon,
                R.drawable.category_kmelon,
                R.drawable.category_cucumber,
                R.drawable.category_strawberry,
        };

        for (int i = 0; i < favoriteFoodNames.length; i++){
            Favorit prod = new Favorit(favoriteFoodNames[i], "Description " + (i + 1),
                    favoriteFoodPlaces[i],  favoriteFoodPrices[i], images_array[i]);
            products.add(prod);
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_card, viewGroup, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        final Favorit prod =  products.get(position);

        holder.mProductName.setText(prod.getmProductName());
        holder.mProductDescription.setText(prod.getmProductRestaurant());
        holder.mProductValue.setText(prod.getmProductValue());
        holder.mProductImages.setImageResource(prod.getmProductDrawable());
        holder.mNoFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener  != null){
                    mListener.OnProductFavoriteClick(prod);
                    holder.mNoFavorite.setVisibility(View.GONE);
                    holder.mFavorite.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener  != null){
                    mListener.OnProductNoFavoriteClick(prod);
                    holder.mNoFavorite.setVisibility(View.VISIBLE);
                    holder.mFavorite.setVisibility(View.GONE);
                }
            }
        });
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mProductName, mProductDescription, mProductValue, mProductRestaurant;
        public ImageView mNoFavorite, mFavorite, mProductImages;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mProductName = itemView.findViewById(R.id.product_name);
            mProductDescription = itemView.findViewById(R.id.product_description);
            mProductValue = itemView.findViewById(R.id.product_price);
            mFavorite = itemView.findViewById(R.id.favorite);
            mNoFavorite = itemView.findViewById(R.id.no_favorite);
            mProductImages = itemView.findViewById(R.id.favorite_profile);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Clicked Item", Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnProductClickListener {
        void OnProductNoFavoriteClick(Favorit products);
        void OnProductFavoriteClick(Favorit products);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
