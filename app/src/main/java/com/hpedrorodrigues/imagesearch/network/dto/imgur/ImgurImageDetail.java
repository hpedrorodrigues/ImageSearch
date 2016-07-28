package com.hpedrorodrigues.imagesearch.network.dto.imgur;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ImgurImageDetail {

    private String id;

    private String title;

    private String description;

    private Long datetime;

    private String cover;

    @SerializedName("cover_width")
    private Integer coverWidth;

    @SerializedName("cover_height")
    private Integer coverHeight;

    @SerializedName("account_id")
    private String accountId;

    @SerializedName("account_url")
    private String accountUrl;

    private String privacy;

    private String layout;

    private String link;

    private Integer ups;

    private Integer downs;

    private Integer points;

    private Integer score;

    @SerializedName("is_album")
    private Boolean isAlbum;

    private String vote;

    private Boolean favorite;

    private Boolean nsfw;

    private String section;

    @SerializedName("comment_count")
    private Integer commentCount;

    @SerializedName("topic_id")
    private Integer topicId;

    private String topic;

    @SerializedName("images_count")
    private Integer imagesCount;

    @SerializedName("in_gallery")
    private Boolean inGallery;

    @SerializedName("is_ad")
    private Boolean isAd;

    private Integer views;

    private String type;
}