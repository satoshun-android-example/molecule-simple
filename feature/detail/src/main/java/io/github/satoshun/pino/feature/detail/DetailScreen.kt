package io.github.satoshun.pino.feature.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.NavEvent
import com.slack.circuit.foundation.onNavEvent
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.share.data.Image
import io.github.satoshun.pino.share.ui.rememberEventSink
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailScreen(
  val image: Image,
) : Screen

@Stable
data class DetailState(
  val image: Image,
  val currentTab: Int,
  val eventSink: (DetailEvent) -> Unit,
) : CircuitUiState

@Stable
sealed interface DetailEvent : CircuitUiEvent {
  class OnTabSelect(val index: Int) : DetailEvent
  data object OnBack : DetailEvent
  class OnNavEvent(val event: NavEvent) : DetailEvent
}

class DetailPresenter @AssistedInject constructor(
  @Assisted private val navigator: Navigator,
  @Assisted private val screen: DetailScreen,
) : Presenter<DetailState> {
  @CircuitInject(DetailScreen::class, SingletonComponent::class)
  @AssistedFactory
  fun interface Factory {
    fun create(
      screen: DetailScreen,
      navigator: Navigator,
    ): DetailPresenter
  }

  @Composable
  override fun present(): DetailState {
    var currentTab by remember { mutableIntStateOf(0) }

    val eventSink: (DetailEvent) -> Unit = rememberEventSink { event ->
      when (event) {
        is DetailEvent.OnTabSelect -> {
          currentTab = event.index
        }
        DetailEvent.OnBack -> {
          navigator.pop()
        }
        is DetailEvent.OnNavEvent -> {
          navigator.onNavEvent(event.event)
        }
      }
    }

    return DetailState(
      image = screen.image,
      currentTab = currentTab,
      eventSink = eventSink,
    )
  }
}
