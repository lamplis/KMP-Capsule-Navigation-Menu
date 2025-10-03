package com.amp_digital.capsule.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing a navigation item in the bottom navigation bar.
 * 
 * @param title The display title for the navigation item
 * @param icon The icon to display for the navigation item
 * @param route The route identifier for navigation
 * @param badge Optional badge content to display on the item
 */
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badge: String? = null,
    val fabAction: FabAction? = null
)
