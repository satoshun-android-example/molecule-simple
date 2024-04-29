package io.github.satoshun.pino.feature.account.basic

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.slack.circuit.test.FakeNavigator
import com.slack.circuit.test.test
import io.github.satoshun.pino.feature.account.TestAccountNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccountBasicPresenterTest {
  @Test
  fun `back - navigator pop`() = runTest {
    val startScreen = AccountBasicScreen()
    val navigator = FakeNavigator(startScreen)
    val presenter = AccountBasicPresenter(
      accountNavigator = TestAccountNavigator,
      navigator = navigator,
      screen = startScreen,
    )

    presenter.test {
      val initialState = awaitItem()
      initialState.eventSink(AccountBasicEvent.Back)

      // null when startscreen is popped
      assertThat(navigator.awaitPop().poppedScreen).isNull()
    }
  }
}
