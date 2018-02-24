package com.new_york_times.nytimes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.new_york_times.nytimes.R;
import com.new_york_times.nytimes.activity.ArticleInformationActivity;
import com.new_york_times.nytimes.model.Article;
import com.new_york_times.nytimes.model.MetaData;
import com.new_york_times.nytimes.model.MetaDataList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 18.02.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Article> articleList;
    private Context context;
    public static final String ARTICLE_URL = "article_url";
    public static final String ARTICLE_TITLE = "article_title";
    public static final String ARTICLE_DATE = "atricle_date";
    public static final String ARTICLE_BYLINE = "article_byline";
    public static final String ARTICLE_ABSTRACT = "article_abstract";
    public static final String ARTICLE_IMAGE_URL = "article_image_url";
    public static final String ARTICLE_IMAGE_CAPTION="article_image_caption";
    public static final String ARTICLE_IMAGE_COPYRIGHT="article_image_copyright";

    public RecyclerViewAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.article = articleList.get(position);
        holder.mTitleTextView.setText(articleList.get(position).getTitle());
        holder.mByLineTextView.setText(articleList.get(position).getByLine());
        String dateFormat = DateFormat.format("dd MMM, yyyy", articleList.get(position).getPublishedDate()).toString();
        holder.mDateTextView.setText(dateFormat);
        if (articleList.get(position).getMedia() != null &&
                !articleList.get(position).getMedia().isEmpty() &&
                !articleList.get(position).getMedia().get(0).getMediaMetadata().isEmpty()) {
            for (MetaData metaData : articleList.get(position).getMedia().get(0).getMediaMetadata()) {
                if (metaData.getFormat().equals("Standard Thumbnail")) {
                    Picasso.with(context).load(metaData.getUrl()).into(holder.mImageView);
                    break;
                } else {
                    Picasso.with(context).load(articleList.get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(holder.mImageView);
                }
            }
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ArticleInformationActivity.class);
                intent.putExtra(ARTICLE_TITLE, holder.article.getTitle());
                intent.putExtra(ARTICLE_ABSTRACT, holder.article.getAbstractText());
                intent.putExtra(ARTICLE_DATE, holder.article.getPublishedDate().toString());
                intent.putExtra(ARTICLE_URL, holder.article.getUrl());
                intent.putExtra(ARTICLE_BYLINE, holder.article.getByLine());
                if (articleList.get(holder.getAdapterPosition()).getMedia() != null &&
                        !articleList.get(holder.getAdapterPosition()).getMedia().isEmpty() &&
                        !articleList.get(holder.getAdapterPosition()).getMedia().get(0).getMediaMetadata().isEmpty()) {
                    List<String> formatList = new ArrayList<>();
                    for (MetaData metaData : articleList.get(holder.getAdapterPosition()).getMedia().get(0).getMediaMetadata()) {
                        formatList.add(metaData.getFormat());
                    }
                    int formatIndex;
                    if (formatList.contains("mediumThreeByTwo440")) {
                        formatIndex = formatList.indexOf("mediumThreeByTwo440");
                    } else if (formatList.contains("mediumThreeByTwo210")) {
                        formatIndex = formatList.indexOf("mediumThreeByTwo210");
                    } else {
                        formatIndex = formatList.indexOf("Standard Thumbnail");
                    }
                    intent.putExtra(ARTICLE_IMAGE_URL, holder.article.getMedia().get(0).getMediaMetadata().get(formatIndex).getUrl());
                }
                intent.putExtra(ARTICLE_IMAGE_CAPTION,holder.article.getMedia().get(0).getCaption());
                intent.putExtra(ARTICLE_IMAGE_COPYRIGHT,holder.article.getMedia().get(0).getCopyright());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        AppCompatImageView mImageView;
        TextView mTitleTextView;
        TextView mByLineTextView;
        TextView mDateTextView;
        CheckBox mCheckBox;
        Article article;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mImageView = itemView.findViewById(R.id.article_image);
            mDateTextView = (TextView) itemView.findViewById(R.id.date_text);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title_text);
            mByLineTextView = (TextView) itemView.findViewById(R.id.byline_text);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.favourite_checkbox);
        }
    }
}
