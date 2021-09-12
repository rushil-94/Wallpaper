package com.rushil.wallpaper.model.apis

import com.rushil.wallpaper.model.Topic
import retrofit2.http.GET


interface ApiServices {
    @GET("/topics")
    suspend fun getTopics(): Topic
}