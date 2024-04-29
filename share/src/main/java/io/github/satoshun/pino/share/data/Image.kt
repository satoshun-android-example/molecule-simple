package io.github.satoshun.pino.share.data

import android.os.Parcelable
import kotlinx.coroutines.delay
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class Image(
  val url: String,
) : Parcelable

class HomeRepository @Inject constructor() {
  suspend fun getImages(seed: Int): List<Image> {
    delay(3000)
    val start = seed * 100
    return (start..start + 100).map {
      Image("https://picsum.photos/id/$it/200/300")
    }
  }

  suspend fun searchImages(query: String): List<Image> {
    delay(3000)
    return (100 + query.length..200).map {
      Image("https://picsum.photos/id/$it/200/300")
    }
  }
}
