package io.github.satoshun.pino

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleViewModelTest {
  @Test
  fun tap() = runTest {
    val expected = SimpleModel(
      loading = true,
      name = "name"
    )

    val viewModel = SimpleViewModel()
    moleculeFlow(RecompositionMode.Immediate) {
      viewModel.testModels()
    }.test {
      // skip initial value
      awaitItem()

      viewModel.take(SimpleEvent.Tap)

      val actual = awaitItem()
      assertThat(actual).isEqualTo(expected)
    }
  }
}
