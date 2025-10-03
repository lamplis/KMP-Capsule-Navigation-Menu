package com.amp_digital.capsule.navigation

import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UIImpactFeedbackStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * iOS implementation of haptic feedback.
 */
actual fun performHapticFeedback() {
    val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleLight)
    generator.impactOccurred()
}

/**
 * iOS implementation of Composable haptic feedback.
 * This provides the same haptic feedback as the non-Composable version.
 */
@Composable
actual fun performHapticFeedbackComposable() {
    val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleLight)
    generator.impactOccurred()
}

/**
 * Initialize haptic feedback system (iOS implementation).
 * This is a no-op on iOS as no initialization is needed.
 */
actual fun initHapticFeedback() {
    // No initialization needed on iOS
}

/**
 * iOS implementation of Composable haptic feedback initialization.
 * This is a no-op on iOS as no initialization is needed.
 */
@Composable
actual fun initHapticFeedbackFromComposable() {
    // No initialization needed on iOS
}

/**
 * iOS implementation of remember haptic feedback.
 * Creates a HapticFeedback instance that can be called from non-Composable contexts.
 */
@Composable
actual fun rememberHapticFeedback(): HapticFeedback {
    return remember {
        object : HapticFeedback {
            override fun performHapticFeedback() {
                val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleLight)
                generator.impactOccurred()
            }
        }
    }
}
