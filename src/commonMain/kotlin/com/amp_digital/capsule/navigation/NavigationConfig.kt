package com.amp_digital.capsule.navigation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Configuration class for customizing the appearance and behavior of the navigation menu.
 * 
 * @param backgroundColor Background color of the navigation bar
 * @param selectedItemColor Color for selected navigation items
 * @param unselectedItemColor Color for unselected navigation items
 * @param height Height of the navigation bar
 * @param cornerRadius Corner radius for the capsule shape
 * @param horizontalPadding Horizontal padding around the navigation bar
 * @param verticalPadding Vertical padding around the navigation bar
 * @param itemSpacing Spacing between navigation items
 * @param animationDuration Duration for selection animations in milliseconds
 * @param enableHapticFeedback Whether to enable haptic feedback on item selection
 * @param enableGlassEffect Whether to enable glass effect (translucent background)
 * @param glassEffectAlpha Alpha value for glass effect (0.0 to 1.0)
 */
data class NavigationConfig(
    val backgroundColor: Color = Color.Unspecified,
    val selectedItemColor: Color = Color.Unspecified,
    val unselectedItemColor: Color = Color.Unspecified,
    val height: Dp = 72.dp,
    val cornerRadius: Dp = 36.dp, // Half of height for perfect capsule
    val horizontalPadding: Dp = 32.dp,
    val verticalPadding: Dp = 12.dp,
    val itemSpacing: Dp = 8.dp,
    val animationDuration: Int = 300,
    val enableHapticFeedback: Boolean = true,
    val enableGlassEffect: Boolean = true,
    val glassEffectAlpha: Float = 0.92f
)
