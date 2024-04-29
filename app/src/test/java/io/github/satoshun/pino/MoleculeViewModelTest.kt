package io.github.satoshun.pino

import app.cash.turbine.ReceiveTurbine
import app.cash.turbine.test

suspend fun <V : MoleculeViewModel<Event, Model>, Event, Model> V.test(
  body: suspend ReceiveTurbine<Model>.() -> Unit,
) {
  models.test {
    body()
  }
}
