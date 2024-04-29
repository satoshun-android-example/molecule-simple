import com.google.devtools.ksp.gradle.KspExtension
import io.github.satoshun.pino.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidCircuitConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.google.devtools.ksp")
      }
      dependencies {
        add("implementation", libs.findLibrary("circuit-codegenAnnotations").get())
        add("ksp", libs.findLibrary("circuit-codegen").get())
      }

      extensions.configure<KspExtension> {
        arg("circuit.codegen.mode", "hilt")
      }
    }
  }
}
