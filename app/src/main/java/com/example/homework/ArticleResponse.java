package com.example.homework;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

class ArticleResponse {

    public static class Article {
        @SerializedName("feedurl")
        public String feedurl;

        @SerializedName("nickname")
        public String nickname;

        @Override
        public String toString() {
            return "Article{" +
                    "feedurl=' " + feedurl + '\'' +
                    "nickname=' " + nickname + '\'' +
                    '}';
        }
    }
}
