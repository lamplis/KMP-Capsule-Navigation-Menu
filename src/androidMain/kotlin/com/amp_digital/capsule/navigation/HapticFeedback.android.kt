package com.amp_digital.capsule.navigation

import androidx.compose.runtime.Composable
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
        HapticFeedback { view.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY) }
    }
}

/**
 * Haptic feedback interface for platform-specific implementations.
 */
interface HapticFeedback {
    fun performHapticFeedback()
}
