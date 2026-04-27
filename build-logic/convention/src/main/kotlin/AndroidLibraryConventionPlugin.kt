import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.focpet.buildlogic.configureKotlinAndroid
import com.focpet.buildlogic.disableUnnecessaryAndroidTests
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * 일반 Android Library 모듈용 Convention Plugin
 * core:common, core:designsystem, core:navigation, core:type 에서 사용
 *
 * 적용되는 설정:
 * - com.android.library
 * - org.jetbrains.kotlin.android
 * - compileSdk, minSdk, JVM 11 (configureKotlinAndroid 헬퍼)
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(target)
            }
        }
    }
}
