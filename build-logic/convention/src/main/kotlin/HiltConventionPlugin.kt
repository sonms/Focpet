import com.kindl.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Hilt 의존성 주입 설정용 Convention Plugin
 * Hilt가 필요한 모듈에 추가로 적용
 *
 * data, presentation 모듈에서 사용
 * domain은 Hilt 불필요 (순수 Kotlin 인터페이스/UseCase만 존재)
 *
 * 적용되는 설정:
 * - com.google.dagger.hilt.android 플러그인
 * - com.google.devtools.ksp 플러그인 (어노테이션 처리)
 * - hilt-android implementation
 * - hilt-compiler ksp
 */
class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("com.google.devtools.ksp")
            }
            dependencies {
                "implementation"(libs.findLibrary("hilt").get())
                "ksp"(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}
