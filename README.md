# Navigation3 Product List

A modern Android application showcasing product management using Jetpack Compose, Navigation3, and Clean Architecture principles.

## Overview

This application demonstrates a clean implementation of a product management system with the following features:
- Product listing with modern UI
- Product details view
- Product editing and creation
- Mock data repository for quick testing

## Architecture

The project follows Clean Architecture principles with a clear separation of concerns:

### Data Layer
- Repository implementation (`FakeProductRepositoryImpl`)
- Data models (`ProductDto`)

### Domain Layer
- Business models (`Product`)
- Repository interfaces (`ProductRepository`)
- Use cases:
  - `GetProductsUseCase`
  - `GetProductByIdUseCase`
  - `SaveProductUseCase`
  - `DeleteProductUseCase`

### UI Layer
- View models:
  - `ProductListViewModel`
  - `ProductDetailViewModel`
  - `EditProductViewModel`
- Compose UI screens:
  - Product list screen
  - Product detail screen
  - Product edit/create screen

## Technologies

- **UI**: Jetpack Compose with Material 3
- **Navigation**: Navigation3
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt
- **Concurrency**: Kotlin Coroutines & Flow
- **Image Loading**: Coil

## Getting Started

### Prerequisites
- Android Studio Hedgehog or newer
- Kotlin 1.9.0+
- Android SDK 26+

### Installation
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application on an emulator or physical device

## Features

### Product List
- Displays a list of products with images, names, and prices
- Supports pull-to-refresh
- Provides error handling and empty state
- Floating action button for adding new products

### Product Details
- Shows comprehensive product information
- Displays product image, name, price, and description
- Edit button for modifying product details

### Product Editing
- Form for creating or editing products
- Input validation
- Success and error handling

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Material Design 3 for the modern UI components
- Jetpack Compose for the declarative UI
- Navigation3 for the simplified navigation
- Hilt for dependency injection
