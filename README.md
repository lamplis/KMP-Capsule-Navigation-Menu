<div align="center">

# KMP‑Capsule‑Navigation‑Menu

Elegant, glassy, capsule‑shaped bottom navigation for Kotlin Multiplatform (Android + iOS) built with Compose Multiplatform.

[![Kotlin](https://img.shields.io/badge/kotlin-2.x-blue.svg)](https://kotlinlang.org/)  
[![Compose Multiplatform](https://img.shields.io/badge/compose-multiplatform-1.7%2B-green.svg)](https://www.jetbrains.com/lp/compose-multiplatform/)  
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](./LICENSE)

</div>

---

- **Multiplatform**: Android and iOS targets from one codebase
- **Capsule Aesthetic**: Uses `ContinuousCapsule` shape from `KMP‑Capsule` for a crisp, modern look
- **Glass Effect**: Optional translucent “liquid glass” background
- **Haptics**: Platform‑specific haptic feedback on selection
- **Safe Area Aware**: Respects iOS home indicator and Android nav bars
- **Tiny API**: Simple `NavigationItem` + `NavigationConfig` with sane defaults

Repository: `lamplis/KMP-Capsule-Navigation-Menu`  
GitHub: https://github.com/lamplis/KMP-Capsule-Navigation-Menu.git

## Quick Start

1) Add the module to your Gradle settings

```kotlin
// settings.gradle.kts (root)
include(":libs:KMP-Capsule-Navigation-Menu")
project(":libs:KMP-Capsule-Navigation-Menu").projectDir = File(rootDir, "libs/KMP-Capsule-Navigation-Menu")
```

2) Depend on it from your shared/app module

```kotlin
// composeApp/build.gradle.kts (example)
commonMain.dependencies {
    implementation(project(":libs:KMP-Capsule-Navigation-Menu"))
    implementation(project(":libs:KMP-Capsule")) // ContinuousCapsule dependency
}
```

3) Use in your Composable

```kotlin
@Composable
fun MyBottomBar(selectedIndex: Int, onSelect: (Int) -> Unit, modifier: Modifier = Modifier) {
    val items = listOf(
        NavigationItem("Home", Icons.Default.Home, "home"),
        NavigationItem("Collection", Icons.Default.AccountCircle, "collection", badge = "3"),
        NavigationItem("Trade", Icons.Default.SwapHoriz, "trade"),
        NavigationItem("Nearby", Icons.Default.LocationOn, "nearby")
    )

    FloatingBottomNavigation(
        selectedIndex = selectedIndex,
        onItemSelected = onSelect,
        items = items,
        config = NavigationConfig(),
        modifier = modifier
    )
}
```

## API Overview

```kotlin
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badge: String? = null
)

data class NavigationConfig(
    val backgroundColor: Color = Color.Unspecified,
    val selectedItemColor: Color = Color.Unspecified,
    val unselectedItemColor: Color = Color.Unspecified,
    val height: Dp = 72.dp,
    val horizontalPadding: Dp = 32.dp,
    val verticalPadding: Dp = 12.dp,
    val itemSpacing: Dp = 8.dp,
    val animationDuration: Int = 300,
    val enableHapticFeedback: Boolean = true,
    val enableGlassEffect: Boolean = true,
    val glassEffectAlpha: Float = 0.92f
)

@Composable
fun FloatingBottomNavigation(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    items: List<NavigationItem>,
    config: NavigationConfig = NavigationConfig(),
    modifier: Modifier = Modifier
)
```

### Notes
- Colors default to your `MaterialTheme` when `Color.Unspecified` is used.
- Capsule shape is provided by `ContinuousCapsule` from the `KMP‑Capsule` library.
- Haptics are implemented per-platform (Android/iOS) out of the box.
- The component pads itself using `WindowInsets.safeDrawing` for bottom/home indicators.

## Preview

The library includes a hard‑coded preview (`FloatingBottomNavigationPreview`) under `commonMain` so Android Studio can render it. For Android Previews, the module applies `compose.uiTooling`.

```kotlin
@Composable
@Preview
fun FloatingBottomNavigationPreview() {
    MaterialTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
            Box(Modifier.fillMaxSize()) {
                var selected by remember { mutableStateOf(0) }
                val items = listOf(
                    NavigationItem("Home", Icons.Filled.Home, "home"),
                    NavigationItem("Collection", Icons.Filled.AccountCircle, "collection", badge = "3"),
                    NavigationItem("Trade", Icons.Filled.SwapHoriz, "trade"),
                    NavigationItem("Nearby", Icons.Filled.LocationOn, "nearby")
                )
                FloatingBottomNavigation(
                    selectedIndex = selected,
                    onItemSelected = { selected = it },
                    items = items,
                    modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                )
            }
        }
    }
}
```

## Theming & Styling

- Set `backgroundColor`, `selectedItemColor`, `unselectedItemColor` in `NavigationConfig`, or leave as `Color.Unspecified` to use `MaterialTheme`.
- Enable/disable the glass effect with `enableGlassEffect` and tune `glassEffectAlpha` (0.0 – 1.0).
- Adjust `height`, paddings and `itemSpacing` to fit your layout.

## Platform Details

- Android: Haptic feedback uses `View.performHapticFeedback`.
- iOS: Haptics use `UIImpactFeedbackGenerator`.
- Both platforms respect safe areas/home indicators via `WindowInsets.safeDrawing`.

## Development

Build module only:

```bash
./gradlew :libs:KMP-Capsule-Navigation-Menu:build
```

Use from a sample app (Android):

```bash
./gradlew :composeApp:assembleDebug
```

## Roadmap

- Badges with counts + animations
- Optional labels on selection only
- Accessibility refinements and TalkBack/VoiceOver docs

## License

This project is licensed under the MIT License – see [LICENSE](./LICENSE).

---

For updates and issues, see the GitHub repository:  
`lamplis/KMP-Capsule-Navigation-Menu` — https://github.com/lamplis/KMP-Capsule-Navigation-Menu.git
