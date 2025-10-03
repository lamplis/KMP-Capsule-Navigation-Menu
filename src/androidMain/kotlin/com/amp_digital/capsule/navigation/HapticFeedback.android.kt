package com.amp_digital.capsule.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView

/**
 * Android implementation of haptic feedback.
 */
actual fun performHapticFeedback() {
    // This will be called from the Composable context
    // The actual implementation will be handled in the Composable
}

/**
 * Android-specific haptic feedback composable.
 */
@Composable
fun rememberHapticFeedback(): HapticFeedback {
    val view = LocalView.current
    return remember {
        object : HapticFeedback {
            override fun performHapticFeedback() {
                view.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY)
            }
        }
    }
}
