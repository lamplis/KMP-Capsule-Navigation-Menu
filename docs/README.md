# KMP-Capsule-Navigation-Menu Documentation

Welcome to the comprehensive documentation for KMP-Capsule-Navigation-Menu, a beautiful floating bottom navigation component for Kotlin Multiplatform applications.

## 📚 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)
- [Quick Start](#quick-start)
- [API Reference](#api-reference)
- [Customization](#customization)
- [Examples](#examples)
- [Platform Support](#platform-support)
- [Performance](#performance)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

## 🎯 Overview

KMP-Capsule-Navigation-Menu is a modern, floating bottom navigation component designed specifically for Kotlin Multiplatform applications. It provides a beautiful, glass-effect navigation bar with perfect capsule shapes, smooth animations, and platform-optimized behavior.

### Key Benefits

- **Modern Design**: Floating navigation bar with glass effect and capsule shapes
- **Cross-Platform**: Works seamlessly on Android, iOS, and Desktop
- **Highly Customizable**: Easy to customize colors, animations, and behavior
- **Accessibility**: Full accessibility support with proper content descriptions
- **Performance**: Optimized for smooth animations and minimal memory usage
- **Safe Area Support**: Respects platform-specific safe areas and navigation bars

## ✨ Features

### Core Features

- **Floating Design**: Modern floating navigation bar that doesn't take up full width
- **Glass Effect**: Translucent background with customizable opacity
- **Capsule Shapes**: Perfect capsule shapes using the KMP-Capsule library
- **Smooth Animations**: Fluid transitions and selection animations
- **Haptic Feedback**: Platform-specific haptic feedback on item selection
- **Badge Support**: Optional badge indicators for navigation items
- **Safe Area Support**: Automatically handles iOS home indicator and Android navigation bar

### Platform Features

- **Android**: Material Design 3 integration with proper theming
- **iOS**: Native iOS design patterns with proper safe area handling
- **Desktop**: Responsive design that works on desktop platforms

## 🚀 Installation

### Prerequisites

- Kotlin Multiplatform project
- Compose Multiplatform setup
- KMP-Capsule library (dependency)

### Step 1: Add as Submodule

```bash
git submodule add https://github.com/lamplis/KMP-Capsule-Navigation-Menu.git libs/KMP-Capsule-Navigation-Menu
```

### Step 2: Configure Gradle

Add to your `settings.gradle.kts`:

```kotlin
include(":libs:KMP-Capsule-Navigation-Menu")
project(":libs:KMP-Capsule-Navigation-Menu").projectDir = File(rootDir, "libs/KMP-Capsule-Navigation-Menu")
```

### Step 3: Add Dependency

Add to your `composeApp/build.gradle.kts`:

```kotlin
commonMain.dependencies {
    implementation(project(":libs:KMP-Capsule-Navigation-Menu"))
    implementation(project(":libs:KMP-Capsule")) // Required dependency
}
```

### Step 4: Initialize Submodule

```bash
git submodule update --init --recursive
```

## 🎮 Quick Start

### Basic Usage

```kotlin
import com.amp_digital.capsule.navigation.FloatingBottomNavigation
import com.amp_digital.capsule.navigation.NavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings

@Composable
fun MyApp() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val navigationItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Profile", Icons.Default.Person, "profile"),
        NavigationItem("Settings", Icons.Default.Settings, "settings")
    )
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Your main content here
        
        FloatingBottomNavigation(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it },
            items = navigationItems,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
```

### With Custom Configuration

```kotlin
@Composable
fun CustomNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        backgroundColor = Color.Blue,
        selectedItemColor = Color.White,
        unselectedItemColor = Color.Gray,
        height = 80.dp,
        enableGlassEffect = true,
        glassEffectAlpha = 0.8f,
        animationDuration = 500
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

## 📖 API Reference

### FloatingBottomNavigation

The main composable for the floating bottom navigation bar.

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

**Parameters:**
- `selectedIndex: Int` - Currently selected navigation item index
- `onItemSelected: (Int) -> Unit` - Callback when a navigation item is selected
- `items: List<NavigationItem>` - List of navigation items to display
- `config: NavigationConfig` - Configuration for customizing appearance and behavior
- `modifier: Modifier` - Modifier for the navigation bar

### NavigationItem

Data class representing a navigation item.

```kotlin
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badge: String? = null
)
```

**Properties:**
- `title: String` - The display title for the navigation item
- `icon: ImageVector` - The icon to display for the navigation item
- `route: String` - The route identifier for navigation
- `badge: String?` - Optional badge content to display on the item

### NavigationConfig

Configuration class for customizing the navigation appearance and behavior.

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

## 🎨 Customization

### Colors

Customize the appearance with your own color scheme:

```kotlin
val config = NavigationConfig(
    backgroundColor = Color(0xFF1E1E1E),
    selectedItemColor = Color(0xFF00BCD4),
    unselectedItemColor = Color(0xFF757575)
)
```

### Dimensions

Adjust the size and spacing:

```kotlin
val config = NavigationConfig(
    height = 80.dp,
    cornerRadius = 40.dp,
    horizontalPadding = 24.dp,
    verticalPadding = 16.dp,
    itemSpacing = 12.dp
)
```

### Animations

Control animation behavior:

```kotlin
val config = NavigationConfig(
    animationDuration = 500, // milliseconds
    enableHapticFeedback = true
)
```

### Glass Effect

Customize the glass effect:

```kotlin
val config = NavigationConfig(
    enableGlassEffect = true,
    glassEffectAlpha = 0.85f // 0.0 to 1.0
)
```

## 💻 Examples

### Basic Navigation

```kotlin
@Composable
fun BasicNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val items = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Search", Icons.Default.Search, "search"),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = items
    )
}
```

### Navigation with Badges

```kotlin
@Composable
fun NavigationWithBadges() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val items = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Messages", Icons.Default.Message, "messages", badge = "3"),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = items
    )
}
```

### Dark Theme Navigation

```kotlin
@Composable
fun DarkThemeNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        backgroundColor = Color(0xFF121212),
        selectedItemColor = Color(0xFFBB86FC),
        unselectedItemColor = Color(0xFF757575),
        glassEffectAlpha = 0.95f
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = items,
        config = config
    )
}
```

### Custom Styled Navigation

```kotlin
@Composable
fun CustomStyledNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        backgroundColor = Color(0xFF6200EE),
        selectedItemColor = Color.White,
        unselectedItemColor = Color.White.copy(alpha = 0.7f),
        height = 88.dp,
        cornerRadius = 44.dp,
        horizontalPadding = 16.dp,
        verticalPadding = 8.dp,
        animationDuration = 400
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = items,
        config = config
    )
}
```

## 📱 Platform Support

### Android
- **Minimum SDK**: API 21 (Android 5.0)
- **Features**: Material Design 3 integration, haptic feedback, proper theming
- **Safe Areas**: Handles navigation bar and status bar

### iOS
- **Minimum Version**: iOS 13.0
- **Features**: Native iOS design patterns, haptic feedback, proper safe area handling
- **Safe Areas**: Handles home indicator and notch

### Desktop
- **Platforms**: Windows, macOS, Linux
- **Features**: Responsive design, keyboard navigation support
- **Safe Areas**: Not applicable

## ⚡ Performance

### Optimization Features

- **Efficient Rendering**: Uses Compose's efficient rendering system
- **Minimal Recomposition**: Optimized to minimize unnecessary recompositions
- **Memory Efficient**: Minimal memory footprint with efficient state management
- **Smooth Animations**: 60fps animations with proper easing curves

### Best Practices

1. **Use Stable References**: Keep navigation items and configuration stable
2. **Minimize Items**: Limit to 3-5 navigation items for optimal UX
3. **Optimize Icons**: Use vector icons for better performance
4. **Reuse Configuration**: Create configuration objects once and reuse

## 🔧 Troubleshooting

### Common Issues

#### Navigation Not Appearing
- Ensure KMP-Capsule dependency is added
- Check that the navigation is placed in a Box with proper alignment
- Verify that items list is not empty

#### Styling Issues
- Check that colors are properly defined in your theme
- Ensure MaterialTheme is properly set up
- Verify that the configuration is applied correctly

#### Animation Problems
- Check that animation duration is reasonable (100-1000ms)
- Ensure that the selected index is properly managed
- Verify that the state is stable and not changing frequently

#### Platform-Specific Issues

**Android:**
- Ensure proper Material Design 3 setup
- Check that haptic feedback permissions are granted
- Verify that the navigation bar is properly handled

**iOS:**
- Ensure proper safe area handling
- Check that the home indicator is not overlapping
- Verify that haptic feedback is working

### Debug Tips

1. **Enable Debug Mode**: Add debug logging to track state changes
2. **Check State**: Verify that selectedIndex is properly managed
3. **Test on Device**: Always test on real devices for proper behavior
4. **Profile Performance**: Use Compose profiler to check for performance issues

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

### Development Setup

1. Clone the repository
2. Open in IntelliJ IDEA or Android Studio
3. Run the sample app to test changes
4. Submit a pull request with your changes

### Code Style

- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add proper documentation for public APIs
- Write tests for new features

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [KMP-Capsule](https://github.com/lamplis/KMP-Capsule) for the perfect capsule shapes
- [Kyant0/Capsule](https://github.com/Kyant0/Capsule) for the original capsule implementation
- Jetpack Compose team for the amazing UI framework
- Kotlin Multiplatform team for the cross-platform capabilities
