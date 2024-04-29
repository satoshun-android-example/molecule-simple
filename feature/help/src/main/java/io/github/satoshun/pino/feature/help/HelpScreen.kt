package io.github.satoshun.pino.feature.help

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.designsystem.produceStateSaveable
import io.github.satoshun.pino.feature.help.data.HelpItem
import io.github.satoshun.pino.feature.help.data.HelpRepository
import kotlinx.parcelize.Parcelize

@Parcelize
class HelpScreen : Screen

@Stable
class HelpState(
  val popularHelp: List<HelpItem>,
  val eventSink: (HelpEvent) -> Unit,
) : CircuitUiState

sealed interface HelpEvent {
  data object Back : HelpEvent
}

class HelpPresenter @AssistedInject internal constructor(
  @Assisted private val navigator: Navigator,
  @Assisted private val screen: HelpScreen,
  private val repository: HelpRepository,
) : Presenter<HelpState> {
  @CircuitInject(HelpScreen::class, SingletonComponent::class)
  @AssistedFactory
  fun interface Factory {
    fun create(
      screen: HelpScreen,
      navigator: Navigator,
    ): HelpPresenter
  }

  @Composable
  override fun present(): HelpState {
    val popularHelp: List<HelpItem> by produceStateSaveable(initialValue = emptyList()) {
      value = repository.getPopularHelp()
    }

    return HelpState(
      popularHelp = popularHelp,
      eventSink = {
        when (it) {
          HelpEvent.Back -> navigator.pop()
        }
      },
    )
  }
}
