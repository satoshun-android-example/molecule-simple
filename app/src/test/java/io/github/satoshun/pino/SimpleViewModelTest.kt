package io.github.satoshun.pino

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleViewModelTest {
  @Test
  fun initialValue() = runTest {
    val expected = SimpleModel(
      loading = false,
      name = "name"
    )

    val viewModel = SimpleViewModel()
    viewModel.test(skipInitialValue = false) {
      val actual = awaitItem()
      assertThat(actual).isEqualTo(expected)
    }
  }

  @Test
  fun tap() = runTest {
    val expected = SimpleModel(
      loading = true,
      name = "name"
    )

    val viewModel = SimpleViewModel()
    viewModel.test {
      viewModel.take(SimpleEvent.Tap)

      val actual = awaitItem()
      assertThat(actual).isEqualTo(expected)
    }
  }
}
