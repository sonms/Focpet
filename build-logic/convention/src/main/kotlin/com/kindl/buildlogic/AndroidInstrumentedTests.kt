package com.kindl.buildlogic

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project

/**
 * `androidTest` 소스셋이 없는 라이브러리 모듈의 경우,
 *  불필요한 Android Instrumented Test 작업을 비활성화하여 빌드 시간을 최적화합니다.
 *  기기에서 직접 실행해보는 테스트(androidTest)를 위한 준비 작업을 백그라운드에서 진행하는데 그걸 막기 위함
 */
fun LibraryAndroidComponentsExtension.disableUnnecessaryAndroidTests(
    project: Project,
) = beforeVariants { variant ->
    variant.androidTest.enable = variant.androidTest.enable
            && project.projectDir.resolve("src/androidTest").exists()
}
