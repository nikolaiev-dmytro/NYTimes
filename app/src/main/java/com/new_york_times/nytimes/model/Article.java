package com.new_york_times.nytimes.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 17.02.2018.
 */
public class Article {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("count_type")
    @Expose
    private String countType;
     @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("byline")
    @Expose
    private String byLine;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String abstractText;
    @SerializedName("published_date")
    @Expose
    private Date publishedDate;
    @SerializedName("source")
    @Expose
    private String source;
/*
    @SerializedName("media")
    @Expose
    private List<Media> media;*/

    public Article(String url, String countType, String section, String byLine, String title, String abstractText, Date publishedDate, String source) {
        this.url = url;
        this.countType = countType;
        this.section = section;
        this.byLine = byLine;
        this.title = title;
        this.abstractText = abstractText;
        this.publishedDate = publishedDate;
        this.source = source;
    }

    public Article() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

  /*  public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

 */   @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(url, article.url)
                .append(countType, article.countType)
                .append(section, article.section)
                .append(byLine, article.byLine)
                .append(title, article.title)
                .append(abstractText, article.abstractText)
                .append(publishedDate, article.publishedDate)
                .append(source, article.source)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(url)
                .append(countType)
                .append(section)
                .append(byLine)
                .append(title)
                .append(abstractText)
                .append(publishedDate)
                .append(source)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", countType='" + countType + '\'' +
                ", section='" + section + '\'' +
                ", byLine='" + byLine + '\'' +
                ", title='" + title + '\'' +
                ", abstractText='" + abstractText + '\'' +
                ", publishedDate=" + publishedDate +
                ", source='" + source + '\'' +
                '}';
    }
}