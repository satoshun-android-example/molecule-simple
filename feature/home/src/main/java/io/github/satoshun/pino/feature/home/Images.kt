package io.github.satoshun.pino.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.satoshun.pino.designsystem.theme.PinoTheme
import io.github.satoshun.pino.share.data.Image

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Images(
  images: List<Image>,
  contentPadding: PaddingValues,
  onImageClick: (Image) -> Unit,
  onImageLongClick: (Image) -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyVerticalGrid(
    modifier = modifier.fillMaxSize(),
    columns = GridCells.Fixed(3),
    contentPadding = contentPadding,
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    items(images, key = { it.url }) {
      AsyncImage(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(1f)
          .background(MaterialTheme.colorScheme.surfaceVariant)
          .combinedClickable(
            onLongClick = { onImageLongClick(it) },
            onClick = { onImageClick(it) },
          ),
        model = it.url,
        contentScale = ContentScale.Crop,
        contentDescription = null,
      )
    }
  }
}

@Preview
@Composable
private fun ImagesPreview() {
  PinoTheme {
    Surface {
      Images(
        images = listOf(
          Image(url = "1"),
          Image(url = "2"),
          Image(url = "3"),
          Image(url = "4"),
        ),
        contentPadding = PaddingValues(0.dp),
        onImageClick = {},
        onImageLongClick = {},
      )
    }
  }
}
