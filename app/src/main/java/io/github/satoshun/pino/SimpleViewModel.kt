package io.github.satoshun.pino

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow

@Stable
data class SimpleModel(
  val loading: Boolean,
  val nane: String,
)

sealed interface SimpleEvent {
  data object Tap : SimpleEvent
}

class SimpleViewModel : MoleculeViewModel<SimpleEvent, SimpleModel>() {
  @Composable
  override fun models(events: Flow<SimpleEvent>): SimpleModel {
    var isLoading by remember { mutableStateOf(false) }
    val name by remember { mutableStateOf("name") }

    LaunchedEffect(Unit) {
      events.collect { event ->
        when (event) {
          SimpleEvent.Tap -> {
            isLoading = !isLoading
          }
        }
      }
    }

    return SimpleModel(
      loading = isLoading,
      nane = name,
    )
  }
}
