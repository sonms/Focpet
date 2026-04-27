import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.focpet.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
}

gradlePlugin {
    plugins {
        // app 모듈용
        register("androidApplication") {
            id = "focpet.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        // 일반 library용
        register("androidLibrary") {
            id = "focpet.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        // BuildConfig library용
        register("androidBuildConfig") {
            id = "focpet.android.buildconfig"
            implementationClass = "AndroidBuildConfigConventionPlugin"
        }
        // Compose 설정용
        register("androidCompose") {
            id = "focpet.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        // presentation(feature) 모듈용
        register("androidPresentation") {
            id = "focpet.android.presentation"
            implementationClass = "AndroidPresentationConventionPlugin"
        }
        // data 모듈용
        register("androidData") {
            id = "focpet.android.data"
            implementationClass = "AndroidDataConventionPlugin"
        }
        // domain 모듈용
        register("kotlinJvm") {
            id = "focpet.kotlin.jvm"
            implementationClass = "KotlinJvmConventionPlugin"
        }
        // Hilt 설정용
        register("hilt") {
            id = "focpet.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        // Serialization용
        register("serialization") {
            id = "focpet.serialization"
            implementationClass = "SerializationConventionPlugin"
        }
        // Test 설정용
        register("androidTest") {
            id = "focpet.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
    }
}
