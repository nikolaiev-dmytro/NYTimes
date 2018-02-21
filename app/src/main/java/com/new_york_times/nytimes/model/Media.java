package com.new_york_times.nytimes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;

/**
 * Created by user on 18.02.2018.
 */

public class Media {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("approved_for_syndication")
    @Expose
    private int approvedForSyndication;
    @SerializedName("media-metadata")
    @Expose
    private MetaDataList mediaMetadata;

    public Media() {
    }

    public Media(String type, String subtype, String caption, String copyright, int approvedForSyndication) {
        this.type = type;
        this.subtype = subtype;
        this.caption = caption;
        this.copyright = copyright;
        this.approvedForSyndication = approvedForSyndication;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public void setApprovedForSyndication(int approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }

    public MetaDataList getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(MetaDataList mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Media media = (Media) o;

        return new EqualsBuilder()
                .append(approvedForSyndication, media.approvedForSyndication)
                .append(type, media.type)
                .append(subtype, media.subtype)
                .append(caption, media.caption)
                .append(copyright, media.copyright)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(type)
                .append(subtype)
                .append(caption)
                .append(copyright)
                .append(approvedForSyndication)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Media{" +
                "type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", caption='" + caption + '\'' +
                ", copyright='" + copyright + '\'' +
                ", approvedForSyndication=" + approvedForSyndication +
                '}';
    }
}
