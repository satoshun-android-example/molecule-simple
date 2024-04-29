package io.github.satoshun.pino.feature.home.notification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.designsystem.produceStateSaveable
import io.github.satoshun.pino.feature.home.notification.data.Notification
import io.github.satoshun.pino.feature.home.notification.data.NotificationRepository

class NotificationPresenter @AssistedInject internal constructor(
  @Assisted private val navigator: Navigator,
  @Assisted private val screen: NotificationScreen,
  private val repository: NotificationRepository,
) : Presenter<NotificationState> {
  @CircuitInject(NotificationScreen::class, SingletonComponent::class)
  @AssistedFactory
  fun interface Factory {
    fun create(
      navigator: Navigator,
      screen: NotificationScreen,
    ): NotificationPresenter
  }

  @Composable
  override fun present(): NotificationState {
    val notifications by produceStateSaveable(initialValue = emptyList<Notification>()) {
      value = repository.getNotifications()
    }
    return NotificationState(
      notifications = notifications,
    ) { event ->
    }
  }
}
