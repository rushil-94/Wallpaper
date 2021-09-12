package com.rushil.wallpaper.model.repositories

import com.rushil.wallpaper.model.apis.ApiServices

class NetworkRepository(private val apiServices: ApiServices) {
    suspend fun getTopics() = apiServices.getTopics()
}