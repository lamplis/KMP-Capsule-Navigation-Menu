package com.amp_digital.capsule.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Strongly-typed navigation entry that pairs a screen with its UI metadata.
 * Library-friendly and independent of app-specific routing.
 */
data class NavEntry(
    val key: String,
    val title: String,
    val icon: ImageVector,
    val screen: @Composable (Modifier) -> Unit,
    val fabAction: FabAction? = null
)

fun NavEntry.toNavigationItem(): NavigationItem =
    NavigationItem(title = title, icon = icon, route = key, fabAction = fabAction)

fun List<NavEntry>.toNavigationItems(): List<NavigationItem> = map { it.toNavigationItem() }


