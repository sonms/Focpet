import com.focpet.buildlogic.setNamespace

plugins {
    alias(libs.plugins.focpet.android.library)
    alias(libs.plugins.focpet.hilt)
    alias(libs.plugins.focpet.serialization)
    alias(libs.plugins.focpet.android.buildconfig)
}

android {
    setNamespace("core.network")
}

dependencies {
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.timber)
}
