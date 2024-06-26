/*
 * Copyright (C) 2022 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.satoshun.pino

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AndroidUiDispatcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.annotations.VisibleForTesting

abstract class MoleculeViewModel<Model> : ViewModel() {
  private val scope = CoroutineScope(viewModelScope.coroutineContext + AndroidUiDispatcher.Main)

  val models: StateFlow<Model> by lazy(LazyThreadSafetyMode.NONE) {
    scope.launchMolecule(mode = RecompositionMode.ContextClock) {
      models()
    }
  }

  @Composable
  protected abstract fun models(): Model

  @Composable
  @VisibleForTesting
  fun testModels(): Model {
    return models()
  }
}
