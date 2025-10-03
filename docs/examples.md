# Examples

This document provides comprehensive examples of using KMP-Capsule-Navigation-Menu in various scenarios.

## Table of Contents

- [Basic Usage](#basic-usage)
- [Custom Styling](#custom-styling)
- [Navigation with Badges](#navigation-with-badges)
- [Theme Integration](#theme-integration)
- [Advanced Configuration](#advanced-configuration)
- [Platform-Specific Examples](#platform-specific-examples)
- [Real-World Applications](#real-world-applications)

## Basic Usage

### Simple Navigation

```kotlin
@Composable
fun SimpleNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val navigationItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Search", Icons.Default.Search, "search"),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        when (selectedIndex) {
            0 -> HomeScreen()
            1 -> SearchScreen()
            2 -> ProfileScreen()
        }
        
        FloatingBottomNavigation(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it },
            items = navigationItems,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
```

### Navigation with State Management

```kotlin
@Composable
fun NavigationWithState() {
    val navController = rememberNavController()
    var selectedIndex by remember { mutableStateOf(0) }
    
    val navigationItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Messages", Icons.Default.Message, "messages"),
        NavigationItem("Settings", Icons.Default.Settings, "settings")
    )
    
    LaunchedEffect(selectedIndex) {
        when (selectedIndex) {
            0 -> navController.navigate("home")
            1 -> navController.navigate("messages")
            2 -> navController.navigate("settings")
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = "home") {
            composable("home") { HomeScreen() }
            composable("messages") { MessagesScreen() }
            composable("settings") { SettingsScreen() }
        }
        
        FloatingBottomNavigation(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it },
            items = navigationItems,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
```

## Custom Styling

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
        items = navigationItems,
        config = config
    )
}
```

### Custom Color Scheme

```kotlin
@Composable
fun CustomColorNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        backgroundColor = Color(0xFF6200EE),
        selectedItemColor = Color.White,
        unselectedItemColor = Color.White.copy(alpha = 0.7f)
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

### Minimalist Design

```kotlin
@Composable
fun MinimalistNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        backgroundColor = Color.Transparent,
        selectedItemColor = Color.Black,
        unselectedItemColor = Color.Gray,
        enableGlassEffect = false,
        height = 60.dp,
        cornerRadius = 30.dp
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

## Navigation with Badges

### Simple Badges

```kotlin
@Composable
fun NavigationWithBadges() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val navigationItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Messages", Icons.Default.Message, "messages", badge = "3"),
        NavigationItem("Notifications", Icons.Default.Notifications, "notifications", badge = "12"),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems
    )
}
```

### Dynamic Badges

```kotlin
@Composable
fun DynamicBadgeNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    var messageCount by remember { mutableStateOf(3) }
    var notificationCount by remember { mutableStateOf(12) }
    
    val navigationItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem(
            "Messages", 
            Icons.Default.Message, 
            "messages", 
            badge = if (messageCount > 0) messageCount.toString() else null
        ),
        NavigationItem(
            "Notifications", 
            Icons.Default.Notifications, 
            "notifications", 
            badge = if (notificationCount > 0) notificationCount.toString() else null
        ),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    // Simulate badge updates
    LaunchedEffect(Unit) {
        delay(5000)
        messageCount = 0
        notificationCount = 5
    }
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems
    )
}
```

## Theme Integration

### Material Design 3 Integration

```kotlin
@Composable
fun Material3Navigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    // Use Material 3 colors from theme
    val config = NavigationConfig(
        backgroundColor = MaterialTheme.colorScheme.surface,
        selectedItemColor = MaterialTheme.colorScheme.primary,
        unselectedItemColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

### Custom Theme Integration

```kotlin
@Composable
fun CustomThemeNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val customColors = CustomColors()
    
    val config = NavigationConfig(
        backgroundColor = customColors.surface,
        selectedItemColor = customColors.primary,
        unselectedItemColor = customColors.onSurface
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

## Advanced Configuration

### Custom Dimensions

```kotlin
@Composable
fun CustomDimensionsNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        height = 88.dp,
        cornerRadius = 44.dp,
        horizontalPadding = 16.dp,
        verticalPadding = 8.dp,
        itemSpacing = 12.dp
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

### Custom Animations

```kotlin
@Composable
fun CustomAnimationNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        animationDuration = 500,
        enableHapticFeedback = true
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

### Glass Effect Variations

```kotlin
@Composable
fun GlassEffectNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        enableGlassEffect = true,
        glassEffectAlpha = 0.8f
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

## Platform-Specific Examples

### Android-Specific Features

```kotlin
@Composable
fun AndroidNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        backgroundColor = Color(0xFF1E1E1E),
        selectedItemColor = Color(0xFF00BCD4),
        unselectedItemColor = Color(0xFF757575),
        enableHapticFeedback = true
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

### iOS-Specific Features

```kotlin
@Composable
fun IOSNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val config = NavigationConfig(
        backgroundColor = Color(0xFFF2F2F7),
        selectedItemColor = Color(0xFF007AFF),
        unselectedItemColor = Color(0xFF8E8E93),
        height = 80.dp,
        cornerRadius = 40.dp
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

## Real-World Applications

### E-Commerce App

```kotlin
@Composable
fun ECommerceNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    var cartItemCount by remember { mutableStateOf(2) }
    
    val navigationItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Categories", Icons.Default.Category, "categories"),
        NavigationItem("Cart", Icons.Default.ShoppingCart, "cart", badge = cartItemCount.toString()),
        NavigationItem("Orders", Icons.Default.Receipt, "orders"),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems
    )
}
```

### Social Media App

```kotlin
@Composable
fun SocialMediaNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    var messageCount by remember { mutableStateOf(5) }
    var notificationCount by remember { mutableStateOf(12) }
    
    val navigationItems = listOf(
        NavigationItem("Feed", Icons.Default.Home, "feed"),
        NavigationItem("Search", Icons.Default.Search, "search"),
        NavigationItem("Messages", Icons.Default.Message, "messages", badge = messageCount.toString()),
        NavigationItem("Notifications", Icons.Default.Notifications, "notifications", badge = notificationCount.toString()),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems
    )
}
```

### Productivity App

```kotlin
@Composable
fun ProductivityNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    var taskCount by remember { mutableStateOf(8) }
    
    val navigationItems = listOf(
        NavigationItem("Dashboard", Icons.Default.Dashboard, "dashboard"),
        NavigationItem("Tasks", Icons.Default.Assignment, "tasks", badge = taskCount.toString()),
        NavigationItem("Calendar", Icons.Default.CalendarToday, "calendar"),
        NavigationItem("Notes", Icons.Default.Note, "notes"),
        NavigationItem("Settings", Icons.Default.Settings, "settings")
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems
    )
}
```

### Music Streaming App

```kotlin
@Composable
fun MusicAppNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val navigationItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Search", Icons.Default.Search, "search"),
        NavigationItem("Library", Icons.Default.LibraryMusic, "library"),
        NavigationItem("Playlists", Icons.Default.PlaylistPlay, "playlists"),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    val config = NavigationConfig(
        backgroundColor = Color(0xFF1A1A1A),
        selectedItemColor = Color(0xFF1DB954),
        unselectedItemColor = Color(0xFFB3B3B3)
    )
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

## Complex Navigation Patterns

### Nested Navigation

```kotlin
@Composable
fun NestedNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    var subSelectedIndex by remember { mutableStateOf(0) }
    
    val mainNavigationItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Categories", Icons.Default.Category, "categories"),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    val categoryNavigationItems = listOf(
        NavigationItem("Electronics", Icons.Default.Devices, "electronics"),
        NavigationItem("Clothing", Icons.Default.Checkroom, "clothing"),
        NavigationItem("Books", Icons.Default.MenuBook, "books")
    )
    
    Box(modifier = Modifier.fillMaxSize()) {
        when (selectedIndex) {
            0 -> HomeScreen()
            1 -> {
                Column {
                    CategoryScreen()
                    FloatingBottomNavigation(
                        selectedIndex = subSelectedIndex,
                        onItemSelected = { subSelectedIndex = it },
                        items = categoryNavigationItems,
                        config = NavigationConfig(height = 60.dp, cornerRadius = 30.dp)
                    )
                }
            }
            2 -> ProfileScreen()
        }
        
        FloatingBottomNavigation(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it },
            items = mainNavigationItems,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
```

### Conditional Navigation

```kotlin
@Composable
fun ConditionalNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    var isLoggedIn by remember { mutableStateOf(false) }
    
    val loggedInItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Messages", Icons.Default.Message, "messages"),
        NavigationItem("Profile", Icons.Default.Person, "profile")
    )
    
    val loggedOutItems = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Login", Icons.Default.Login, "login"),
        NavigationItem("Register", Icons.Default.PersonAdd, "register")
    )
    
    val navigationItems = if (isLoggedIn) loggedInItems else loggedOutItems
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems
    )
}
```

## Performance Optimization Examples

### Stable Configuration

```kotlin
@Composable
fun OptimizedNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    // Create stable configuration
    val config = remember {
        NavigationConfig(
            backgroundColor = Color(0xFF1E1E1E),
            selectedItemColor = Color(0xFF00BCD4),
            unselectedItemColor = Color(0xFF757575)
        )
    }
    
    // Create stable navigation items
    val navigationItems = remember {
        listOf(
            NavigationItem("Home", Icons.Default.Home, "home"),
            NavigationItem("Search", Icons.Default.Search, "search"),
            NavigationItem("Profile", Icons.Default.Person, "profile")
        )
    }
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems,
        config = config
    )
}
```

### Lazy Navigation Items

```kotlin
@Composable
fun LazyNavigation() {
    var selectedIndex by remember { mutableStateOf(0) }
    
    val navigationItems = remember {
        listOf(
            NavigationItem("Home", Icons.Default.Home, "home"),
            NavigationItem("Search", Icons.Default.Search, "search"),
            NavigationItem("Profile", Icons.Default.Person, "profile")
        )
    }
    
    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        items = navigationItems
    )
}
```
