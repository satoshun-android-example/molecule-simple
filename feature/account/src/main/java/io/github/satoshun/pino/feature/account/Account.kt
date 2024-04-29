package io.github.satoshun.pino.feature.account

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuitx.gesturenavigation.GestureNavigationDecoration
import dagger.hilt.components.SingletonComponent

@CircuitInject(AccountScreen::class, SingletonComponent::class)
@Composable
internal fun Account(
  state: AccountState,
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      AccountTopBar(
        onBack = {
          state.eventSink(AccountEvent.Back)
        },
        onHelp = {
          state.eventSink(AccountEvent.GoToHelp)
        },
      )
    },
    bottomBar = {
      AccountBottomBar(
        currentTab = state.currentTab,
        onBasicClick = {
          state.eventSink(AccountEvent.ToBasic)
        },
        onNetworkClick = {
          state.eventSink(AccountEvent.ToNetwork)
        },
      )
    },
  ) { paddingValues ->
    CircuitCompositionLocals(circuit = state.circuit) {
      Surface(
        Modifier
          .fillMaxSize()
          .padding(paddingValues),
      ) {
        NavigableCircuitContent(
          navigator = state.navigator,
          backStack = state.backStack,
          decoration = GestureNavigationDecoration(
            fallback = state.circuit.defaultNavDecoration,
            // Pop the back stack once the user has gone 'back'
            onBackInvoked = state.navigator::pop,
          ),
        )
      }
    }
  }
}

@Composable
private fun AccountTopBar(
  onBack: () -> Unit,
  onHelp: () -> Unit,
) {
  TopAppBar(
    title = {
      Text(stringResource(R.string.account_title))
    },
    navigationIcon = {
      IconButton(onClick = onBack) {
        Icon(
          imageVector = Icons.AutoMirrored.Default.ArrowBack,
          contentDescription = null,
        )
      }
    },
    actions = {
      IconButton(onClick = onHelp) {
        Icon(
          // TODO Help icon
          imageVector = Icons.Default.Info,
          contentDescription = null,
        )
      }
    },
  )
}

@Composable
private fun AccountBottomBar(
  currentTab: AccountTab,
  onBasicClick: () -> Unit,
  onNetworkClick: () -> Unit,
) {
  NavigationBar {
    NavigationBarItem(
      selected = currentTab == AccountTab.Basic,
      onClick = onBasicClick,
      icon = {
        Icon(
          imageVector = Icons.Default.Build,
          contentDescription = null,
        )
      },
      label = {
        Text(text = stringResource(R.string.account_basic_title))
      },
    )
    NavigationBarItem(
      selected = currentTab == AccountTab.Network,
      onClick = onNetworkClick,
      icon = {
        Icon(
          imageVector = Icons.Default.Done,
          contentDescription = null,
        )
      },
      label = {
        Text(text = stringResource(R.string.account_network_title))
      },
    )
  }
}
