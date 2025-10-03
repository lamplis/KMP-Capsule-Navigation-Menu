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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
    modifier: Modifier = Modifier
) {
    val bottomInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal)
    
    val backgroundColor = if (config.backgroundColor != Color.Unspecified) {
        config.backgroundColor
    } else {
        MaterialTheme.colorScheme.surface
    }
    
    val selectedColor = if (config.selectedItemColor != Color.Unspecified) {
        config.selectedItemColor
    } else {
        MaterialTheme.colorScheme.primary
    }
    
    val unselectedColor = if (config.unselectedItemColor != Color.Unspecified) {
        config.unselectedItemColor
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottomInsets.asPaddingValues()) // Apply safe area padding
            .padding(
                horizontal = config.horizontalPadding,
                vertical = config.verticalPadding
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(config.height)
                .clip(ContinuousCapsule)
                .background(
                    color = if (config.enableGlassEffect) {
                        backgroundColor.copy(alpha = config.glassEffectAlpha)
                    } else {
                        backgroundColor
                    }
                )
                .padding(horizontal = config.itemSpacing, vertical = config.itemSpacing),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                FloatingNavigationItem(
                    item = item,
                    isSelected = selectedIndex == index,
                    onClick = { onItemSelected(index) },
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor,
                    animationDuration = config.animationDuration,
                    enableHapticFeedback = config.enableHapticFeedback
                )
            }
        }
    }
}

@Composable
private fun FloatingNavigationItem(
    item: NavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    selectedColor: Color,
    unselectedColor: Color,
    animationDuration: Int,
    enableHapticFeedback: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    // Pulsing animation for the icon container
    val containerAlpha = remember { Animatable(if (isSelected) 0.2f else 0f) }
    
    LaunchedEffect(isPressed) {
        if (isPressed) {
            if (enableHapticFeedback) {
                // Perform haptic feedback - platform specific implementation
                performHapticFeedback()
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

    val textColor = if (isSelected) selectedColor else unselectedColor
    val iconColor = if (isSelected) selectedColor else unselectedColor

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(72.dp) // Fixed width for uniform buttons
            .clickable(
                interactionSource = interactionSource,
                indication = null // Remove default ripple
            ) { onClick() }
            .background(
                color = selectedColor.copy(alpha = containerAlpha.value),
                shape = ContinuousCapsule
            )
            .padding(horizontal = 8.dp, vertical = 8.dp)
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
                            color = selectedColor,
                            shape = ContinuousCapsule
                        )
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = badge,
                        color = MaterialTheme.colorScheme.onPrimary,
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
 * Preview for Cardium floating bottom navigation
 */
@Composable
@Preview
fun FloatingBottomNavigationPreview() {
    MaterialTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                var selectedIndex by remember { mutableStateOf(0) }
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
                        .fillMaxWidth()
                )
            }
        }
    }
}