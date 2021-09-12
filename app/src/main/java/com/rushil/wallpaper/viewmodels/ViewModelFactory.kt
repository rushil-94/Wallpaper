package com.rushil.wallpaper.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rushil.wallpaper.misc.getApiService
import com.rushil.wallpaper.model.repositories.NetworkRepository

class MainViewModelFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(application = application, NetworkRepository(getApiService())) as T
    }
}