package com.focpet.core.common.trigger

import androidx.compose.runtime.staticCompositionLocalOf
import com.focpet.core.common.model.GlobalUiEventHolder

val LocalGlobalUiEventTrigger = staticCompositionLocalOf<GlobalUiEventHolder> {
    error("No GlobalUiEvent Trigger provided")
}
