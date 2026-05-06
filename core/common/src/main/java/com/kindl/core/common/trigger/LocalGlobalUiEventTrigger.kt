package com.kindl.core.common.trigger

import androidx.compose.runtime.staticCompositionLocalOf
import com.kindl.core.common.model.GlobalUiEventHolder

val LocalGlobalUiEventTrigger = staticCompositionLocalOf<GlobalUiEventHolder> {
    error("No GlobalUiEvent Trigger provided")
}
