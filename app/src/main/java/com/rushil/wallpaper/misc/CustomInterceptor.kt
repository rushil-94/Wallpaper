package com.rushil.wallpaper.misc

import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val req = chain.request().newBuilder()
        req.addHeader("Authorization", "Client-ID 2EDswo4NrPhfBM89zF-dU1HA4W9rMCGv_NjklC5DabI")

        return chain.proceed(req.build())

    }


}