package com.amp_digital.capsule.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * Common haptic feedback composable that works across all platforms.
 */
@Composable
fun rememberHapticFeedback(): HapticFeedback {
    return remember {
        HapticFeedback { performHapticFeedback() }
    }
}

/**
 * Haptic feedback interface for platform-specific implementations.
 */
interface HapticFeedback {
    fun performHapticFeedback()
}
