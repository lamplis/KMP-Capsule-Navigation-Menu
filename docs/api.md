# API Reference

This document provides detailed API reference for KMP-Capsule-Navigation-Menu.

## Table of Contents

- [FloatingBottomNavigation](#floatingbottomnavigation)
- [NavigationItem](#navigationitem)
- [NavigationConfig](#navigationconfig)
- [HapticFeedback](#hapticfeedback)
- [Platform-Specific APIs](#platform-specific-apis)

## FloatingBottomNavigation

The main composable for creating a floating bottom navigation bar.

### Signature

```kotlin
@Composable
fun FloatingBottomNavigation(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    items: List<NavigationItem>,
    config: NavigationConfig = NavigationConfig(),
    modifier: Modifier = Modifier
)
```

### Parameters

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `selectedIndex` | `Int` | - | Currently selected navigation item index (0-based) |
| `onItemSelected` | `(Int) -> Unit` | - | Callback invoked when a navigation item is selected |
| `items` | `List<NavigationItem>` | - | List of navigation items to display |
| `config` | `NavigationConfig` | `NavigationConfig()` | Configuration for customizing appearance and behavior |
| `modifier` | `Modifier` | `Modifier` | Modifier to be applied to the navigation bar |

### Usage Example

```kotlin
@Composable
fun MyNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        modifier = Modifier.align(Alignment.BottomCenter)
    )
}
```

## NavigationItem

Data class representing a single navigation item.

### Signature

```kotlin
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badge: String? = null
)
```

### Properties

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `title` | `String` | - | Display title for the navigation item |
| `icon` | `ImageVector` | - | Icon to display for the navigation item |
| `route` | `String` | - | Route identifier for navigation |
| `badge` | `String?` | `null` | Optional badge content to display on the item |

### Usage Example

```kotlin
val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = "home"
    ),
    NavigationItem(
        title = "Messages",
        icon = Icons.Default.Message,
        route = "messages",
        badge = "3"
    )
)
```

## NavigationConfig

Configuration class for customizing the navigation appearance and behavior.

### Signature

```kotlin
data class NavigationConfig(
    val backgroundColor: Color = Color.Unspecified,
    val selectedItemColor: Color = Color.Unspecified,
    val unselectedItemColor: Color = Color.Unspecified,
    val height: Dp = 72.dp,
    val cornerRadius: Dp = 36.dp,
    val horizontalPadding: Dp = 32.dp,
    val verticalPadding: Dp = 12.dp,
    val itemSpacing: Dp = 8.dp,
    val animationDuration: Int = 300,
    val enableHapticFeedback: Boolean = true,
    val enableGlassEffect: Boolean = true,
    val glassEffectAlpha: Float = 0.92f
)
```

### Properties

| Property | Type | Default | Description |
|----------|------|---------|-------------|
| `backgroundColor` | `Color` | `Color.Unspecified` | Background color of the navigation bar |
| `selectedItemColor` | `Color` | `Color.Unspecified` | Color for selected navigation items |
| `unselectedItemColor` | `Color` | `Color.Unspecified` | Color for unselected navigation items |
| `height` | `Dp` | `72.dp` | Height of the navigation bar |
| `cornerRadius` | `Dp` | `36.dp` | Corner radius for the capsule shape |
| `horizontalPadding` | `Dp` | `32.dp` | Horizontal padding around the navigation bar |
| `verticalPadding` | `Dp` | `12.dp` | Vertical padding around the navigation bar |
| `itemSpacing` | `Dp` | `8.dp` | Spacing between navigation items |
| `animationDuration` | `Int` | `300` | Duration for selection animations in milliseconds |
| `enableHapticFeedback` | `Boolean` | `true` | Whether to enable haptic feedback on item selection |
| `enableGlassEffect` | `Boolean` | `true` | Whether to enable glass effect (translucent background) |
| `glassEffectAlpha` | `Float` | `0.92f` | Alpha value for glass effect (0.0 to 1.0) |

### Usage Example

```kotlin
val config = NavigationConfig(
    backgroundColor = Color(0xFF1E1E1E),
    selectedItemColor = Color(0xFF00BCD4),
    unselectedItemColor = Color(0xFF757575),
    height = 80.dp,
    cornerRadius = 40.dp,
    animationDuration = 500,
    enableGlassEffect = true,
    glassEffectAlpha = 0.85f
)
```

## HapticFeedback

Interface for platform-specific haptic feedback implementations.

### Signature

```kotlin
interface HapticFeedback {
    fun performHapticFeedback()
}
```

### Methods

| Method | Description |
|--------|-------------|
| `performHapticFeedback()` | Performs haptic feedback on the current platform |

### Platform Implementations

#### Android

```kotlin
@Composable
fun rememberHapticFeedback(): HapticFeedback {
    val view = LocalView.current
    return remember {
        HapticFeedback { 
            view.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY) 
        }
    }
}
```

#### iOS

```kotlin
@Composable
fun rememberHapticFeedback(): HapticFeedback {
    return remember {
        HapticFeedback { 
            val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleLight)
            generator.impactOccurred()
        }
    }
}
```

### Usage Example

```kotlin
@Composable
fun MyComponent() {
    val haptic = rememberHapticFeedback()
    
    Button(
        onClick = {
            haptic.performHapticFeedback()
            // Handle click
        }
    ) {
        Text("Click me")
    }
}
```

## Platform-Specific APIs

### Android

#### HapticFeedback.android.kt

```kotlin
actual fun performHapticFeedback() {
    // Android implementation
}

@Composable
fun rememberHapticFeedback(): HapticFeedback {
    val view = LocalView.current
    return remember {
        HapticFeedback { 
            view.performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY) 
        }
    }
}
```

### iOS

#### HapticFeedback.ios.kt

```kotlin
actual fun performHapticFeedback() {
    val generator = UIImpactFeedbackGenerator(UIImpactFeedbackStyle.UIImpactFeedbackStyleLight)
    generator.impactOccurred()
}

@Composable
fun rememberHapticFeedback(): HapticFeedback {
    return remember {
        HapticFeedback { performHapticFeedback() }
    }
}
```

## Color Theming

### Default Colors

When `Color.Unspecified` is used for color properties, the following defaults are applied:

| Property | Default Value |
|----------|---------------|
| `backgroundColor` | `MaterialTheme.colorScheme.surface` |
| `selectedItemColor` | `MaterialTheme.colorScheme.primary` |
| `unselectedItemColor` | `MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)` |

### Custom Colors

```kotlin
val config = NavigationConfig(
    backgroundColor = Color(0xFF1E1E1E),           // Dark background
    selectedItemColor = Color(0xFF00BCD4),         // Cyan accent
    unselectedItemColor = Color(0xFF757575)        // Gray text
)
```

## Animation Configuration

### Animation Duration

The `animationDuration` property controls the duration of selection animations:

```kotlin
val config = NavigationConfig(
    animationDuration = 500 // 500 milliseconds
)
```

**Recommended Values:**
- Fast: 200-300ms
- Normal: 300-400ms
- Slow: 500-700ms

### Animation Types

The library uses the following animations:

1. **Selection Animation**: Smooth transition between selected/unselected states
2. **Press Animation**: Brief pulse effect when item is pressed
3. **Haptic Feedback**: Platform-specific haptic feedback

## Safe Area Handling

The navigation automatically handles safe areas on different platforms:

### iOS
- Respects home indicator
- Handles notch and status bar
- Uses `WindowInsets.safeDrawing`

### Android
- Respects navigation bar
- Handles status bar
- Uses `WindowInsets.safeDrawing`

### Desktop
- No special safe area handling required
- Full width available

## Accessibility

### Content Descriptions

Navigation items automatically use their `title` property as content descriptions for accessibility:

```kotlin
NavigationItem(
    title = "Home",           // Used as content description
    icon = Icons.Default.Home,
    route = "home"
)
```

### Touch Targets

Navigation items have a minimum touch target size of 44dp to meet accessibility guidelines.

### Screen Reader Support

The navigation is fully compatible with screen readers and provides proper semantic information.

## Performance Considerations

### Optimization Tips

1. **Stable References**: Keep navigation items and configuration stable
2. **Minimal Items**: Limit to 3-5 navigation items for optimal performance
3. **Efficient Icons**: Use vector icons for better performance
4. **Reuse Configuration**: Create configuration objects once and reuse

### Memory Usage

The navigation component has minimal memory footprint:
- No heavy state management
- Efficient animation handling
- Minimal recomposition

### Rendering Performance

- Uses Compose's efficient rendering system
- Optimized to minimize unnecessary recompositions
- Smooth 60fps animations
