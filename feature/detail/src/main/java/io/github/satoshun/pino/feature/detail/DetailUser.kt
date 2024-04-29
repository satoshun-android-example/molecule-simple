package io.github.satoshun.pino.feature.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class DetailUser(
  val id: Int,
  val name: String,
) : Parcelable
