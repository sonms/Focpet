import com.focpet.buildlogic.setNamespace

plugins {
    alias(libs.plugins.focpet.android.library)
    alias(libs.plugins.focpet.android.compose)
}

android {
    setNamespace("core.designsystem")
}
