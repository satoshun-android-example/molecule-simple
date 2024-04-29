package io.github.satoshun.pino.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.satoshun.pino.share.data.Image

@Composable
internal fun ImageModalBottom(
  image: Image,
  onClick: (Image) -> Unit,
) {
  Column(Modifier.heightIn(min = 320.dp)) {
    Text(text = image.url)
    Spacer(Modifier.height(8.dp))
    Button(onClick = { onClick(image) }) {
      Text(text = stringResource(R.string.home_goto_detail))
    }
  }
}
