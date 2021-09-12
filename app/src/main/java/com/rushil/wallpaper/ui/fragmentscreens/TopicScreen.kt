package com.rushil.wallpaper.ui.fragmentscreens

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rushil.wallpaper.misc.loadImage
import com.rushil.wallpaper.model.Topic
import com.rushil.wallpaper.model.TopicResponse
import com.rushil.wallpaper.viewmodels.MainViewModel
import com.rushil.wallpaper.viewmodels.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun TopicScreen(model: MainViewModel) {

    model.getTopics()
    model.topic?.let {
        TopicList(topicList = it)
    }
}

@Composable
fun TopicList(topicList: List<Topic>) {
    LazyColumn {
        items(items = topicList) { item ->
            TopicListItem(topic = item)
        }
    }
}

@Composable
fun TopicListItem(topic: Topic?) {
    val bitmap: MutableState<Bitmap?> = rememberSaveable { mutableStateOf(null) }

    Column(modifier = Modifier.fillMaxWidth()) {
        topic?.coverPhoto?.urls?.regular?.let { url ->
            LaunchedEffect(key1 = url) {
                withContext(Dispatchers.IO) {
                    bitmap.value = loadImage(url)
                }

            }
        }


        TopicImage(bitmap = bitmap.value)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = topic?.title ?: "",
            modifier = Modifier.padding(vertical = 12.dp),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TopicImage(bitmap: Bitmap?) {
    Log.e("TAG", "$bitmap   ********")
    bitmap?.let {
        Log.e("TAG", "$it.toString()  ***")
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),

            )
    }
}

@Preview
@Composable
fun TopicListItemPreview() {
    TopicListItem(topic = null)
}