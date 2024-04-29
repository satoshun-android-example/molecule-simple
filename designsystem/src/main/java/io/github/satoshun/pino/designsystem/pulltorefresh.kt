package io.github.satoshun.pino.designsystem

import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun rememberPullToRefreshState(
  isRefreshing: Boolean,
  onRefresh: () -> Unit,
): PullToRefreshState {
  val state = rememberPullToRefreshState()
  var uiRefreshTriggered by remember { mutableStateOf(false) }
  if (state.isRefreshing) {
    LaunchedEffect(Unit) {
      onRefresh()
      delay(500)
      uiRefreshTriggered = true
    }
  }
  LaunchedEffect(isRefreshing, uiRefreshTriggered) {
    if (uiRefreshTriggered && state.isRefreshing && !isRefreshing) {
      uiRefreshTriggered = false
      state.endRefresh()
    }
  }
  return state
}
