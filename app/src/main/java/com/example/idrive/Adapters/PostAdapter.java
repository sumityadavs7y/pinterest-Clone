package com.example.idrive.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idrive.ModelResponse.Category;
import com.example.idrive.ModelResponse.PostModel;
import com.example.idrive.R;
import com.example.idrive.Util.ImageLoadTask;
import com.example.idrive.Util.ImageUtil;
import com.example.idrive.Util.InMemoryCache;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder> {

    ArrayList<PostModel> list;
    Context context;
    InMemoryCache<String, Bitmap> imageBitMapCache = new InMemoryCache<>(30);

    public PostAdapter(ArrayList<PostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_card, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PostModel post = list.get(position);
        holder.likeCountTextView.setText(String.valueOf(post.getLikes()));
        ImageUtil.checkCacheOrLoad(imageBitMapCache,holder.postImageView,post.getUrls().getSmall());
        try{
            ImageUtil.checkCacheOrLoad(imageBitMapCache,holder.userImageView,post.getUser().getImageUrl().getSmall());
        }catch (Exception e){
//            e.printStackTrace();
        }
        holder.userNameTextView.setText(post.getUser().getName());
        holder.likeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int currentLikes = Integer.parseInt(holder.likeCountTextView.getText().toString());
            if(isChecked){
                currentLikes++;
            }else{
                currentLikes--;
            }
            holder.likeCountTextView.setText(String.valueOf(currentLikes));
        });

        for (Category category: post.getCategoryList()){
            Chip chip = new Chip(context);
            chip.setText(category.getTitle());
            chip.setChipBackgroundColorResource(R.color.design_default_color_secondary);
            chip.setCloseIconVisible(false);
            chip.setTextColor(context.getResources().getColor(R.color.white));
            holder.categoryChipGroup.addView(chip);
        }
    }

    @Override
    public void onViewRecycled(@NonNull viewHolder holder) {
        super.onViewRecycled(holder);
        holder.postImageView.setImageResource(android.R.color.transparent);
        holder.userImageView.setImageResource(android.R.color.transparent);
        holder.categoryChipGroup.removeAllViews();
        holder.likeCheckBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView postImageView;
        ImageView userImageView;
        TextView userNameTextView;
        TextView likeCountTextView;
        CheckBox likeCheckBox;
        ChipGroup categoryChipGroup;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            postImageView = itemView.findViewById(R.id.postImage);
            userImageView = itemView.findViewById(R.id.userImage);
            userNameTextView = itemView.findViewById(R.id.userNameText);
            likeCheckBox = itemView.findViewById(R.id.like_botton);
            likeCountTextView = itemView.findViewById(R.id.like_count);
            categoryChipGroup = itemView.findViewById(R.id.chipGroup);
        }
    }

    public void clearCache(){
        imageBitMapCache.clean();
    }
}
