package io.github.satoshun.pino.feature.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.onNavEvent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.feature.account.basic.AccountBasicScreen
import io.github.satoshun.pino.feature.account.network.AccountNetworkScreen
import io.github.satoshun.pino.share.di.DaggerSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

@Parcelize
class AccountScreen : Screen

@Stable
class AccountState(
  val circuit: Circuit,
  val navigator: Navigator,
  val backStack: SaveableBackStack,
  val currentTab: AccountTab,
  val eventSink: (AccountEvent) -> Unit,
) : CircuitUiState

sealed interface AccountEvent {
  data object Back : AccountEvent
  data object ToBasic : AccountEvent
  data object ToNetwork : AccountEvent
  data object GoToHelp : AccountEvent
  class NavEvent(val event: com.slack.circuit.foundation.NavEvent) : AccountEvent
}

class AccountPresenter @AssistedInject constructor(
  private val accountNavigator: AccountNavigator,
  private val presenterFactories: DaggerSet<Presenter.Factory>,
  private val uiFactories: DaggerSet<Ui.Factory>,
  @Assisted private val navigator: Navigator,
  @Assisted private val screen: AccountScreen,
) : Presenter<AccountState> {
  @CircuitInject(AccountScreen::class, SingletonComponent::class)
  @AssistedFactory
  fun interface Factory {
    fun create(
      screen: AccountScreen,
      navigator: Navigator,
    ): AccountPresenter
  }

  @Composable
  override fun present(): AccountState {
    var currentTab by rememberSaveable {
      mutableStateOf(AccountTab.Basic)
    }
    val circuit = remember {
      Circuit.Builder()
        .addPresenterFactories(presenterFactories)
        .addUiFactories(uiFactories)
        .build()
    }

    val stack = remember {
      persistentListOf(AccountBasicScreen())
    }
    val backStack = rememberSaveableBackStack(stack)
    val childNavigator = rememberCircuitNavigator(backStack)

    val basicScreen = remember { AccountBasicScreen() }
    val networkScreen = remember { AccountNetworkScreen() }

    LaunchedEffect(currentTab) {
      when (currentTab) {
        AccountTab.Basic -> childNavigator.resetRoot(
          newRoot = basicScreen,
          saveState = true,
          restoreState = true,
        )
        AccountTab.Network -> childNavigator.resetRoot(
          newRoot = networkScreen,
          saveState = true,
          restoreState = true,
        )
      }
    }

    return AccountState(
      circuit = circuit,
      navigator = childNavigator,
      backStack = backStack,
      currentTab = currentTab,
    ) {
      when (it) {
        AccountEvent.Back -> {
          navigator.pop()
        }
        AccountEvent.GoToHelp -> {
          accountNavigator.gotoHelp(navigator)
        }
        AccountEvent.ToBasic -> {
          currentTab = AccountTab.Basic
        }
        AccountEvent.ToNetwork -> {
          currentTab = AccountTab.Network
        }
        is AccountEvent.NavEvent -> {
          navigator.onNavEvent(it.event)
        }
      }
    }
  }
}

enum class AccountTab {
  Basic,
  Network,
}
