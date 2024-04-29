package io.github.satoshun.pino.feature.detail.imageinfo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent
import io.github.satoshun.pino.feature.detail.R
import io.github.satoshun.pino.share.data.Image

@CircuitInject(DetailImageInfoScreen::class, SingletonComponent::class)
@Composable
internal fun DetailImageInfo(
  state: DetailImageInfoState,
  modifier: Modifier = Modifier,
) {
  LazyColumn(modifier = modifier) {
    item {
      ImagePathInfo(
        image = state.image,
        onClick = { image ->
          state.eventSink(DetailImageInfoEvent.OnImagePathClicked(image))
        }
      )
    }
  }
}

@Composable
private fun ImagePathInfo(
  image: Image,
  onClick: (Image) -> Unit,
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick(image) }
      .padding(horizontal = 16.dp),
  ) {
    Text(text = stringResource(R.string.detail_tab_image_path))
    Spacer(Modifier.height(4.dp))
    Text(text = image.url)
  }
}
