import com.kindl.buildlogic.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

/**
 * 순수 Kotlin 모듈(domain)용 Convention Plugin
 *
 * domain 모듈은 Android 의존성이 없어야 해서
 * com.android.library 대신 org.jetbrains.kotlin.jvm 사용
 *
 * 장점:
 * - 빌드 속도가 Android 모듈보다 빠름
 * - Android 의존성 실수로 추가하면 컴파일 에러로 바로 감지 가능
 *
 * 적용되는 설정:
 * - org.jetbrains.kotlin.jvm
 * - JVM 11
 */
class KotlinJvmConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.jvm")
            configureKotlinJvm()
        }
    }
}
