package com.kindl.core.common.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
data class DialogState(
    val isVisible: Boolean = false,
    val onClickAction: () -> Unit = {}
)

@Stable
class DialogTrigger(
    val show: (() -> Unit) -> Unit,
    val dismiss: () -> Unit
)

