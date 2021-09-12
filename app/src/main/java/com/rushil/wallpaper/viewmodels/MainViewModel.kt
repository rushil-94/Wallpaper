package com.rushil.wallpaper.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rushil.wallpaper.misc.DataGenerator
import com.rushil.wallpaper.model.Topic
import com.rushil.wallpaper.model.repositories.NetworkRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val networkRepository: NetworkRepository) :
    AndroidViewModel(application) {

    val topic = ArrayList<Topic>()

    fun getTopics() {
        viewModelScope.launch {
//            topic.value = networkRepository.getTopics()

            DataGenerator.getTopic(getApplication())?.let { topic.addAll(it) }

        }
    }


}