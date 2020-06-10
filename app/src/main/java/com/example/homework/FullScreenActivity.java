package com.example.homework;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class FullScreenActivity extends AppCompatActivity {
    private FullVideoView videoView;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        uri = getIntent().getStringExtra("uri");
        videoView = findViewById(R.id.fsvideoview);
        MediaController mediaController = new MediaController(FullScreenActivity.this);
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.setVideoURI(Uri.parse(uri));
        videoView.start();
//        videoView.setOnClickListener(view -> {
//            if(videoView.isPlaying())
//                videoView.pause();
//            else
//                videoView.start();
//        });
    }
}
