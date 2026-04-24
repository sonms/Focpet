package com.focpet

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class FocpetApplication: Application(), ImageLoaderFactory {
    @Inject
    lateinit var imageLoaderProvider: dagger.Lazy<ImageLoader>

    override fun onCreate() {
        super.onCreate()

        setDayMode()
        initTimber()
    }

    private fun setDayMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun newImageLoader(): ImageLoader {
        return imageLoaderProvider.get()
    }
}
