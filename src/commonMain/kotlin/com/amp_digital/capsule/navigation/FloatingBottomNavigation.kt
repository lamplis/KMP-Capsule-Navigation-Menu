package com.amp_digital.capsule.navigation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amp_digital.capsule.ContinuousCapsule
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Floating bottom navigation bar with glass effect and safe area support.
 * Uses platform-specific capsule shapes for perfect appearance.
 * Respects iOS home indicator and Android navigation bar.
 * 
 * @param selectedIndex Currently selected navigation item index
 * @param onItemSelected Callback when a navigation item is selected
 * @param items List of navigation items to display
 * @param config Configuration for customizing appearance and behavior
 * @param modifier Modifier for the navigation bar
 */
@Composable
fun FloatingBottomNavigation(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    items: List<NavigationItem>,
    config: NavigationConfig = NavigationConfig(),
    modifier: Modifier = Modifier,
    fab: FabAction? = null
) {
    val bottomInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal)
    
    // Create haptic feedback instance for this composable
    val hapticFeedback = rememberHapticFeedback()
    
    val colors = resolveNavigationColors(config)

    Box(
        modifier = modifier
            .padding(bottomInsets.asPaddingValues())
            .padding(
                horizontal = config.horizontalPadding,
                vertical = config.verticalPadding
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val resolvedFab = fab ?: items.getOrNull(selectedIndex)?.fabAction

            MenuContainer(
                items = items,
                selectedIndex = selectedIndex,
                onItemSelected = onItemSelected,
                colors = colors,
                config = config,
                hapticFeedback = hapticFeedback
            )
            
            resolvedFab?.let { action ->
                FloatingActionButton(
                    action = action,
                    config = config,
                    hapticFeedback = hapticFeedback
                )
            }
        }
    }
}

@Composable
private fun MenuContainer(
    items: List<NavigationItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    colors: NavigationColors,
    config: NavigationConfig,
    hapticFeedback: HapticFeedback
) {
    Row(
        modifier = Modifier
            .height(config.height)
            .clip(ContinuousCapsule)
            .background(
                color = if (config.enableGlassEffect) {
                    colors.backgroundColor.copy(alpha = config.glassEffectAlpha)
                } else {
                    colors.backgroundColor
                }
            )
            .padding(horizontal = config.itemSpacing, vertical = config.itemSpacing),
        horizontalArrangement = Arrangement.spacedBy(config.itemSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            FloatingNavigationItem(
                item = item,
                isSelected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                colors = colors,
                animationDuration = config.animationDuration,
                enableHapticFeedback = config.enableHapticFeedback,
                hapticFeedback = hapticFeedback
            )
        }
    }
}

@Composable
private fun FloatingNavigationItem(
    item: NavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    colors: NavigationColors,
    animationDuration: Int,
    enableHapticFeedback: Boolean,
    hapticFeedback: HapticFeedback
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    // Pulsing animation for the icon container
    val containerAlpha = remember { Animatable(if (isSelected) 0.2f else 0f) }
    
    LaunchedEffect(isPressed) {
        if (isPressed) {
            if (enableHapticFeedback) {
                // Perform haptic feedback - platform specific implementation
                hapticFeedback.performHapticFeedback()
            }
            // Pulse effect: reduce transparency (increase alpha) and go back
            containerAlpha.animateTo(
                targetValue = 0.4f,
                animationSpec = tween(durationMillis = 100)
            )
            containerAlpha.animateTo(
                targetValue = if (isSelected) 0.2f else 0f,
                animationSpec = tween(durationMillis = 200)
            )
        }
    }
    
    LaunchedEffect(isSelected) {
        // Smoothly transition to selected/unselected state
        containerAlpha.animateTo(
            targetValue = if (isSelected) 0.2f else 0f,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }

    val textColor = if (isSelected) colors.selectedColor else colors.unselectedColor
    val iconColor = if (isSelected) colors.selectedColor else colors.unselectedColor

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(72.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null // Remove default ripple
            ) { onClick() }
            .background(
                color = colors.selectedColor.copy(alpha = containerAlpha.value),
                shape = ContinuousCapsule
            )
            .padding(horizontal = 0.dp, vertical = 8.dp)
    ) {
        Box {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                modifier = Modifier.size(24.dp),
                tint = iconColor
            )
            
            // Badge indicator
            item.badge?.let { badge ->
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.error,
                            shape = ContinuousCapsule
                        )
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = badge,
                        color = MaterialTheme.colorScheme.errorContainer,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        
        Text(
            text = item.title,
            fontSize = 9.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = textColor,
            maxLines = 1
        )
    }
}

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

/**
 * Resolved navigation colors for consistent theming.
 */
private data class NavigationColors(
    val backgroundColor: Color,
    val selectedColor: Color,
    val unselectedColor: Color
)

/**
 * Resolves navigation colors from config or falls back to Material theme.
 */
@Composable
private fun resolveNavigationColors(config: NavigationConfig): NavigationColors {
    return NavigationColors(
        backgroundColor = config.backgroundColor.takeIf { it != Color.Unspecified }
            ?: MaterialTheme.colorScheme.surface,
        selectedColor = config.selectedItemColor.takeIf { it != Color.Unspecified }
            ?: MaterialTheme.colorScheme.primary,
        unselectedColor = config.unselectedItemColor.takeIf { it != Color.Unspecified }
            ?: MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
    )
}

/**
 * Floating Action Button component for navigation.
 */
@Composable
private fun FloatingActionButton(
    action: FabAction,
    config: NavigationConfig,
    hapticFeedback: HapticFeedback
) {
    val containerColor = action.containerColor ?: 
        config.fabContainerColor.takeIf { it != Color.Unspecified } ?: 
        MaterialTheme.colorScheme.primary
    
    val iconColor = action.iconColor ?: 
        config.fabIconColor.takeIf { it != Color.Unspecified } ?: 
        MaterialTheme.colorScheme.onPrimary

    Box(
        modifier = Modifier
            .size(config.fabSize)
            .clip(CircleShape)
            .background(containerColor)
            .clickable {
                if (config.enableHapticFeedback) {
                    hapticFeedback.performHapticFeedback()
                }
                action.onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = action.icon,
            contentDescription = action.contentDescription,
            tint = iconColor
        )
    }
}


/**
 * Preview for floating bottom navigation
 */
@Composable
@Preview
fun FloatingBottomNavigationPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                var selectedIndex by remember { mutableStateOf(1) }
                val items = listOf(
                    NavigationItem("Home", Icons.Filled.Home, "home"),
                    NavigationItem("Collection", Icons.Filled.AccountCircle, "collection", badge = "3"),
                    NavigationItem("Trade", Icons.Filled.SwapHoriz, "trade"),
                    NavigationItem("Nearby", Icons.Filled.LocationOn, "nearby")
                )

                FloatingBottomNavigation(
                    selectedIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    items = items,
                    config = NavigationConfig(),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),
                    fab = FabAction(
                        icon = Icons.Filled.Add,
                        contentDescription = "Add",
                        onClick = {}
                    )
                )
            }
        }
    }
}