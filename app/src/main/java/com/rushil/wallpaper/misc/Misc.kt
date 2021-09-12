package com.rushil.wallpaper.misc

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.integerResource
import com.rushil.wallpaper.R
import com.rushil.wallpaper.model.apis.ApiServices
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int
) {
    object Profile : Screen("profile", R.string.profile, iconId = R.drawable.ic_launcher_foreground)
    object Topic : Screen("topic", R.string.topic, iconId = R.drawable.ic_launcher_foreground)
    object Search : Screen("search", R.string.search, iconId = R.drawable.ic_launcher_foreground)
    object Home : Screen("home", R.string.home, iconId = R.drawable.ic_launcher_foreground)

}

fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.unsplash.com")

        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(CustomInterceptor()).build())
        .build()
}

fun getApiService(): ApiServices {
    val retrofit = getRetrofit()
    return retrofit.create(ApiServices::class.java)
}


 fun loadImage(url: String): Bitmap? {
    Log.e("TAG", url)
    val retrofit = getRetrofit()
    var bitmap: Bitmap? = null
    val request = Request.Builder()
        .url(url)
        .get()
        .build()

    retrofit.callFactory().newCall(request).execute().let {
        if (it.isSuccessful) {
            bitmap = BitmapFactory.decodeStream(it.body()?.byteStream())
            Log.e("TAG", bitmap.toString())
        }
    }


//    retrofit.callFactory().newCall(request).enqueue(object : Callback {
//        override fun onFailure(call: okhttp3.Call, e: IOException) {
//            Log.e("TAG", e.message.toString())
//            e.printStackTrace()
//        }
//
//        override fun onResponse(call: okhttp3.Call, response: Response) {
//            if (response.isSuccessful) {
//                bitmap.value = BitmapFactory.decodeStream(response.body()?.byteStream())
//                Log.e("TAG", bitmap.toString())
//            }
//        }
//
//    })


    return bitmap

}


