plugins {
  alias(libs.plugins.pino.android.library)
  alias(libs.plugins.pino.android.library.compose)
  alias(libs.plugins.pino.android.dagger)

  alias(libs.plugins.dagger.hilt)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.paparazzi)
}

android {
  namespace = "io.github.satoshun.pino.share"
}

dependencies {
  implementation(libs.bundles.android.ui)

  implementation(libs.ktor.android)
  implementation(libs.ktor.okhttp)
  implementation(libs.ktor.serialization)
  implementation(libs.ktor.negotiation)
  implementation(libs.ktor.json)

  implementation(libs.showkase.runtime)
  ksp(libs.showkase.processor)

  testImplementation(libs.android.test.runner)
  testImplementation(libs.testParameterInjector)
}
