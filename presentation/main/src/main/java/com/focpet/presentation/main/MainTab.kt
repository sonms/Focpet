package com.focpet.presentation.main

import androidx.annotation.DrawableRes
import com.focpet.core.navigation.MainTabRoute
import com.focpet.core.navigation.Route

internal enum class MainTab(
    @param:DrawableRes val iconRes: Int,
    val route: MainTabRoute,
    val label: String,
) {
    ;

    companion object {
        fun find(predicate: (MainTabRoute) -> Boolean): MainTab? = entries.find { predicate(it.route) }

        fun contains(predicate: (Route) -> Boolean): Boolean = entries.map { it.route }.any { predicate(it) }
    }
}
