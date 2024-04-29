package io.github.satoshun.pino.feature.home

import androidx.compose.runtime.Stable
import com.slack.circuit.overlay.OverlayHost
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.Screen
import io.github.satoshun.pino.share.data.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data object HomeScreen : Screen

@Stable
class HomeState(
  val tabState: HomeTabState,
  val currentTab: HomeTab,
  val eventSink: (HomeEvent) -> Unit,
) : CircuitUiState

@Stable
sealed interface HomeTabState {

  @Stable
  sealed interface MainState : HomeTabState {
    @Stable
    data object Loading : MainState

    @Stable
    data class Success(
      val images: List<Image>,
      val isRefreshing: Boolean,
    ) : MainState
  }

  @Stable
  sealed interface SearchState : HomeTabState {
    val searchResults: List<Image>?

    @Stable
    data class Loading(
      override val searchResults: List<Image>?,
    ) : SearchState

    @Stable
    data class Success(
      override val searchResults: List<Image>?,
    ) : SearchState
  }

  @Stable
  sealed interface FavoriteState : HomeTabState {
    @Stable
    class Loading() : FavoriteState

    @Stable
    class Success() : FavoriteState
  }

  @Stable
  data object NotificationState : HomeTabState
}

enum class HomeTab(
  val index: Int,
) {
  Home(0),
  Search(1),
  Favorite(2),
  Notification(3),
}

@Stable
sealed interface HomeEvent {
  data class GoToImageDetail(
    val image: Image
  ) : HomeEvent

  data class GoToImageModalBottom(
    val image: Image
  ) : HomeEvent

  data class GoToImageDialog(
    val image: Image
  ) : HomeEvent

  data object Refresh : HomeEvent

  data class ChangeTab(
    val tab: HomeTab,
  ) : HomeEvent

  data class Search(
    val query: String,
  ) : HomeEvent

  sealed interface TopBar : HomeEvent {
    data object AccountClick : TopBar
  }
}

@Stable
interface HomeNavigator {
  suspend fun goToDetail(host: OverlayHost, image: Image)
  fun goToAccount(navigator: Navigator)
}
