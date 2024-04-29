@file:OptIn(ExperimentalFoundationApi::class)

package io.github.satoshun.pino.feature.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.CircuitContent
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.feature.detail.image.DetailImageScreen
import io.github.satoshun.pino.feature.detail.imageinfo.DetailImageInfoScreen

@CircuitInject(DetailScreen::class, SingletonComponent::class)
@Composable
internal fun Detail(
  state: DetailState,
  modifier: Modifier = Modifier,
) {
  val pagerState = rememberPagerState(state.currentTab) { 2 }
  LaunchedEffect(state.currentTab) {
    if (pagerState.currentPage != state.currentTab) {
      pagerState.animateScrollToPage(state.currentTab)
    }
  }

  Scaffold(
    modifier = modifier,
    topBar = {
      DetailTopBar(
        onBack = {
          state.eventSink(DetailEvent.OnBack)
        },
      )
    },
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
    ) {
      DetailTabRow(
        selectedTabIndex = pagerState.currentPage,
        onTabSelected = {
          state.eventSink(DetailEvent.OnTabSelect(it))
        },
      )

      HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
        beyondBoundsPageCount = 1,
      ) {
        when (it) {
          0 -> CircuitContent(screen = DetailImageScreen(state.image))
          1 -> CircuitContent(
            screen = DetailImageInfoScreen(state.image),
            onNavEvent = { event ->
              state.eventSink(DetailEvent.OnNavEvent(event))
            },
          )
        }
      }
    }
  }
}

@Composable
fun DetailTopBar(onBack: () -> Unit) {
  TopAppBar(
    title = { },
    navigationIcon = {
      IconButton(onClick = onBack) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = null,
        )
      }
    },
  )
}

@Composable
private fun DetailTabRow(
  selectedTabIndex: Int,
  onTabSelected: (Int) -> Unit,
) {
  TabRow(
    modifier = Modifier.fillMaxWidth(),
    selectedTabIndex = selectedTabIndex,
  ) {
    Tab(
      selected = selectedTabIndex == 0,
      onClick = { onTabSelected(0) },
      text = { Text(text = stringResource(R.string.detail_tab_image)) },
    )
    Tab(
      selected = selectedTabIndex == 1,
      onClick = { onTabSelected(1) },
      text = { Text(text = stringResource(R.string.detail_tab_info)) },
    )
  }
}
