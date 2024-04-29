package io.github.satoshun.pino.feature.account.network

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.feature.account.R
import kotlinx.coroutines.delay
import kotlinx.parcelize.Parcelize

@Parcelize
class AccountNetworkScreen : Screen

@Stable
sealed interface AccountNetworkState : CircuitUiState {
  data object Loading : AccountNetworkState

  data class Success(
    val items: List<AccountNetworkType>,
  ) : AccountNetworkState
}

sealed interface AccountNetworkEvent

class AccountNetworkPresenter @AssistedInject constructor(
  @Assisted private val navigator: Navigator,
) : Presenter<AccountNetworkState> {
  @CircuitInject(AccountNetworkScreen::class, SingletonComponent::class)
  @AssistedFactory
  fun interface Factory {
    fun create(
      navigator: Navigator,
    ): AccountNetworkPresenter
  }

  @Composable
  override fun present(): AccountNetworkState {
    var items by rememberRetained {
      mutableStateOf(emptyList<AccountNetworkType>())
    }
    LaunchedEffect(Unit) {
      delay(4000)
      items = AccountNetworkType.entries.shuffled()
    }

    return when {
      items.isEmpty() -> AccountNetworkState.Loading
      else -> AccountNetworkState.Success(
        items = items,
      )
    }
  }
}


enum class AccountNetworkType(
  @StringRes val title: Int,
) {
  Wifi(title = R.string.account_network_wifi),
  Bluetooth(title = R.string.account_network_bluetooth),
  Ethernet(title = R.string.account_network_ethernet),
  Mobile(title = R.string.account_network_mobile),
}
