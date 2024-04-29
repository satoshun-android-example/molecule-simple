package io.github.satoshun.pino.feature.detail.imageinfo

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuitx.android.IntentScreen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.share.data.Image
import io.github.satoshun.pino.share.ui.rememberEventSink
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailImageInfoScreen(
  val image: Image,
) : Screen

@Stable
data class DetailImageInfoState(
  val image: Image,
  val eventSink: (DetailImageInfoEvent) -> Unit,
) : CircuitUiState

sealed interface DetailImageInfoEvent : CircuitUiEvent {
  data class OnImagePathClicked(val image: Image) : DetailImageInfoEvent
}

class DetailImageInfoPresenter @AssistedInject constructor(
  @Assisted private val navigator: Navigator,
  @Assisted private val screen: DetailImageInfoScreen,
) : Presenter<DetailImageInfoState> {
  @CircuitInject(DetailImageInfoScreen::class, SingletonComponent::class)
  @AssistedFactory
  fun interface Factory {
    fun create(
      screen: DetailImageInfoScreen,
      navigator: Navigator,
    ): DetailImageInfoPresenter
  }

  @Composable
  override fun present(): DetailImageInfoState {
    val context = LocalContext.current

    val eventSink: (DetailImageInfoEvent) -> Unit = rememberEventSink { event ->
      when (event) {
        is DetailImageInfoEvent.OnImagePathClicked -> {

          // TODO(satoshun) not working cuz detail image info screen is full overlay
//          val intent = Intent(Intent.ACTION_VIEW)
//          intent.setData(event.image.url.toUri())
//          navigator.goTo(IntentScreen(intent))

          val intent = Intent(Intent.ACTION_VIEW)
          intent.setData(event.image.url.toUri())
          context.startActivity(intent)
        }
      }
    }

    return DetailImageInfoState(
      image = screen.image,
      eventSink = eventSink,
    )
  }
}
