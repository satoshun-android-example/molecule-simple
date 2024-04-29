package io.github.satoshun.pino

import com.slack.circuit.overlay.OverlayHost
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuitx.overlays.showFullScreenOverlay
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds
import io.github.satoshun.pino.feature.account.AccountNavigator
import io.github.satoshun.pino.feature.account.AccountScreen
import io.github.satoshun.pino.feature.detail.DetailScreen
import io.github.satoshun.pino.feature.help.HelpScreen
import io.github.satoshun.pino.feature.home.HomeNavigator
import io.github.satoshun.pino.share.data.Image
import io.github.satoshun.pino.share.di.DaggerSet
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor() : HomeNavigator {
  override suspend fun goToDetail(
    host: OverlayHost,
    image: Image,
  ) {
    host.showFullScreenOverlay(DetailScreen(image))
  }

  override fun goToAccount(navigator: Navigator) {
    navigator.goTo(AccountScreen())
  }
}

class AccountNavigatorImpl @Inject constructor() : AccountNavigator {
  override fun gotoHelp(navigator: Navigator) {
    navigator.goTo(HelpScreen())
  }
}

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorsModule {
  @Binds fun bindHomeNavigator(impl: HomeNavigatorImpl): HomeNavigator
  @Binds fun bindAccountNavigator(impl: AccountNavigatorImpl): AccountNavigator

  @Multibinds fun presenterFactories(): DaggerSet<Presenter.Factory>
  @Multibinds fun uiFactories(): DaggerSet<Ui.Factory>
}
