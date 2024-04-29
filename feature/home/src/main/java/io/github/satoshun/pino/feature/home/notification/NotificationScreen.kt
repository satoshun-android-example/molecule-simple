package io.github.satoshun.pino.feature.home.notification

import androidx.compose.runtime.Stable
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import io.github.satoshun.pino.feature.home.notification.data.Notification
import kotlinx.parcelize.Parcelize

@Parcelize
class NotificationScreen : Screen

@Stable
class NotificationState(
  val notifications: List<Notification>,
  val eventSink: (NotificationEvent) -> Unit,
) : CircuitUiState

@Stable
sealed interface NotificationEvent
