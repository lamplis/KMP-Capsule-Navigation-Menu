package com.amp_digital.capsule.navigation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Represents a Floating Action Button (FAB) action with icon and click handler.
 * 
 * @param icon The icon to display in the FAB
 * @param contentDescription Content description for accessibility
 * @param containerColor Optional custom container color for the FAB
 * @param iconColor Optional custom icon color for the FAB
 * @param onClick Callback function when the FAB is clicked
 */
data class FabAction(
    val icon: ImageVector,
    val contentDescription: String? = null,
    val containerColor: Color? = null,
    val iconColor: Color? = null,
    val onClick: () -> Unit
)
