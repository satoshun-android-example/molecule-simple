package io.github.satoshun.pino.feature.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun Entry(
  text: String,
  index: Int,
  lastIndex: Int,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier
      .fillMaxWidth()
      .clickable { onClick() },
  ) {
    Box(
      modifier = Modifier.padding(vertical = 16.dp),
    ) {
      Text(
        modifier = Modifier
          .padding(horizontal = 16.dp)
          .align(Alignment.CenterStart),
        text = text,
      )
    }

    if (index != lastIndex) {
      HorizontalDivider(
        Modifier
          .fillMaxWidth()
          .align(Alignment.BottomStart),
      )
    }
  }
}
