package io.github.satoshun.pino.feature.account.basic

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.feature.account.AccountNavigator
import io.github.satoshun.pino.feature.account.R
import io.github.satoshun.pino.share.ui.rememberEventSink
import kotlinx.parcelize.Parcelize

@Parcelize
class AccountBasicScreen : Screen

@Stable
class AccountBasicState(
  val eventSink: (AccountBasicEvent) -> Unit,
) : CircuitUiState

sealed interface AccountBasicEvent : CircuitUiEvent {
  data object Back : AccountBasicEvent
  data object GoToHelp : AccountBasicEvent
}

class AccountBasicPresenter @AssistedInject constructor(
  private val accountNavigator: AccountNavigator,
  @Assisted private val navigator: Navigator,
  @Assisted private val screen: AccountBasicScreen,
) : Presenter<AccountBasicState> {
  @CircuitInject(AccountBasicScreen::class, SingletonComponent::class)
  @AssistedFactory
  fun interface Factory {
    fun create(
      screen: AccountBasicScreen,
      navigator: Navigator,
    ): AccountBasicPresenter
  }

  @Composable
  override fun present(): AccountBasicState {
    val eventSink: (AccountBasicEvent) -> Unit = rememberEventSink {
      when (it) {
        AccountBasicEvent.Back -> {
          navigator.pop()
        }
        AccountBasicEvent.GoToHelp -> {
          accountNavigator.gotoHelp(navigator)
        }
      }
    }

    return AccountBasicState(eventSink = eventSink)
  }
}

enum class AccountBasicType(
  @StringRes val title: Int,
) {
  Language(title = R.string.account_language),
  Notification(title = R.string.account_notification),
  Theme(title = R.string.account_theme),
  Network(title = R.string.account_network),
}

enum class AccountDetailType(
  @StringRes val title: Int,
) {
  Download(title = R.string.account_download),
  Site(title = R.string.account_site),
  About(title = R.string.account_about),
}
