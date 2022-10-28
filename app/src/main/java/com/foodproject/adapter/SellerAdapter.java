package com.foodproject.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.foodproject.R;
import com.foodproject.model.Place;

import java.util.ArrayList;
import java.util.List;
/* 메인 판매자 목록 */
public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.ItemHolder>{

    private List<Place> mPlaces = new ArrayList<>();
    private Context context;
    private final OnPlaceClickListener mListener;

    public SellerAdapter(Context context){
        this.context = context;

        try {
            this.mListener = ((OnPlaceClickListener) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnPlaceClickListener.");
        }

        // 상호명
        String[] placeNames = {"청송 사과", "나주 배", "영동 포도", "거창 딸기",
                "제주 감귤", "김천 자두", "상주 청포도", "고창 수박", "진주 멜론", "성주 참외"};

        // 판매자상태
        String[] placeDelivery = {"판매중", "판매중",
                "판매중", "판매중", "판매중", "판매중", "판매중", "판매중", "판매중", "판매중"};

        // 판매자위치
        String[] placePlace = {"경상북도 청송", "전라남도 나주",
                "충청북도 영동", "경상남도 거창", "제주특별자치도 서귀포", "경상북도 김천", "경상북도 상주", "전라북도 고창", "경상남도 진주", "경상북도 성주"};

        // 판매자평점
        String[] placeRating = {"4.0", "4.2",
                "4.2", "4.1", "4.3", "4.4", "4.5", "4.4", "4.2", "4.1"};

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

        for (int i = 0; i < placeNames.length; i++){
            Place place = new Place((i+1),placeNames[i], placePlace[i],
                     placeRating[i], placeDelivery[i], images_array[i]);
            mPlaces.add(place);
        }
    }

    public void setFavorite(int placeId) {
        if(mPlaces.size() > 0) {
            for (int i = 0; i < mPlaces.size(); i++) {
                if(mPlaces.get(i).getPlaceId() == placeId) {
                    if (!mPlaces.get(i).isFavorite()) {
                        mPlaces.get(i).setFavorite(true);
                        break;
                    } else {
                        mPlaces.get(i).setFavorite(false);
                        break;
                    }
                }
            }
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.place_card, viewGroup, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        final Place place =  mPlaces.get(position);

        holder.mItem = place;

        holder.placeName.setText(place.getPlaceName());
        holder.placeLocation.setText(place.getLocation());
        holder.placeRating.setText(place.getRating());
        holder.placePrice.setText(place.getDelivery());
        holder.placeImages.setImageResource(place.getPlaceDrawable());

        if (holder.mItem.isFavorite()) {
            holder.icFavorite.setImageResource(R.drawable.star);
        } else {
            holder.icFavorite.setImageResource(R.drawable.star2);
        }

        holder.lnlFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener  != null)
                    mListener.onPlaceFavoriteClick(place);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onPlaceClickListener(place);
            }
        });
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView placeName, placeLocation, placeRating, placePrice;
        public RelativeLayout lnlFavorite;
        public ImageView icFavorite;
        public final View mView;
        public Place mItem;
        public ImageView placeImages;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            placeName = itemView.findViewById(R.id.place_product);
            placeLocation = itemView.findViewById(R.id.place_location);
            placeRating = itemView.findViewById(R.id.place_rating);
            placePrice = itemView.findViewById(R.id.place_price);
            placeImages= itemView.findViewById(R.id.place_profile);
            lnlFavorite = itemView.findViewById(R.id.lnl_favorite);
            icFavorite = itemView.findViewById(R.id.ic_favorite);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Clicked Item", Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnPlaceClickListener {
        void onPlaceClickListener(Place place);
        void onPlaceFavoriteClick(Place place);
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }
}
