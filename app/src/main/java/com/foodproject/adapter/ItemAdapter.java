package com.foodproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodproject.R;
import com.foodproject.model.Item;

import java.util.ArrayList;
import java.util.List;
/* activity_seller.xml 의 판매중인상품 목록 아래부분 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>{

    private List<Item> items = new ArrayList<>();
    private Context context;
    private final onClickListener mListener;

    public ItemAdapter(Context context){
        this.context = context;

        try {
            this.mListener = ((onClickListener) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnPlaceClickListener.");
        }

        String[] itemNames = {"청송 사과", "나주 배", "영동 포도",
                "거창 딸기", "제주 감귤"};

        String[] itemPrices = {"12,000원", "20,000원", "15,000원",
               "10,000원", "14,000원" };

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

        for (int i = 0; i < itemNames.length ; i++){
            Item item = new Item(itemNames[i], itemPrices[i], images_array[i]);
            items.add(item);
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_items, viewGroup, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Item item =  items.get(position);

        holder.itemName.setText(item.getItemName());
        holder.itemPrice.setText(item.getItemPrice());
        holder.itemImages.setImageResource(item.getItemDrawable());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onClickListener(item);
            }
        });
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView itemName, itemPrice;
        public ImageView itemImages;
        public View mView;



        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImages = itemView.findViewById(R.id.item_profile1);

        }

        @Override
        public void onClick(View v) {}
    }

    public interface onClickListener {
        void onClickListener(Item item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
