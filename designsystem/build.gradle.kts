plugins {
  alias(libs.plugins.pino.android.library)
  alias(libs.plugins.pino.android.library.compose)
  alias(libs.plugins.pino.android.dagger)

  alias(libs.plugins.kotlin.serialization)
}

dependencies {
  implementation(libs.bundles.android.ui)
  implementation(libs.bundles.kotlin.android)

  implementation(libs.kotlinx.serialization)

  implementation(libs.ktor.android)
  implementation(libs.ktor.okhttp)
  implementation(libs.ktor.serialization)
  implementation(libs.ktor.negotiation)
  implementation(libs.ktor.json)

  testImplementation(libs.bundles.test)
}

android {
  namespace = "io.github.satoshun.pino.designsystem"
}
