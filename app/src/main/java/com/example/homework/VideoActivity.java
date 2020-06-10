package com.example.homework;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ArticleResponse.Article> articles;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(VideoActivity.this));
        articles = new ArrayList<>();
        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getArticles().enqueue(new Callback<List<ArticleResponse.Article>>() {
            @Override
            public void onResponse(Call<List<ArticleResponse.Article>> call, Response<List<ArticleResponse.Article>> response) {

                articles = response.body();
                recyclerView.setAdapter(new LinearAdapter(VideoActivity.this, articles));
                if (response.body() != null) {
                    Log.d("retrofit", articles.toString()+'\n');
                }
            }

            @Override
            public void onFailure(Call<List<ArticleResponse.Article>> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });


    }
}
