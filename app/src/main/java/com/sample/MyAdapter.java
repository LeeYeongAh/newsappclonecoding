package com.sample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<NewsData> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView TextView_title;
        public TextView TextView_content;
        public SimpleDraweeView ImageView_title;
        public MyViewHolder(View v){
            super(v);
            TextView_title=v.findViewById(R.id.TextView_title);
            TextView_content=v.findViewById(R.id.TextView_content);
            ImageView_title = v.findViewById(R.id.ImageView_title);

        }
    }

    public MyAdapter(List<NewsData> myDataset, Context context){
        mDataset = myDataset;
        Fresco.initialize( context);
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        NewsData news = mDataset.get(position);
        holder.TextView_title.setText(news.getTitle());
        if(news.getContent() != null && news.getContent().length() > 0) {
            holder.TextView_content.setText(news.getContent());
        }
        else{
            holder.TextView_content.setText("-");
        }

        Uri uri = Uri.parse(news.getUrlToImage());

        holder.ImageView_title.setImageURI(uri);
    }

    @Override
    public int getItemCount(){
        return mDataset==null? 0 : mDataset.size();
    }
}