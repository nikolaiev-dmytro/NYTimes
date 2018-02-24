package com.new_york_times.nytimes.activity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.new_york_times.nytimes.R;
import com.new_york_times.nytimes.adapter.TabsPagerAdapter;
import com.new_york_times.nytimes.database.DataBaseHelper;
import com.new_york_times.nytimes.fragment.ArticleListFragment;
import com.new_york_times.nytimes.fragment.FavouriteArticlesFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabsPagerAdapter mTabsPagerAdapter;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager=(ViewPager) findViewById(R.id.content_container);
        mTabsPagerAdapter=new TabsPagerAdapter(getSupportFragmentManager());
        ArticleListFragment mostMailedArticles=new ArticleListFragment();
        mostMailedArticles.setTypeOfContent("mostemailed");
        ArticleListFragment mostSharedArticles=new ArticleListFragment();
        mostSharedArticles.setTypeOfContent("mostshared");
        ArticleListFragment mostViewedArticles=new ArticleListFragment();
        mostViewedArticles.setTypeOfContent("mostviewed");
        FavouriteArticlesFragment favouriteArticlesFragment=new FavouriteArticlesFragment();
        favouriteArticlesFragment.setTypeOfContent("favourite");
        favouriteArticlesFragment.setmNameOfTab("favourite");
        mTabsPagerAdapter.addFragment(mostMailedArticles,mostMailedArticles.getNameOfTab());
        mTabsPagerAdapter.addFragment(mostSharedArticles,mostSharedArticles.getNameOfTab());
        mTabsPagerAdapter.addFragment(mostViewedArticles,mostViewedArticles.getNameOfTab());
        mTabsPagerAdapter.addFragment(favouriteArticlesFragment,favouriteArticlesFragment.getNameOfTab());
        mViewPager.setAdapter(mTabsPagerAdapter);
        mTabLayout=(TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);


    }
}
