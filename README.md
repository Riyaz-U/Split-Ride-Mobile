# Split Ride

A Kotlin Multiplatform (KMM) app for Android and iOS that matches commuters travelling the same route. Users post a ride intent (origin area → destination area + departure time), get matched with others on a similar route, and split the fare — reducing the cost for everyone in the group.

## Platform Support

| Platform | Status |
|----------|--------|
| Android  | Supported (minSdk 24, targetSdk 36) |
| iOS      | Supported (iosArm64, iosSimulatorArm64) |

## Tech Stack

| Layer | Library |
|-------|---------|
| UI | Compose Multiplatform 1.10, Material3 |
| DI | Koin 4.1 |
| Networking | Ktor 3.4 (OkHttp on Android, Darwin on iOS) |
| Serialization | Kotlinx Serialization |
| Storage | KVault (secure key-value) |
| Async | Kotlinx Coroutines |
| Date/Time | Kotlinx DateTime |
| Navigation | Jetpack Compose Navigation |

## Architecture

The project follows **Clean Architecture** with a custom **MVI (Model-View-Intent)** pattern.

```
feature/
├── domain/        # Interfaces, use cases, domain models
├── data/          # Repository implementations, DTOs, data sources
└── presentation/  # Screen, ViewModel, State, Intent
```

### MVI Flow

```
User Action → Intent → StateUpdater (ReducerBuilder DSL) → State → UI
                              ↓ (unhandled)
                          EventBus → Navigation / Snackbar
```

- **Intent**: All user actions subclass `Intent` (`core/ui/routing/Intent.kt`)
- **StateUpdater**: `on<IntentType> { ... }` DSL reduces intents into new state
- **EventBus**: Coroutines `Channel<Intent>` for cross-feature events (navigation, snackbars)

## Features

### Onboarding
Welcome screens shown on first launch before authentication.

### Authentication
- **Login**: Email + password with inline validation
- **Registration**: First name, last name, email, password

API endpoints:
- `POST /api/auth/login`
- `POST /api/auth/register`

### Home
Main dashboard with:
- **Active status toggle** — enables/disables ride matching
- **Create Intent card** — shortcut to start a new ride
- **Nearby ride groups** — groups with companion count, occupancy, and savings %
- **Recent commutes** — history with savings per trip

### Create Ride Intent *(in progress)*
Form to publish a new ride intent:
- Route details (pickup coordinates, destination coordinates)
- Schedule (date + time picker)
- Preferences (flexible timing window in minutes, max group size)

Domain model (`RideIntent`):
```kotlin
data class RideIntent(
    val id: String,
    val sourceLat: Double,
    val sourceLng: Double,
    val destinationLat: Double,
    val destinationLng: Double,
    val startTime: Instant,   // ISO format
    val flexibleMinutes: Int
)
```

## Project Structure

```
composeApp/src/commonMain/kotlin/com/wiseowl/splitride/
├── core/
│   ├── di/            # Koin modules
│   ├── network/       # ApiService, EndPoint, AuthInterceptor, SplitRideResponse
│   ├── storage/       # StorageManager, AuthenticationStorage, UserDetailStorage
│   ├── theme/         # Colors, Typography
│   └── ui/            # Shared components, routing (Screen, Root, EventBus, StateUpdater)
├── onboarding/
│   └── presentation/
├── authentication/
│   ├── domain/        # AuthenticationService, InputValidator, use cases
│   ├── data/          # AuthenticationRepositoryImpl, DTOs
│   └── presentation/  # Login, Registration screens + ViewModels
└── ride/
    ├── common/
    │   ├── domain/    # RideRepository interface, RideIntent model
    │   └── data/      # RideRepositoryImpl
    ├── home/
    │   ├── domain/    # HomeRepository, RideGroup, CommuteDetail
    │   ├── data/      # HomeRepositoryImpl
    │   └── presentation/
    └── create_intent/
        ├── data/      # CreateRideIntentRequestDTO
        └── presentation/
```

## Navigation

```
Onboarding → Login ←→ Registration
                ↓
             Home ←→ CreateIntent
                ↓
             Settings
```

## Build & Run

### Prerequisites
- Android Studio Ladybug or later
- JDK 11
- Xcode (for iOS builds)

### Android

```bash
# macOS / Linux
./gradlew :composeApp:assembleDebug

# Windows
.\gradlew.bat :composeApp:assembleDebug
```

### iOS

Open `iosApp/iosApp.xcodeproj` in Xcode and run on a simulator or device.

## Feature Status

| Feature | Domain | Data | UI |
|---------|--------|------|----|
| Onboarding | — | — | Done |
| Login | Done | Done | Done |
| Registration | Done | Done | Done |
| Home | Done | Stubbed | Done |
| Create Ride Intent | Done | Stubbed | Done |