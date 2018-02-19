package com.new_york_times.nytimes;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.new_york_times.nytimes.model.Article;

import java.util.List;

/**
 * Created by user on 18.02.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Article> articleList;


    public RecyclerViewAdapter(List<Article> articleList) {
        this.articleList = articleList;

    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.article= articleList.get(position);
        holder.mTitleTextView.setText(articleList.get(position).getTitle());
        holder.mByLineTextView.setText(articleList.get(position).getByLine());
        holder.mDateTextView.setText(articleList.get(position).getPublishedDate().toString());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageView mImageView;
        TextView mTitleTextView;
        TextView mByLineTextView;
        TextView mDateTextView;
        CheckBox mCheckBox;
        Article article;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            mImageView=(ImageView) itemView.findViewById(R.id.article_image);
            mDateTextView=(TextView) itemView.findViewById(R.id.date_text);
            mTitleTextView=(TextView) itemView.findViewById(R.id.title_text);
            mByLineTextView=(TextView) itemView.findViewById(R.id.byline_text);
            mCheckBox=(CheckBox) itemView.findViewById(R.id.favourite_checkbox);
        }
    }
}
