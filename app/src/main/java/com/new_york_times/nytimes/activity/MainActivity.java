package com.new_york_times.nytimes.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.new_york_times.nytimes.R;
import com.new_york_times.nytimes.adapter.TabsPagerAdapter;
import com.new_york_times.nytimes.fragment.ArticleListFragment;

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
        mTabsPagerAdapter.addFragment(mostMailedArticles,mostMailedArticles.getNameOfTab());
        mTabsPagerAdapter.addFragment(mostSharedArticles,mostSharedArticles.getNameOfTab());
        mTabsPagerAdapter.addFragment(mostViewedArticles,mostViewedArticles.getNameOfTab());
        mViewPager.setAdapter(mTabsPagerAdapter);
        mTabLayout=(TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
