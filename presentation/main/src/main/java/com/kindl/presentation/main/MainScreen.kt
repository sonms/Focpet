package com.kindl.presentation.main

import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.kindl.core.common.model.DialogTrigger
import com.kindl.core.common.model.GlobalUiEventHolder
import com.kindl.core.common.model.SnackbarState
import com.kindl.core.common.trigger.LocalGlobalUiEventTrigger
import com.kindl.presentation.main.state.MainAppState
import com.kindl.presentation.main.state.rememberDialogStateHolder
import com.kindl.presentation.main.state.rememberMainAppState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun MainScreen(
    appState: MainAppState = rememberMainAppState(),
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val dialogState = rememberDialogStateHolder()

    val snackBarHostState = remember { SnackbarHostState() }
    var currentSnackbarState by remember { mutableStateOf<SnackbarState?>(null) }

    val onShowToast: (String) -> Unit = remember {
        { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    val onShowSnackbar: (SnackbarState) -> Unit = remember(scope, snackBarHostState) {
        { state ->
            currentSnackbarState = state
            scope.launch {
                snackBarHostState.currentSnackbarData?.dismiss()

                val job = launch {
                    snackBarHostState.showSnackbar(
                        message = state.message,
                    )
                }
                job.invokeOnCompletion {
                    if (currentSnackbarState == state) {
                        currentSnackbarState = null
                    }
                }
                delay(2000L)
                job.cancel()
            }
        }
    }

    val eventHolder = remember(dialogState, onShowToast, onShowSnackbar) {
        GlobalUiEventHolder(
            dialogTrigger = DialogTrigger(
                show = { onConfirm ->
                    dialogState.showDialog(onConfirm)
                },
                dismiss = {
                    dialogState.dismissDialog()
                }
            ),
            showToast = onShowToast,
            showSnackbar = onShowSnackbar,
        )
    }

    CompositionLocalProvider(
        LocalGlobalUiEventTrigger provides eventHolder,
    ) {

    }
}
