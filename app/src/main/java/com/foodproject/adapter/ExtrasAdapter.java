package com.foodproject.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.foodproject.R;
import com.foodproject.model.Extra;

import java.util.ArrayList;
import java.util.List;
/* activity_item_details.xml 내의 구매가능상품 */
public class ExtrasAdapter extends RecyclerView.Adapter<ExtrasAdapter.ItemHolder>{

    private static String TAG = "ExtrasAdapter";

    private List<Extra> mExtras = new ArrayList<>();
    private Context context;
    private final OnExtraClickListener mListener;

    private String[] extrasNames = {"Tuna, Salmon, Wasabi, Unagi, Vegetables, Noodles"};

    public ExtrasAdapter(Context context){
        this.context = context;

        try {
            this.mListener = ((OnExtraClickListener) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnPlaceClickListener.");
        }

        String[] extrasNames = {"사과 1kg", "사과 3kg", "사과 5kg", "사과 10kg"};
        String[] extrasValues = {"10,000", "25,000", "40,000", "75,000"};

        for (int i = 0; i < extrasNames.length; i++){
            Extra extra = new Extra((i + 1), extrasNames[i], extrasValues[i] + "원");
            mExtras.add(extra);
        }
    }

    public void addExtra(int extraId) {
        if(mExtras.size() > 0) {
            for (int i = 0; i < mExtras.size(); i++) {
                if(mExtras.get(i).getExtraId() == extraId) {
                    if (!mExtras.get(i).isAdded()) {
                        mExtras.get(i).addExtra(true);
                        break;
                    } else {
                        mExtras.get(i).addExtra(false);
                        break;
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.extra_item, viewGroup, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Extra extra =  mExtras.get(position);

        holder.mItem = extra;

        holder.mExtraName.setText(extra.getExtraName());
        holder.mExtraValue.setText(extra.getExtraValue());

        if (holder.mItem.isAdded()) {
            holder.icAdd.setImageResource(R.drawable.ic_check);
        } else {
            holder.icAdd.setImageResource(R.drawable.ic_plus);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onExtraClickListener(extra);
            }
        });
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mExtraName, mExtraValue;
        public ImageView icAdd;
        public final View mView;
        public Extra mItem;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mExtraName = itemView.findViewById(R.id.extra_name);
            mExtraValue = itemView.findViewById(R.id.extra_value);
            icAdd = itemView.findViewById(R.id.ic_add);
        }

        @Override
        public void onClick(View v) {}
    }

    @Override
    public int getItemCount() {
        return mExtras.size();
    }

    public interface OnExtraClickListener {
        void onExtraClickListener(Extra extra);
    }
}
