package com.kindl.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Android 모듈(Library/Application) 공통 설정 헬퍼
 * AndroidLibraryConventionPlugin, AndroidApplicationConventionPlugin에서 호출돼
 *
 * compileSdk, minSdk는 libs.versions.toml에서 읽어와서
 * 버전 변경 시 toml 한 곳만 수정하면 댐
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        // toml의 [versions]에서 읽어옴
        // 모든 Android 모듈이 동일한 compileSdk를 사용하도록 강제
        compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()

        defaultConfig {
            // 지원하는 최소 Android 버전
            minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        }

        compileOptions {
            // Java 소스 코드 호환성 버전
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    // Kotlin 컴파일 옵션은 Android/JVM 공통이라 별도 함수로 분리
    configureKotlin()
}

/**
 * 순수 Kotlin 모듈(domain) 공통 설정 헬퍼
 * KotlinJvmConventionPlugin에서 호출
 *
 * Android 의존성이 없는 domain 모듈은 compileSdk/minSdk 설정이 필요 없어서
 * configureKotlin()만 호출
 */
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    configureKotlin()
}

/**
 * Kotlin 컴파일 옵션 공통 설정
 * Android/JVM 모듈 모두 동일하게 적용되는 설정이라 private으로 분리
 *
 * freeCompilerArgs:
 * - RequiresOptIn: @OptIn 어노테이션 사용을 위한 opt-in 설정
 *   (예: ExperimentalCoroutinesApi 같은 실험적 API 사용 시 필요)
 */
private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
            freeCompilerArgs.addAll(
                listOf("-opt-in=kotlin.RequiresOptIn")
            )
        }
    }
}
