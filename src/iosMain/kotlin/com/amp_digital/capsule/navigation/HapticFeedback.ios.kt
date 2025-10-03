package com.amp_digital.capsule.navigation

import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle

/**
 * iOS implementation of haptic feedback.
 */
actual fun performHapticFeedback() {
    val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleLight)
    generator.impactOccurred()
}

/**
 * iOS-specific haptic feedback composable.
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
