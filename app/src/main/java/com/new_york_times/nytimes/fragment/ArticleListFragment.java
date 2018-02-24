package com.new_york_times.nytimes.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.new_york_times.nytimes.retrofit.NYTimesAPI;
import com.new_york_times.nytimes.retrofit.NYTimesAPIClient;
import com.new_york_times.nytimes.R;
import com.new_york_times.nytimes.adapter.RecyclerViewAdapter;
import com.new_york_times.nytimes.retrofit.ResultResponse;
import com.new_york_times.nytimes.model.Article;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleListFragment extends Fragment {
    protected List<Article> articleList = new ArrayList<>();
    protected RecyclerView mRecyclerView;
    protected RecyclerViewAdapter mAdapter;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    private static String sApiKey = "0b5c83873d5e406ab0d912d4748bad41";
    protected String mTypeOfContent;
    protected static final String BUNDLE_TYPE = "bundle_type_of_content";
    protected String mNameOfTab;

    public void setTypeOfContent(String mTypeOfContent) {
        this.mTypeOfContent = mTypeOfContent;
        mNameOfTab = mTypeOfContent.substring(4).toUpperCase();
    }

    public void setmNameOfTab(String nameOfTab) {
        this.mNameOfTab = nameOfTab;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_TYPE, mTypeOfContent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_TYPE)) {
            this.setTypeOfContent(savedInstanceState.getString(BUNDLE_TYPE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.article_list);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        refresh();
    }

    protected void refresh() {
        NYTimesAPI nyTimesAPI = NYTimesAPIClient.getRetrofit().create(NYTimesAPI.class);
        Call<ResultResponse> responseCall = nyTimesAPI.getResult(mTypeOfContent, "all-sections", 30, sApiKey);
        responseCall.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                int statusCode = response.code();
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    articleList = response.body().getArticles();

                    mAdapter = new RecyclerViewAdapter(articleList, getActivity());
                    mRecyclerView.setAdapter(mAdapter);

                }
            }


            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Log.e("ArticleListFragment", t.toString());
                Toast.makeText(getContext(), "Loading error", Toast.LENGTH_SHORT).show();
            }
        });
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public String getNameOfTab() {
        return mNameOfTab;
    }
}
