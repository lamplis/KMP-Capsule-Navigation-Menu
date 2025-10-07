package com.amp_digital.capsule.navigation

import androidx.compose.runtime.Composable

/**
 * Platform-specific haptic feedback implementation.
 * This will be implemented differently for each platform.
 */
expect fun performHapticFeedback()

/**
 * Composable haptic feedback function that works within Composable context.
 * This provides better haptic feedback on Android by accessing the context.
 */
@Composable
expect fun performHapticFeedbackComposable()

/**
 * Initialize haptic feedback system (Android only).
 * This should be called from the app's main activity or application class.
 */
expect fun initHapticFeedback()

/**
 * Initialize haptic feedback from Composable context (Android only).
 * This is called automatically when the navigation component is first composed.
 */
@Composable
expect fun initHapticFeedbackFromComposable()

/**
 * Remember haptic feedback instance for use in Composables.
 * This provides a non-Composable interface for haptic feedback.
 */
@Composable
expect fun rememberHapticFeedback(): HapticFeedback

/**
 * Interface for haptic feedback that can be called from non-Composable contexts.
 */
interface HapticFeedback {
    fun performHapticFeedback()
}
