package io.github.satoshun.pino.feature.account

import com.slack.circuit.runtime.Navigator

interface AccountNavigator {
  fun gotoHelp(navigator: Navigator)
}
