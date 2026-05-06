import com.kindl.buildlogic.setNamespace

plugins {
    alias(libs.plugins.kindl.android.library)
}

android {
    setNamespace("core.navigation")
}
