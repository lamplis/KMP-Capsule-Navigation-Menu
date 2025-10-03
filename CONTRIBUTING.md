# Contributing to KMP-Capsule-Navigation-Menu

Thank you for your interest in contributing to KMP-Capsule-Navigation-Menu! This document provides guidelines and information for contributors.

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Setup](#development-setup)
- [Contributing Guidelines](#contributing-guidelines)
- [Pull Request Process](#pull-request-process)
- [Issue Reporting](#issue-reporting)
- [Code Style](#code-style)
- [Testing](#testing)
- [Documentation](#documentation)

## Code of Conduct

This project adheres to a code of conduct that we expect all contributors to follow. Please be respectful and inclusive in all interactions.

## Getting Started

### Prerequisites

- Kotlin Multiplatform project setup
- IntelliJ IDEA or Android Studio
- Git
- Basic understanding of Compose Multiplatform

### Fork and Clone

1. Fork the repository on GitHub
2. Clone your fork locally:
   ```bash
   git clone https://github.com/your-username/KMP-Capsule-Navigation-Menu.git
   cd KMP-Capsule-Navigation-Menu
   ```

3. Add the upstream repository:
   ```bash
   git remote add upstream https://github.com/lamplis/KMP-Capsule-Navigation-Menu.git
   ```

## Development Setup

### 1. Open in IDE

Open the project in IntelliJ IDEA or Android Studio. The project should automatically sync and download dependencies.

### 2. Run Sample App

The project includes a sample app that demonstrates the navigation component:

```bash
# For Android
./gradlew :sample:assembleDebug

# For iOS (macOS only)
./gradlew :sample:iosSimulatorArm64Test
```

### 3. Run Tests

```bash
./gradlew test
```

## Contributing Guidelines

### Types of Contributions

We welcome the following types of contributions:

- **Bug Fixes**: Fix issues and improve stability
- **New Features**: Add new functionality to the library
- **Documentation**: Improve documentation and examples
- **Performance**: Optimize performance and reduce memory usage
- **Accessibility**: Improve accessibility support
- **Platform Support**: Add support for new platforms

### Before You Start

1. **Check Existing Issues**: Look for existing issues or discussions related to your contribution
2. **Create an Issue**: For significant changes, create an issue first to discuss the approach
3. **Small Changes**: For small fixes, you can directly create a pull request

### Development Workflow

1. **Create a Branch**:
   ```bash
   git checkout -b feature/your-feature-name
   # or
   git checkout -b fix/issue-description
   ```

2. **Make Changes**: Implement your changes following the code style guidelines

3. **Test Your Changes**: Ensure all tests pass and test on multiple platforms

4. **Update Documentation**: Update relevant documentation if needed

5. **Commit Changes**:
   ```bash
   git add .
   git commit -m "feat: add new feature description"
   ```

6. **Push and Create PR**:
   ```bash
   git push origin feature/your-feature-name
   ```

## Pull Request Process

### Before Submitting

- [ ] Code follows the project's code style
- [ ] All tests pass
- [ ] Documentation is updated
- [ ] Changes are tested on multiple platforms
- [ ] Commit messages follow conventional commits format

### PR Description

When creating a pull request, please include:

1. **Description**: Clear description of what the PR does
2. **Related Issues**: Link to any related issues
3. **Testing**: Describe how you tested the changes
4. **Screenshots**: For UI changes, include screenshots
5. **Breaking Changes**: Note any breaking changes

### Review Process

1. **Automated Checks**: CI will run tests and checks
2. **Code Review**: Maintainers will review the code
3. **Feedback**: Address any feedback from reviewers
4. **Merge**: Once approved, the PR will be merged

## Issue Reporting

### Bug Reports

When reporting bugs, please include:

1. **Description**: Clear description of the bug
2. **Steps to Reproduce**: Detailed steps to reproduce the issue
3. **Expected Behavior**: What you expected to happen
4. **Actual Behavior**: What actually happened
5. **Environment**: Platform, version, and other relevant details
6. **Screenshots**: If applicable, include screenshots

### Feature Requests

For feature requests, please include:

1. **Description**: Clear description of the feature
2. **Use Case**: Why this feature would be useful
3. **Proposed Solution**: How you think it should work
4. **Alternatives**: Any alternative solutions you've considered

## Code Style

### Kotlin Style

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Prefer immutable data structures
- Use `val` over `var` when possible

### Compose Style

- Follow [Compose Guidelines](https://developer.android.com/jetpack/compose/guidelines)
- Use proper state management
- Optimize for performance
- Follow accessibility guidelines

### File Organization

```
src/
├── commonMain/
│   └── kotlin/
│       └── com/
│           └── amp_digital/
│               └── capsule/
│                   └── navigation/
├── androidMain/
│   └── kotlin/
│       └── com/
│           └── amp_digital/
│               └── capsule/
│                   └── navigation/
└── iosMain/
    └── kotlin/
        └── com/
            └── amp_digital/
                └── capsule/
                    └── navigation/
```

### Naming Conventions

- **Classes**: PascalCase (e.g., `FloatingBottomNavigation`)
- **Functions**: camelCase (e.g., `performHapticFeedback`)
- **Variables**: camelCase (e.g., `selectedIndex`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DEFAULT_ANIMATION_DURATION`)

## Testing

### Unit Tests

Write unit tests for new functionality:

```kotlin
@Test
fun `should create navigation item with correct properties`() {
    val item = NavigationItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = "home"
    )
    
    assertEquals("Home", item.title)
    assertEquals("home", item.route)
}
```

### Integration Tests

Test the component in different scenarios:

```kotlin
@Test
fun `should handle selection correctly`() {
    // Test selection behavior
}
```

### Platform Testing

Test on multiple platforms:
- Android (API 21+)
- iOS (iOS 13+)
- Desktop (Windows, macOS, Linux)

## Documentation

### Code Documentation

- Add KDoc comments for public APIs
- Include examples in documentation
- Document any platform-specific behavior

### README Updates

- Update README for new features
- Add examples for new functionality
- Update installation instructions if needed

### API Documentation

- Update API reference for new APIs
- Include parameter descriptions
- Add usage examples

## Release Process

### Versioning

We follow [Semantic Versioning](https://semver.org/):
- **MAJOR**: Breaking changes
- **MINOR**: New features (backward compatible)
- **PATCH**: Bug fixes (backward compatible)

### Changelog

Update the CHANGELOG.md file with:
- New features
- Bug fixes
- Breaking changes
- Deprecations

## Community

### Getting Help

- **GitHub Issues**: For bugs and feature requests
- **Discussions**: For questions and general discussion
- **Discord**: Join our community Discord server

### Recognition

Contributors will be recognized in:
- README.md contributors section
- Release notes
- GitHub contributors page

## License

By contributing to this project, you agree that your contributions will be licensed under the Apache License 2.0.

## Thank You

Thank you for contributing to KMP-Capsule-Navigation-Menu! Your contributions help make this library better for the entire Kotlin Multiplatform community.
