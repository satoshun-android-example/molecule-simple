plugins {
  alias(libs.plugins.pino.android.application)
  alias(libs.plugins.pino.android.application.compose)
  alias(libs.plugins.pino.android.dagger)
  alias(libs.plugins.android.app)
  alias(libs.plugins.baselineprofile)
}

android {
  namespace = "io.github.satoshun.pino"

  defaultConfig {
    applicationId = "io.github.satoshun.pino"
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }

  packaging {
    resources.excludes += listOf(
      "META-INF/AL2.0",
      "META-INF/LGPL2.1"
    )
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}

dependencies {
  implementation(projects.share)
  implementation(projects.designsystem)

  implementation(projects.feature.home)
  implementation(projects.feature.detail)
  implementation(projects.feature.account)
  implementation(projects.feature.help)

  implementation(libs.bundles.android.ui)
  implementation(libs.bundles.kotlin.android)

  implementation(libs.kotlinx.serialization)
  implementation(libs.profileinstaller)
  "baselineProfile"(project(":baselineprofile"))

  debugImplementation(libs.bundles.android.debug.test)
  debugImplementation(libs.leakcanary)

  testImplementation(libs.bundles.test)

  androidTestImplementation(libs.bundles.android.test)
}
