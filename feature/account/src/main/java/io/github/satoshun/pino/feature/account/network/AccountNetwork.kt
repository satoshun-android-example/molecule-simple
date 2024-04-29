package io.github.satoshun.pino.feature.account.network

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.designsystem.SectionTitle
import io.github.satoshun.pino.feature.account.Entry
import io.github.satoshun.pino.feature.account.R

@OptIn(ExperimentalFoundationApi::class)
@CircuitInject(AccountNetworkScreen::class, SingletonComponent::class)
@Composable
internal fun AccountNetwork(
  state: AccountNetworkState,
  modifier: Modifier = Modifier,
) {
  LazyColumn(modifier = modifier) {
    item {
      SectionTitle(stringResource(R.string.account_network))
    }
    item { Spacer(Modifier.height(12.dp)) }

    when (state) {
      AccountNetworkState.Loading -> {
        item {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
          ) {
            CircularProgressIndicator()
          }
        }
      }
      is AccountNetworkState.Success -> {
        itemsIndexed(state.items, key = { _, entry -> entry }) { index, entry ->
          Entry(
            modifier = Modifier.animateItemPlacement(),
            text = stringResource(entry.title),
            index = index,
            lastIndex = state.items.lastIndex,
            onClick = {
              // TODO
            },
          )
        }
      }
    }
  }
}
