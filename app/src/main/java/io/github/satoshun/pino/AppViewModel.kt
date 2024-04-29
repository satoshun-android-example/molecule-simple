package io.github.satoshun.pino

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.satoshun.pino.share.network.ExampleService
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = AppViewModelFactory::class)
class AppViewModel @AssistedInject constructor(
  @Assisted val movieId: Int,
) : ViewModel() {

  private val service = ExampleService()

  init {
    viewModelScope.launch {
      runCatching { service.getGitHub() }
        .onSuccess { println("githubgithub: $it") }
        .onFailure { println("githubgithub: $it") }
    }
  }
}

@AssistedFactory
interface AppViewModelFactory {
  fun create(movieId: Int): AppViewModel
}
