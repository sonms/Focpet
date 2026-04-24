import com.focpet.buildlogic.setNamespace

plugins {
    alias(libs.plugins.focpet.android.library)
}

android {
    setNamespace("core.common")
}
