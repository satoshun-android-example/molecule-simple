package io.github.satoshun.pino.feature.detail.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.components.SingletonComponent

@CircuitInject(DetailImageScreen::class, SingletonComponent::class)
@Composable
internal fun DetailImage(
  state: DetailImageState,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    AsyncImage(
      modifier = Modifier.fillMaxWidth(),
      model = state.image.url,
      contentScale = ContentScale.Crop,
      contentDescription = null,
    )
  }
}
