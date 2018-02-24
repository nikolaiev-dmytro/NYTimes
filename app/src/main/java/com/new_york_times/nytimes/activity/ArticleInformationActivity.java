package com.new_york_times.nytimes.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.new_york_times.nytimes.R;
import com.new_york_times.nytimes.adapter.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by user on 23.02.2018.
 */

public class ArticleInformationActivity extends AppCompatActivity {
    private TextView mTitle;
    private TextView mByLine;
    private TextView mDate;
    private AppCompatImageView mImage;
    private TextView mImageCaption;
    private TextView mImageCopyright;
    private TextView mAbstractText;
    private TextView mLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_info);

        mImageCaption = findViewById(R.id.article_caption_textview);
        mImageCopyright = findViewById(R.id.article_image_copyright_textview);
        mTitle = findViewById(R.id.article_title_textview);
        mByLine = findViewById(R.id.article_byline_textview);
        mDate = findViewById(R.id.article_date_textview);
        mImage = findViewById(R.id.article_image_imageview);
        mAbstractText = findViewById(R.id.article_abstract_textview);
        if (savedInstanceState == null) ;
        mAbstractText.setText(getIntent().getStringExtra(RecyclerViewAdapter.ARTICLE_ABSTRACT));
        mTitle.setText(getIntent().getStringExtra(RecyclerViewAdapter.ARTICLE_TITLE));
        mByLine.setText(getIntent().getStringExtra(RecyclerViewAdapter.ARTICLE_BYLINE));
        mDate.setText(getIntent().getStringExtra(RecyclerViewAdapter.ARTICLE_DATE));
        Picasso.with(this).load(getIntent().getStringExtra(RecyclerViewAdapter.ARTICLE_IMAGE_URL)).into(mImage);
        String caption = getIntent().getStringExtra(RecyclerViewAdapter.ARTICLE_IMAGE_CAPTION);
        mImageCaption.setText(caption);
        mImageCopyright.setText(getIntent().getStringExtra(RecyclerViewAdapter.ARTICLE_IMAGE_COPYRIGHT));
        mLink = findViewById(R.id.article_url_textview);
        mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra(RecyclerViewAdapter.ARTICLE_URL)));
                startActivity(browserIntent);
            }
        });
    }

}
