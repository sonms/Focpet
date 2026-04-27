import com.focpet.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Android Test 설정용 Convention Plugin
 * 테스트가 필요한 모듈에 추가로 적용
 *
 * 적용되는 설정:
 * - JUnit, Espresso, Compose UI Test 의존성 자동 추가
 */
class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                "testImplementation"(libs.findLibrary("junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx.junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx.espresso.core").get())
                "androidTestImplementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
                "androidTestImplementation"(libs.findLibrary("androidx.ui.test.junit4").get())
                "debugImplementation"(libs.findLibrary("androidx.ui.tooling").get())
                "debugImplementation"(libs.findLibrary("androidx.ui.test.manifest").get())
            }
        }
    }
}
