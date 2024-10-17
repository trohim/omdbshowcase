# Libraries and SDKs

This project utilizes several third-party libraries to enhance functionality, improve performance, and streamline development. Below is a list of these libraries, along with a brief description of their usage:

## AndroidX Libraries

- **androidx-core-ktx**: Provides Kotlin extension functions for Android core libraries, making development more idiomatic and concise.
- **androidx-lifecycle-runtime-ktx**: Offers Kotlin extensions for lifecycle-aware components, which help manage UI lifecycle changes.
- **androidx-activity-compose**: Supports Jetpack Compose integration with Android activity lifecycle.
- **androidx-navigation-compose**: Enables navigation handling in Jetpack Compose applications.
- **androidx-compose-ui**, **androidx-ui-graphics**, **androidx-ui-tooling-preview**: Core Compose libraries for building UI, handling graphics, and previewing UI components.
- **androidx-material3**: Provides Material Design components for Compose.
- **androidx-core-splashscreen**: Simplifies implementing a consistent splash screen experience across different APIs.
- **androidx-fragment-ktx**: Offers Kotlin extensions for Android’s Fragment API.
- **androidx-hilt-navigation-compose**: Integration of Hilt with Jetpack Compose navigation system for dependency injection.

## Dependency Injection

- **hilt-android** & **hilt-compiler**: Part of Hilt, a dependency injection library for Android that reduces boilerplate code and integrates well with the Android ecosystem.

## Networking and Data

- **retrofit-core** & **retrofit-kotlin-serialization**: Retrofit is used for making HTTP requests; along with Kotlin serialization, it allows for efficient parsing of JSON responses.
- **okhttp-logging**: Provides logging capabilities for network requests, useful for debugging networking issues.

## Image Loading

- **coil-kt** & **coil-kt-compose**: A modern image loading library for Android with support for Kotlin Coroutines and Jetpack Compose.

## Serialization

- **kotlinx-serialization-json**: A Kotlin serialization library used for parsing JSON data.

## Other Libraries

- **androidx-appcompat**: Provides backward-compatible versions of Android features and classes.
- **material**: Supplies Material Design components for traditional Android views.
