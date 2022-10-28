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
import com.foodproject.model.Feature;
import com.foodproject.model.Product;

import java.util.ArrayList;
import java.util.List;
/* activity_seller.xml 의 판매중인상품 목록 */
public class PlaceFeatureAdapter extends RecyclerView.Adapter<PlaceFeatureAdapter.ItemHolder>{

    private List<Feature> products = new ArrayList<>();
    private Context context;
    private final OnPlaceClickListener mListener;

    public PlaceFeatureAdapter(Context context){
        this.context = context;

        try {
            this.mListener = ((OnPlaceClickListener) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnPlaceClickListener.");
        }

        String[] trendingNames = {"청송 사과", "나주 배", "영동 포도", "거창 딸기",
                "제주 감귤", "김천 자두", "상주 청포도"};

        String[] trendingPrices = {"12,000원", "20,000원", "15,000원",
                "10,000원", "14,000원","11,000원","10,000원"};

        int images_array[] = {
                R.drawable.category_fruit_apple,
                R.drawable.category_pear,
                R.drawable.category_grape,
                R.drawable.category_strawberry,
                R.drawable.category_mandarin,
                R.drawable.category_plum,
                R.drawable.category_shine,
                R.drawable.category_watermelon,
                R.drawable.category_melon,
                R.drawable.category_kmelon,
        };

        for (int i = 0; i < trendingNames.length; i++){
            Feature prod = new Feature(trendingNames[i], trendingPrices[i],
                     "Restaurant " + (i + 1), "Product Value R$" + (i + 1) + ",00",images_array[i]);
            products.add(prod);
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.place_product_card, viewGroup, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        final Feature prod =  products.get(position);

        holder.mProductName.setText(prod.getmProductName());
        holder.mProductDescription.setText(prod.getmProductDescription());
        holder.mProductImages.setImageResource(prod.getmProductDrawable());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onTrendingClickListener(prod);
            }
        });

        holder.mNoFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener  != null){
                    mListener.OnPlaceFavoriteClick(prod);
                    holder.mNoFavorite.setVisibility(View.GONE);
                    holder.mFavorite.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener  != null){
                    mListener.OnPlaceNoFavoriteClick(prod);
                    holder.mNoFavorite.setVisibility(View.VISIBLE);
                    holder.mFavorite.setVisibility(View.GONE);
                }
            }
        });
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mProductName, mProductDescription;
        public ImageView mNoFavorite, mFavorite,mProductImages;
        public View mView;
        public Product mItem;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mProductName = itemView.findViewById(R.id.product_name);
            mProductDescription = itemView.findViewById(R.id.product_description);
            mFavorite = itemView.findViewById(R.id.favorite);
            mNoFavorite = itemView.findViewById(R.id.no_favorite);
            mProductImages = itemView.findViewById(R.id.place_product_card1);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Clicked Item", Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnPlaceClickListener {
        void OnPlaceNoFavoriteClick(Feature products);
        void OnPlaceFavoriteClick(Feature products);
        void onTrendingClickListener(Feature product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
