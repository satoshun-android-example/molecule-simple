package io.github.satoshun.pino.feature.home.notification.data

import kotlinx.coroutines.delay
import javax.inject.Inject

internal class NotificationRepository @Inject constructor() {
  suspend fun getNotifications(): List<Notification> {
    delay(4000)
    return listOf(
      Notification("1", "title1"),
      Notification("2", "title2"),
      Notification("3", "title3"),
    )
  }
}

data class Notification(
  val id: String,
  val title: String,
)
