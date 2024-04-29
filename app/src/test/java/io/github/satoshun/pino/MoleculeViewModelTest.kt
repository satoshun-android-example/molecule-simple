package io.github.satoshun.pino

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import app.cash.turbine.ReceiveTurbine
import app.cash.turbine.test

suspend fun <V : MoleculeViewModel<Event, Model>, Event, Model> V.test(
  skipInitialValue: Boolean = true,
  body: suspend ReceiveTurbine<Model>.() -> Unit,
) {
  moleculeFlow(RecompositionMode.Immediate) {
    testModels()
  }.test {
    if (skipInitialValue) {
      awaitItem()
    }

    body()
  }
}
