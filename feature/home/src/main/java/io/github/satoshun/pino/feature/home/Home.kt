package io.github.satoshun.pino.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.CircuitContent
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.feature.home.favorite.Favorite
import io.github.satoshun.pino.feature.home.notification.NotificationScreen

@CircuitInject(HomeScreen::class, SingletonComponent::class)
@Composable
internal fun Home(
  state: HomeState,
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      HomeTopBar(onAccountClick = {
        state.eventSink(HomeEvent.TopBar.AccountClick)
      })
    },
    bottomBar = {
      HomeBottomBar(
        currentTab = state.currentTab,
        onHomeClick = {
          state.eventSink(HomeEvent.ChangeTab(HomeTab.Home))
        },
        onSearchClick = {
          state.eventSink(HomeEvent.ChangeTab(HomeTab.Search))
        },
        onFavoriteClick = {
          state.eventSink(HomeEvent.ChangeTab(HomeTab.Favorite))
        },
        onNotificationClick = {
          state.eventSink(HomeEvent.ChangeTab(HomeTab.Notification))
        },
      )
    },
    contentWindowInsets = WindowInsets(0.dp),
  ) { paddingValues ->
    when (state.tabState) {
      is HomeTabState.MainState -> {
        Main(state, state.tabState, paddingValues)
      }
      is HomeTabState.SearchState -> {
        Search(state, state.tabState, paddingValues)
      }
      is HomeTabState.FavoriteState -> {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        ) {
          Favorite(state, state.tabState)
        }
      }
      HomeTabState.NotificationState -> {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        ) {
          CircuitContent(NotificationScreen())
        }
      }
    }
  }
}

@Composable
private fun HomeTopBar(
  onAccountClick: () -> Unit,
) {
  TopAppBar(
    title = {
      Text(text = stringResource(R.string.home_title))
    },
    actions = {
      IconButton(onClick = onAccountClick) {
        Icon(
          imageVector = Icons.Default.AccountCircle,
          contentDescription = null,
        )
      }
    },
  )
}

@Composable
private fun HomeBottomBar(
  currentTab: HomeTab,
  onHomeClick: () -> Unit,
  onSearchClick: () -> Unit,
  onFavoriteClick: () -> Unit,
  onNotificationClick: () -> Unit,
) {
  NavigationBar {
    NavigationBarItem(
      selected = currentTab == HomeTab.Home,
      onClick = onHomeClick,
      icon = {
        Icon(
          imageVector = Icons.Default.Home,
          contentDescription = null,
        )
      },
      label = {
        Text(text = stringResource(R.string.home_main))
      },
    )
    NavigationBarItem(
      selected = currentTab == HomeTab.Search,
      onClick = onSearchClick,
      icon = {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = null,
        )
      },
      label = {
        Text(text = stringResource(R.string.home_search))
      },
    )
    NavigationBarItem(
      selected = currentTab == HomeTab.Favorite,
      onClick = onFavoriteClick,
      icon = {
        Icon(
          imageVector = Icons.Default.Favorite,
          contentDescription = null,
        )
      },
      label = {
        Text(text = stringResource(R.string.home_favorite))
      },
    )
    NavigationBarItem(
      selected = currentTab == HomeTab.Notification,
      onClick = onNotificationClick,
      icon = {
        Icon(
          imageVector = Icons.Default.Notifications,
          contentDescription = null,
        )
      },
      label = {
        Text(text = stringResource(R.string.home_notification))
      },
    )
  }
}
