package com.new_york_times.nytimes.fragment;

import com.new_york_times.nytimes.adapter.RecyclerViewAdapter;
import com.new_york_times.nytimes.database.DataBaseHelper;
import com.new_york_times.nytimes.model.Article;

import java.util.List;

/**
 * Created by user on 24.02.2018.
 */

public class FavouriteArticlesFragment extends ArticleListFragment {
    @Override
    public void setmNameOfTab(String nameOfTab) {
        super.setmNameOfTab("Favourite");
    }

    @Override
    protected void refresh() {
        DataBaseHelper db=new DataBaseHelper(getContext());
        List<Article> articleList=db.getArticles();
        mRecyclerView.setAdapter(new RecyclerViewAdapter(articleList,getActivity()));
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
