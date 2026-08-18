# Architecture

## WaySense — Technical Architecture

---

## Overview

WaySense uses a clean, layered architecture with unidirectional data flow, built on modern Android development practices.

```
┌─────────────────────────────────────────┐
│                  UI Layer               │
│  Compose Screens ← ViewModel ← State   │
├─────────────────────────────────────────┤
│              Navigation Layer           │
│  NavGraph + Route Definitions           │
├─────────────────────────────────────────┤
│               Data Layer                │
│  Repositories ← Mock Data Providers     │
├─────────────────────────────────────────┤
│             Domain Models               │
│  Data classes (immutable)               │
└─────────────────────────────────────────┘
```

---

## Technology Stack

| Component | Choice | Rationale |
|-----------|--------|-----------|
| Language | Kotlin | Modern, concise, null-safe |
| UI | Jetpack Compose | Declarative, accessible, Material 3 native |
| Theme | Material 3 | Dynamic color, accessibility, consistency |
| Navigation | Navigation Compose | Type-safe routes, argument passing |
| State | StateFlow + ViewModel | Lifecycle-aware, unidirectional |
| Build | Gradle Kotlin DSL | Type-safe build scripts |
| Dependencies | Version Catalog (libs.versions.toml) | Centralized, maintainable versions |

---

## Package Structure

```
com.waysense.app/
├── WaySenseApplication.kt          # Application class
├── MainActivity.kt                  # Single-activity entry
├── accessibility/
│   └── AccessibilityUtils.kt       # Semantic helpers
├── data/
│   ├── model/                      # Domain data classes
│   │   ├── Station.kt
│   │   ├── Route.kt
│   │   ├── Journey.kt
│   │   ├── NavigationStep.kt
│   │   ├── Disruption.kt
│   │   ├── AccessibilityFeature.kt
│   │   └── UserProfile.kt
│   ├── mock/                       # Mock data providers
│   │   ├── MockStations.kt
│   │   ├── MockRoutes.kt
│   │   ├── MockJourneyHistory.kt
│   │   ├── MockNavigationSteps.kt
│   │   └── MockDisruptions.kt
│   └── repository/                 # Data access layer
│       ├── StationRepository.kt
│       ├── RouteRepository.kt
│       ├── JourneyRepository.kt
│       └── SettingsRepository.kt
└── ui/
    ├── components/                 # Reusable design-system components
    │   ├── WaySensePrimaryButton.kt
    │   ├── WaySenseSearchBar.kt
    │   ├── WaySenseStationCard.kt
    │   └── ... (13 components)
    ├── navigation/                 # Navigation graph
    │   ├── WaySenseRoute.kt       # Route definitions
    │   ├── WaySenseNavGraph.kt    # NavHost composable
    │   └── WaySenseBottomNavigation.kt
    ├── screens/                    # Screen composables
    │   ├── onboarding/
    │   ├── home/
    │   ├── search/
    │   ├── route/
    │   ├── map/
    │   ├── journey/
    │   ├── journeys/
    │   ├── settings/
    │   ├── profile/
    │   ├── help/
    │   └── station/
    └── theme/                      # Material 3 theme
        ├── Color.kt
        ├── Type.kt
        ├── Shape.kt
        ├── Theme.kt
        └── Dimens.kt
```

---

## Data Flow

### Unidirectional Pattern

```
User Action → ViewModel → StateFlow → Compose Recomposition → UI Update
     ↑                                                          │
     └──────────────────────────────────────────────────────────┘
```

1. User interacts with a Compose screen
2. Screen calls ViewModel method
3. ViewModel updates `MutableStateFlow<UiState>`
4. Compose observes via `collectAsStateWithLifecycle()`
5. UI recomposes with new state

### Example: Search Flow

```
User types query
  → SearchViewModel.onQueryChange(query)
  → MockStations.stations.filter(...)
  → _state.value = state.copy(results = filtered)
  → Compose observes new state
  → LazyColumn recomposes with results
```

---

## Navigation

### Route Definitions

Sealed class `WaySenseScreen` defines all routes with type-safe argument passing:

```kotlin
sealed class WaySenseScreen(val route: String) {
    data object Home : WaySenseScreen("home")
    data object RoutePlanning : WaySenseScreen("route_planning/{stationId}") {
        fun createRoute(stationId: String) = "route_planning/$stationId"
    }
    // ...
}
```

### Navigation Graph

Single `NavHost` in `WaySenseNavGraph.kt` manages all transitions:

- Animated transitions (fade + slide)
- Bottom navigation bar (Home, Journeys, Accessibility, Profile)
- Argument extraction from back stack entries
- Pop-up-to for clean back stack management

---

## Design System

### Component Architecture

All reusable components follow a consistent pattern:

```kotlin
@Composable
fun WaySenseXxx(
    // Data parameters
    modifier: Modifier = Modifier,
) {
    // Accessibility semantics as first modifier
    Modifier.semantics { contentDescription = "..." }
    // Material 3 theming
    // Consistent spacing via WayDimens
}
```

### Theme System

- **Color.kt**: Complete light + dark color palettes
- **Type.kt**: Typography hierarchy (display → label)
- **Shape.kt**: Rounded shapes (extraSmall → extraLarge)
- **Theme.kt**: Material 3 theme with dynamic color support
- **Dimens.kt**: Consistent spacing constants

---

## State Management

### ViewModels

Each major screen has a ViewModel holding UI state:

```kotlin
data class HomeState(
    val greeting: String = "Good evening",
    val nearbyStations: List<Station> = emptyList(),
)

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()
}
```

### Settings

`SettingsRepository` holds user preferences as a `StateFlow<UserProfile>`, updated via `updateProfile()` lambda.

---

## Extensibility

The architecture is designed so a real backend could be introduced by:

1. Replacing mock data providers with API-backed repositories
2. Adding network layer (Retrofit/Ktor)
3. Adding authentication (Firebase Auth / custom)
4. Adding real mapping (Google Maps SDK)
5. Adding real navigation (Google Directions API)

The ViewModel and UI layers would remain largely unchanged.
