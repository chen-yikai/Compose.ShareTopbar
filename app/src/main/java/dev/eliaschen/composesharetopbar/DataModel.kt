package dev.eliaschen.composesharetopbar

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson

data class Media(
    val title: String,
    val dateTime: String,
    val hall: List<String>,
    val content: String
)

class DataModel(private val context: Application) : AndroidViewModel(context) {
    val mediaItems = mutableStateListOf<Media>()
    var currentIndex by mutableIntStateOf(0)

    init {
        getMediaData()
    }

    fun getMediaData() {
        val jsonText = context.assets.open("媒體中心.json").bufferedReader().readText()
        val gson = Gson().fromJson(jsonText, Array<Media>::class.java)
        mediaItems.clear()
        mediaItems.addAll(gson)
    }
}