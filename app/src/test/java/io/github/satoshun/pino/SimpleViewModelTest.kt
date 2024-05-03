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
    val viewModel = SimpleViewModel()
    viewModel.test {
      val actual = awaitItem()
      assertThat(actual.loading).isEqualTo(false)
    }
  }

  @Test
  fun tap() = runTest {
    val viewModel = SimpleViewModel()
    viewModel.test {
      val initialState = awaitItem()
      initialState.eventSink(SimpleEvent.Tap)

      val actual = awaitItem()
      assertThat(actual.loading).isEqualTo(true)
    }
  }

  @Test
  fun `tap and untap`() = runTest {
    val viewModel = SimpleViewModel()
    viewModel.test {
      var state = awaitItem()
      state.eventSink(SimpleEvent.Tap)
      state = awaitItem()
      state.eventSink(SimpleEvent.UnTap)

      val actual = awaitItem()
      assertThat(actual.loading).isEqualTo(false)
    }
  }
}
