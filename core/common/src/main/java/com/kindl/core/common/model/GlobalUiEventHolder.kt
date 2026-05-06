package com.kindl.core.common.model

import androidx.compose.runtime.Stable

@Stable
class GlobalUiEventHolder(
    val dialogTrigger: DialogTrigger,
    val showToast: (String) -> Unit,
    val showSnackbar: (SnackbarState) -> Unit,
)
