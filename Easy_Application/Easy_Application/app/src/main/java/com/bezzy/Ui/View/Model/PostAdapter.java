package com.bezzy.Ui.View.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easy_application.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<PostItem> postItems;
    public PostAdapter (List<PostItem> postItems){
        this.postItems=postItems;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.setPostImageView(postItems.get(position));
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView postImageView;

        public PostViewHolder(@NonNull View itemView) {

            super(itemView);
            postImageView=itemView.findViewById(R.id.imagepost);
        }
        void  setPostImageView(PostItem postItem){
            //here you can put coded for loading image using glide or picasso
            postImageView.setImageResource(postItem.getImage());
        }
    }
}

