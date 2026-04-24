import com.focpet.buildlogic.androidExtension
import com.focpet.buildlogic.configureAndroidCompose
import com.focpet.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Compose 설정용 Convention Plugin
 * Compose가 필요한 모듈에 AndroidLibraryConventionPlugin과 함께 적용
 *
 * 단독으로 쓰이지 않고 Presentation/Data Plugin 내부에서 조합되어 사용
 *
 * 적용되는 설정:
 * - org.jetbrains.kotlin.plugin.compose
 * - buildFeatures.compose = true
 * - ComposeCompiler sourceInformation
 */
class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
            configureAndroidCompose(androidExtension)

            dependencies {
                val bom = libs.findLibrary("androidx.compose.bom").get()
                "implementation"(platform(bom))
                "implementation"(libs.findBundle("compose").get())
                "debugImplementation"(libs.findLibrary("androidx.ui.tooling").get())
                "debugImplementation"(libs.findLibrary("androidx.ui.test.manifest").get())
            }
        }
    }
}
