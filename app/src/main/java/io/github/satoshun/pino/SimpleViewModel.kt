package io.github.satoshun.pino

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
data class SimpleModel(
  val loading: Boolean,
  val name: String,
  val eventSink: (SimpleEvent) -> Unit,
)

sealed interface SimpleEvent {
  data object Tap : SimpleEvent
}

class SimpleViewModel : MoleculeViewModel<SimpleModel>() {
  @Composable
  override fun models(): SimpleModel {
    var isLoading by remember { mutableStateOf(false) }
    val name by remember { mutableStateOf("name") }

    return SimpleModel(
      loading = isLoading,
      name = name,
      eventSink = { event ->
        when (event) {
          SimpleEvent.Tap -> {
            isLoading = !isLoading
          }
        }
      },
    )
  }
}
