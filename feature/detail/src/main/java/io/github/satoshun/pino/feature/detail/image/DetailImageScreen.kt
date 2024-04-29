package io.github.satoshun.pino.feature.detail.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.share.data.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailImageScreen(
  val image: Image,
) : Screen

@Stable
data class DetailImageState(
  val image: Image,
  val eventSink: (DetailImageEvent) -> Unit,
) : CircuitUiState

sealed interface DetailImageEvent

class DetailImagePresenter @AssistedInject constructor(
  @Assisted private val navigator: Navigator,
  @Assisted private val screen: DetailImageScreen,
) : Presenter<DetailImageState> {
  @CircuitInject(DetailImageScreen::class, SingletonComponent::class)
  @AssistedFactory
  fun interface Factory {
    fun create(
      screen: DetailImageScreen,
      navigator: Navigator,
    ): DetailImagePresenter
  }

  @Composable
  override fun present(): DetailImageState {
    return DetailImageState(
      image = screen.image,
    ) { event ->
    }
  }
}
