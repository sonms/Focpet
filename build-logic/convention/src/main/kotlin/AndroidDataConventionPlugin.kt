import com.android.build.api.dsl.LibraryExtension
import com.kindl.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Data 모듈용 Convention Plugin
 *
 * library + hilt + serialization을 조합한 플러그인
 * network(Retrofit), local(DataStore), repository 구현체가 여기에 위치
 *
 * 적용되는 설정:
 * - convention.android.library (compileSdk, minSdk, JVM 11)
 * - convention.hilt (Hilt 의존성)
 * - convention.serialization (Serialization 플러그인)
 * - domain 모듈 의존성 자동 추가
 * - Retrofit, OkHttp, DataStore, kotlinx-serialization 의존성 자동 추가
 */
class AndroidDataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kindl.android.library")
                apply("kindl.hilt")
                apply("kindl.serialization")
            }
            extensions.configure<LibraryExtension> {
                packaging {
                    resources {
                        excludes.add("META-INF/**")
                    }
                }
            }
            dependencies {
                "implementation"(project(":core:network"))

                // Local
                "implementation"(libs.findLibrary("androidx.datastore.preferences").get())

                "implementation"(libs.findLibrary("javax.inject").get())

                "implementation"(libs.findBundle("retrofit").get())
            }
        }
    }
}
