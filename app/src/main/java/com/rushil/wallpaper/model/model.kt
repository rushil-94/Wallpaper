package com.rushil.wallpaper.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//data class Topic(val list: List<TopicResponse>)

data class Topic(
    val id: String,
    val title: String,
    val status: String,
    @SerializedName("cover_photo") @Expose val coverPhoto: CoverPhoto
)

data class TopicResponse(
    val id: String,
    val title: String,
    val status: String,
    @SerializedName("cover_photo") @Expose val coverPhoto: CoverPhoto
)

data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class CoverPhoto(val id: String, val urls: Urls)
