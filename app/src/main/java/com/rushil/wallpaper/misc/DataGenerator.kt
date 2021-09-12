package com.rushil.wallpaper.misc

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rushil.wallpaper.model.Topic
import java.io.InputStreamReader

object DataGenerator {

    private fun getAssetManager(context: Context): AssetManager {
        return context.applicationContext.assets
    }

    fun getTopic(context: Context): ArrayList<Topic>? {
        val asset = getAssetManager(context)
        var gson: ArrayList<Topic>? = null

        try {
            gson = Gson().fromJson(
                InputStreamReader(asset.open("Topic.json")),
                object : TypeToken<ArrayList<Topic>>() {}.type
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        Log.e("TAG","GGGGGG")

        return gson;
    }
}