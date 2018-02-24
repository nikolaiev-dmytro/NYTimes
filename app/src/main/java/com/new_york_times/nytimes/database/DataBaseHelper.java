package com.new_york_times.nytimes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.new_york_times.nytimes.model.Article;
import com.new_york_times.nytimes.model.Media;
import com.new_york_times.nytimes.model.MediaList;
import com.new_york_times.nytimes.model.MetaData;
import com.new_york_times.nytimes.model.MetaDataList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.provider.BaseColumns._ID;

/**
 * Created by user on 24.02.2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NYTimesArticles.db";
    private static final String TABLE_NAME_ARTICLES = "Articles";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_COUNT_TYPE = "count_type";
    private static final String COLUMN_SECTION = "section";
    private static final String COLUMN_BYLINE = "byline";
    private static final String COLUMN_ABSTRACT_TEXT = "abstract";
    private static final String COLUMN_DATE = "published_date";
    private static final String COLUMN_SOURCE = "source";
    private static final String TABLE_NAME_MEDIA = "Media";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_SUBTYPE = "subtype";
    private static final String COLUMN_CAPTION = "caption";
    private static final String COLUMN_COPYRIGHT = "copyright";
    private static final String COLUMN_APPROVED_FOR_SYNDICATION = "approver_for_syndication";
    private static final String COLUMN_ARTICLE_ID = "article_id";
    private static final String TABLE_NAME_METADATA = "MediaMetaData";
    private static final String COLUMN_METADATA_URL = "url";
    private static final String COLUMN_FORMAT = "format";
    private static final String COLUMN_WIDTH = "width";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_MEDIA_ID = "media_id";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_MEDIA =
            "create table " + TABLE_NAME_MEDIA + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TYPE + " TEXT," +
                    COLUMN_SUBTYPE + " TEXT," +
                    COLUMN_CAPTION + " TEXT," +
                    COLUMN_COPYRIGHT + " TEXT," +
                    COLUMN_APPROVED_FOR_SYNDICATION + " INTEGER," +
                    COLUMN_ARTICLE_ID + " INTEGER NOT NULL," +
                    "foreign key (" + COLUMN_ARTICLE_ID + ") references " + TABLE_NAME_ARTICLES + "(" + _ID + ")" +
                    ")";
    private static final String SQL_DROP_TABLE_MEDIA = "drop table if exists " + TABLE_NAME_MEDIA;
    private static final String SQL_CREATE_TABLE_ARTICLES =
            "create table " + TABLE_NAME_ARTICLES + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_URL + " TEXT," +
                    COLUMN_TITLE + " TEXT," +
                    COLUMN_COUNT_TYPE + " TEXT," +
                    COLUMN_SECTION + " TEXT," +
                    COLUMN_BYLINE + " TEXT," +
                    COLUMN_ABSTRACT_TEXT + " TEXT," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_SOURCE + " TEXT)";
    private static final String SQL_DROP_TABLE_ARTICLES = "drop table if exists " + TABLE_NAME_ARTICLES;
    private static final String SQL_CREATE_TABLE_METADATA =
            "create table " + TABLE_NAME_METADATA + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_METADATA_URL + " TEXT," +
                    COLUMN_FORMAT + " TEXT," +
                    COLUMN_WIDTH + " INTEGER," +
                    COLUMN_HEIGHT + " INTEGER," +
                    COLUMN_MEDIA_ID + " INTEGER NOT NULL," +
                    "foreign key (" + COLUMN_MEDIA_ID + ") references " + TABLE_NAME_MEDIA + "(" + _ID + ")" +
                    ")";
    private static final String SQL_DROP_TABLE_METADATA = "drop table if exists " + TABLE_NAME_METADATA;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ARTICLES);
        db.execSQL(SQL_CREATE_TABLE_MEDIA);
        db.execSQL(SQL_CREATE_TABLE_METADATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_METADATA);
        db.execSQL(SQL_DROP_TABLE_MEDIA);
        db.execSQL(SQL_DROP_TABLE_ARTICLES);
        onCreate(db);
    }

    public void addArticle(Article article) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues articleValues = new ContentValues();
        articleValues.put(COLUMN_URL, article.getUrl());
        articleValues.put(COLUMN_TITLE, article.getTitle());
        articleValues.put(COLUMN_COUNT_TYPE, article.getCountType());
        articleValues.put(COLUMN_ABSTRACT_TEXT, article.getAbstractText());
        articleValues.put(COLUMN_SECTION, article.getSection());
        articleValues.put(COLUMN_BYLINE, article.getByLine());
        articleValues.put(COLUMN_SOURCE, article.getSource());
        articleValues.put(COLUMN_DATE, article.getPublishedDate().toString());
        long articleId = db.insert(TABLE_NAME_ARTICLES, null, articleValues);
        article.setId(articleId);
        for (Media media : article.getMedia()) {
            media.setArticleID(articleId);
            addMedia(media, media.getArticleID());
            for (MetaData metaData : media.getMediaMetadata()) {
                metaData.setMediaId(media.getId());
                addMetaData(metaData, metaData.getMediaId());
            }
        }

    }

    public void deleteArticle(Article article) {

    }

    public void checkArticleInDB(Article article) {

    }



    public void addMedia(Media media, long articleId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues mediaValues = new ContentValues();
        mediaValues.put(COLUMN_COPYRIGHT, media.getCopyright());
        mediaValues.put(COLUMN_CAPTION, media.getCaption());
        mediaValues.put(COLUMN_APPROVED_FOR_SYNDICATION, media.getApprovedForSyndication());
        mediaValues.put(COLUMN_TYPE, media.getType());
        mediaValues.put(COLUMN_SUBTYPE, media.getSubtype());
        mediaValues.put(COLUMN_ARTICLE_ID, media.getArticleID());
        long mediaId = db.insert(TABLE_NAME_MEDIA, null, mediaValues);
        media.setId(mediaId);
        for (MetaData metaData : media.getMediaMetadata()) {
            metaData.setMediaId(mediaId);
        }
    }

    public void deleteMedia(Media media) {

    }

    public void addMetaData(MetaData metaData, long mediaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues metaDataValues = new ContentValues();
        metaDataValues.put(COLUMN_METADATA_URL, metaData.getUrl());
        metaDataValues.put(COLUMN_FORMAT, metaData.getFormat());
        metaDataValues.put(COLUMN_WIDTH, metaData.getWidth());
        metaDataValues.put(COLUMN_HEIGHT, metaData.getHeight());
        metaDataValues.put(COLUMN_MEDIA_ID, metaData.getMediaId());
        long metaDataId = db.insert(TABLE_NAME_METADATA, null, metaDataValues);
        metaData.setId(metaDataId);
    }

    public void deleteMetaData(MetaData metaData) {

    }

    public List<Article> getArticles() {
        List<Article> articleList = new ArrayList<>();
        String selectAllArticles = "select * from " + TABLE_NAME_ARTICLES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllArticles, null);
        SimpleDateFormat format=new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
        if (cursor.moveToFirst())
        do {
            Article article = new Article();
            article.setId(cursor.getLong(0));
            article.setUrl(cursor.getString(1));
            article.setTitle(cursor.getString(2));
            article.setCountType(cursor.getString(3));
            article.setSection(cursor.getString(4));
            article.setByLine(cursor.getString(5));
            article.setAbstractText(cursor.getString(6));
            try {
                article.setPublishedDate(format.parse(cursor.getString(7)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            article.setSource(cursor.getString(8));
            MediaList mediaList=getMedias(article.getId());
            article.setMedia(mediaList);
            articleList.add(article);
        } while (cursor.moveToNext());
        db.close();
        return articleList;
    }

    public MediaList getMedias(long articleId) {
        MediaList mediaList=new MediaList();
        String selectAllMedia = "select * from " + TABLE_NAME_MEDIA+" where " +
                COLUMN_ARTICLE_ID+"=" +
                articleId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllMedia, null);
        cursor.moveToFirst();
        do {
            Media media = new Media();
            media.setId(cursor.getLong(0));
            media.setType(cursor.getString(1));
            media.setSubtype(cursor.getString(2));
            media.setCaption(cursor.getString(3));
            media.setCopyright(cursor.getString(4));
            media.setApprovedForSyndication(cursor.getInt(5));
            media.setArticleID(cursor.getLong(6));
            MetaDataList metaDataList=getMetaDatas(media.getId());
            media.setMediaMetadata(metaDataList);
            mediaList.add(media);
        } while (cursor.moveToNext());
        db.close();
        return mediaList;
    }

    public MetaDataList getMetaDatas(long mediaId) {
        MetaDataList metaDataList=new MetaDataList();
        String selectAllMetadata = "select * from " + TABLE_NAME_METADATA +" where " +
                COLUMN_MEDIA_ID+"=" +
                mediaId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectAllMetadata, null);
        cursor.moveToFirst();
        do {
            MetaData metaData = new MetaData();
            metaData.setId(cursor.getLong(0));
            metaData.setUrl(cursor.getString(1));
            metaData.setFormat(cursor.getString(2));
            metaData.setWidth(cursor.getInt(3));
            metaData.setHeight(cursor.getInt(4));
            metaData.setMediaId(cursor.getLong(5));
            metaDataList.add(metaData);
        } while (cursor.moveToNext());
        db.close();
        return metaDataList;
    }
}
