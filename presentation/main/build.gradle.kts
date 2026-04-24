import com.focpet.buildlogic.setNamespace

plugins {
    alias(libs.plugins.focpet.android.presentation)
}

android {
    setNamespace("presentation.main")

    buildFeatures {
        buildConfig = true
    }
}
