package io.github.satoshun.pino.feature.account.basic

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.designsystem.SectionTitle
import io.github.satoshun.pino.feature.account.Entry
import io.github.satoshun.pino.feature.account.R

@CircuitInject(AccountBasicScreen::class, SingletonComponent::class)
@Composable
internal fun AccountBasic(
  state: AccountBasicState,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
  ) {
    basic()
    item { Spacer(Modifier.height(18.dp)) }
    detail()
  }
}

private fun LazyListScope.basic() {
  item {
    SectionTitle(stringResource(R.string.account_basic_title))
  }

  item { Spacer(Modifier.height(12.dp)) }

  itemsIndexed(AccountBasicType.entries, key = { _, entry -> entry }) { index, entry ->
    Entry(
      text = stringResource(entry.title),
      index = index,
      lastIndex = AccountBasicType.entries.lastIndex,
      onClick = {
        // TODO
      },
    )
  }
}

private fun LazyListScope.detail() {
  item {
    SectionTitle(stringResource(R.string.account_detail_title))
  }

  item { Spacer(Modifier.height(12.dp)) }

  itemsIndexed(AccountDetailType.entries, key = { _, entry -> entry }) { index, entry ->
    Entry(
      text = stringResource(entry.title),
      index = index,
      lastIndex = AccountDetailType.entries.lastIndex,
      onClick = {
        // TODO
      },
    )
  }
}
