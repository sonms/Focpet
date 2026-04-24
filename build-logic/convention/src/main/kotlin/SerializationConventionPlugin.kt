import com.focpet.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Kotlin Serialization 설정용 Convention Plugin
 * 네트워크 통신 시 JSON 직렬화/역직렬화에 사용
 *
 * data 모듈에서 Retrofit + kotlinx.serialization 조합으로 사용
 *
 * 적용되는 설정:
 * - org.jetbrains.kotlin.plugin.serialization 플러그인
 * (실제 kotlinx-serialization-json 라이브러리는
 *  AndroidDataConventionPlugin에서 의존성으로 추가)
 */
class SerializationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
            }
        }
    }
}
