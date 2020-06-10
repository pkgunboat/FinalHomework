package com.example.homework;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


public class LinearAdapter extends RecyclerView.Adapter <LinearAdapter.LinearViewHolder> {
    @NonNull
    private Context mcontext;
    List<ArticleResponse.Article> mDataset;
    public LinearAdapter(Context context , List<ArticleResponse.Article> articles){
        this.mcontext=context;
        this.mDataset=articles;
    }//构造函数
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new LinearViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.im_list_item,viewGroup,false));//传入一个布局，表示每个Item长什么样子
    }//创建ViewHolder

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final LinearAdapter.LinearViewHolder viewHolder, int i) {
        viewHolder.textView.setText(mDataset.get(i).nickname);
        loadCover(viewHolder.imageView,mDataset.get(i).feedurl,mcontext);
        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mcontext,FullScreenActivity.class);
            intent.putExtra("uri",mDataset.get(i).feedurl);
            Log.d("yzm",intent.getExtras().toString());
            mcontext.startActivity(intent);
        });
    }//绑定ViewHolder


    @Override
    public int getItemCount() {
        return mDataset.size();
    }//列表长度

    class LinearViewHolder extends RecyclerView.ViewHolder{
        //在此声明布局里的控件
        private VideoView videoView;
        private TextView textView;
        private ImageView imageView;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
            imageView = itemView.findViewById(R.id.imageview);
        }
    }

    public static void loadCover(ImageView imageView, String url, Context context) {

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000)
                                .centerCrop()

                )
                .load(url)
                .into(imageView);
    }



}