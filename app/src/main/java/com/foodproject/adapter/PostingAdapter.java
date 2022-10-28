package com.foodproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodproject.R;
import com.foodproject.model.Post;
import java.util.ArrayList;
import java.util.List;

public class PostingAdapter extends RecyclerView.Adapter<PostingAdapter.ItemHolder>{

    private List<Post> mPosts = new ArrayList<>();
    private Context context;
    private final OnPostClickListener mListener;

    public PostingAdapter(Context context){
        this.context = context;

        try {
            this.mListener = ((OnPostClickListener) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnPlaceClickListener.");
        }

        // 상호명
        String[] postingTitles = {"오늘의 수확일지", "요즘 시세",
                "게시물", "게시물", "게시물", "게시물", "게시물7", "게시물8", "게시물9"};

        // 판매자상태
        String[] postingComments = {"1", "12",
                "6", "4", "5", "6", "7", "8", "9"};

        for (int i = 0; i < postingTitles.length; i++){
            Post post = new Post((i + 1),postingTitles[i], postingComments[i]);
            mPosts.add(post);
        }
    }

    /*
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
     */

    @NonNull
    @Override
    public PostingAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.posting_card, viewGroup, false);
        PostingAdapter.ItemHolder holder = new PostingAdapter.ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        final Post post =  mPosts.get(position);

        holder.mItem = post;

        holder.postTitle.setText(post.getpostTitle());
        holder.comments.setText(post.getcomments());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onPostClickListener(post);
            }
        });
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView postTitle, comments;
        public RelativeLayout lnlFavorite;
        public ImageView icFavorite;
        public final View mView;
        public Post mItem;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            postTitle = itemView.findViewById(R.id.post_title);
            comments = itemView.findViewById(R.id.post_comments);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Clicked Item", Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnPostClickListener {
        void onPostClickListener(Post post);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}
