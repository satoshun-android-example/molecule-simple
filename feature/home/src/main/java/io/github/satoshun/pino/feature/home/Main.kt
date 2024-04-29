package io.github.satoshun.pino.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import io.github.satoshun.pino.designsystem.rememberPullToRefreshState

@Composable
internal fun Main(
  parentState: HomeState,
  state: HomeTabState.MainState,
  paddingValues: PaddingValues,
) {
  when (state) {
    is HomeTabState.MainState.Loading -> {
      Box(
        modifier = Modifier
          .padding(paddingValues)
          .fillMaxSize(),
        contentAlignment = Alignment.Center,
      ) {
        CircularProgressIndicator()
      }
    }
    is HomeTabState.MainState.Success -> {
      val pullToRefreshState = rememberPullToRefreshState(
        isRefreshing = state.isRefreshing,
        onRefresh = {
          parentState.eventSink(HomeEvent.Refresh)
        },
      )
      Box(
        modifier = Modifier
          .fillMaxSize()
          .nestedScroll(pullToRefreshState.nestedScrollConnection),
      ) {
        Images(
          images = state.images,
          contentPadding = paddingValues,
          onImageClick = {
            parentState.eventSink(HomeEvent.GoToImageDetail(it))
          },
          onImageLongClick = {
            parentState.eventSink(HomeEvent.GoToImageModalBottom(it))
          },
        )

        PullToRefreshContainer(
          modifier = Modifier
            .padding(paddingValues)
            .align(Alignment.TopCenter),
          state = pullToRefreshState,
        )
      }
    }
  }
}
