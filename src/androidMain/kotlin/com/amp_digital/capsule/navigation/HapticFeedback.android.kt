package com.amp_digital.capsule.navigation

import android.view.HapticFeedbackConstants
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView

/**
 * Android implementation of haptic feedback.
 * Uses View-based haptic feedback which doesn't require VIBRATE permission.
 */
actual fun performHapticFeedback() {
    // This is a fallback that doesn't work without a view context
    // The actual haptic feedback should be called from a Composable context
    // using the Composable version below
}

/**
 * Android implementation of Composable haptic feedback.
 * Uses View-based haptic feedback which doesn't require VIBRATE permission.
 */
@Composable
actual fun performHapticFeedbackComposable() {
    val view = LocalView.current
    view.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
}

/**
 * Initialize haptic feedback system (Android implementation).
 * This is a no-op for the expect/actual pattern.
 */
actual fun initHapticFeedback() {
    // No initialization needed for View-based haptic feedback
}

/**
 * Initialize haptic feedback from Composable context (Android implementation).
 * This is a no-op for View-based haptic feedback.
 */
@Composable
actual fun initHapticFeedbackFromComposable() {
    // No initialization needed for View-based haptic feedback
}

/**
 * Android implementation of remember haptic feedback.
 * Creates a HapticFeedback instance that can be called from non-Composable contexts.
 */
@Composable
actual fun rememberHapticFeedback(): HapticFeedback {
    val view = LocalView.current
    return remember {
        object : HapticFeedback {
            override fun performHapticFeedback() {
                view.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
            }
        }
    }
}
