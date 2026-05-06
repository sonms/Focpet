import com.android.build.api.dsl.LibraryExtension
import com.kindl.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Presentation(feature) 모듈용 Convention Plugin
 * auth, home, main, mypage 모듈에서 사용
 *
 * library + compose + hilt를 조합한 플러그인
 * 세 플러그인을 매번 따로 적용하지 않고 이걸 하나만 적용
 *
 * 적용되는 설정:
 * - kindl.android.library (compileSdk, minSdk, JVM 11)
 * - kindl.android.compose (Compose 활성화)
 * - kindl.hilt (Hilt 의존성)
 * - domain, core 모듈 의존성 자동 추가
 */
class AndroidPresentationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kindl.android.library")
                apply("kindl.android.compose")
                apply("kindl.hilt")
                apply("kindl.serialization")
            }

            // 리소스 패키징 옵션을 설정합니다
            extensions.configure<LibraryExtension> {
                packaging {
                    resources {
                        excludes.add("META-INF/**")
                    }
                }
            }

            dependencies {
                "implementation"(project(":core:common"))
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:navigation"))

                // AndroidX
                "implementation"(libs.findBundle("androidx").get())

                // Hilt Navigation
                "implementation"(libs.findLibrary("hilt-navigation-compose").get())

                // Timber
                "implementation"(libs.findLibrary("timber").get())

                // Immutable
                "implementation"(libs.findLibrary("kotlinx.immutable").get())
            }
        }
    }
}
