package com.new_york_times.nytimes.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.new_york_times.nytimes.R;
import com.new_york_times.nytimes.model.Article;
import com.new_york_times.nytimes.model.MetaData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by user on 18.02.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Article> articleList;
    private Context context;

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
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
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
            mImageView =  itemView.findViewById(R.id.article_image);
            mDateTextView = (TextView) itemView.findViewById(R.id.date_text);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title_text);
            mByLineTextView = (TextView) itemView.findViewById(R.id.byline_text);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.favourite_checkbox);
        }
    }
}
