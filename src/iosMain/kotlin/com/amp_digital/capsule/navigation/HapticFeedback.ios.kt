package com.amp_digital.capsule.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
        object : HapticFeedback {
            override fun performHapticFeedback() {
                performHapticFeedback()
            }
        }
    }
}
