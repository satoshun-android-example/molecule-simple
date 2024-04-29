plugins {
  `kotlin-dsl`
}

group = "io.github.satoshun.pino.buildlogic"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("androidApplication") {
      id = "pino.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }
    register("androidApplicationCompose") {
      id = "pino.android.application.compose"
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }

    register("androidLibrary") {
      id = "pino.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("androidLibraryCompose") {
      id = "pino.android.library.compose"
      implementationClass = "AndroidLibraryComposeConventionPlugin"
    }

    register("androidDagger") {
      id = "pino.android.dagger"
      implementationClass = "AndroidDaggerConventionPlugin"
    }

    register("androidCircuit") {
      id = "pino.android.circuit"
      implementationClass = "AndroidCircuitConventionPlugin"
    }
  }
}
