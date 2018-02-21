package com.new_york_times.nytimes.retrofit;

import com.google.gson.annotations.SerializedName;
import com.new_york_times.nytimes.model.Article;

import java.util.List;

/**
 * Created by user on 18.02.2018.
 */

public class ResultResponse {
    @SerializedName("results")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}
