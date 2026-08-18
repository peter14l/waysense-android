# WaySense

**Accessible Public Transport Navigation Assistant for Visually Impaired Commuters**

> "Navigate independently."

> "Accessible journeys, one step at a time."

---

## Problem Statement

Visually impaired commuters face significant challenges navigating public transport systems. Station layouts are complex, accessibility information is scattered or unavailable, and real-time guidance during journeys is virtually nonexistent. WaySense demonstrates how technology could help a visually impaired person independently navigate public transportation.

## Design Thinking Context

This project was developed as a **Design Thinking / academic prototype** following the double-diamond process: Empathize, Define, Ideate, Prototype, Test. It is not a production transportation service.

## Target Users

- Fully blind commuters
- Partially sighted commuters
- Users relying on TalkBack / screen readers
- Users requiring enlarged text or high-contrast interfaces
- Users who benefit from voice or vibration guidance

## Core Hypothesis

An accessibility-first mobile application that provides step-by-step navigation guidance, station accessibility information, and disruption alerts can help visually impaired commuters navigate public transport more independently and confidently.

---

## Features

- **Accessibility-focused onboarding** with TalkBack, large text, high contrast, voice, and vibration options
- **Search and route planning** with accessibility-rated route options
- **Active journey navigation** with large-font step-by-step instructions
- **Station accessibility details** including features, scores, and verification dates
- **Disruption simulation** with alternative route suggestions
- **Accessibility settings** for voice, vibration, contrast, and interface simplification
- **Help/emergency** screen with quick actions
- **Journey history** with past trips

## Screenshots

> Screenshots will be added after APK generation.

---

## Accessibility Philosophy

Accessibility is not a polish pass in WaySense. Every screen, component, and interaction is designed around the needs of visually impaired commuters:

- All interactive elements have `contentDescription` semantics
- Touch targets are minimum 48dp, navigation controls 56dp+
- TalkBack traversal order is logical and tested
- Navigation instructions dominate the visual hierarchy
- Icons always have text labels
- Color is never the sole indicator of state
- Font scaling to 2x is supported without clipping
- Vibration and voice guidance are configurable

---

## Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Navigation | Navigation Compose |
| Architecture | ViewModel + StateFlow |
| Build | Gradle Kotlin DSL |
| CI/CD | GitHub Actions |
| Min SDK | 26 (Android 8.0) |
| Target SDK | 34 |
| Compile SDK | 35 |

---

## Architecture

Clean architecture with clear separation:

```
ui/          -> Compose screens, components, theme
  screens/   -> Screen composables + ViewModels
  components/-> Reusable design-system components
  theme/     -> Material 3 theme (colors, typography, shapes)
  navigation/-> Nav graph and route definitions
data/        -> Domain models, mock data, repositories
  model/     -> Data classes
  mock/      -> Mock data providers (Kolkata stations)
  repository/-> Repository interfaces + mock implementations
accessibility/ -> Accessibility utilities
```

Unidirectional data flow: UI observes ViewModel state via `StateFlow`. ViewModels hold `MutableStateFlow<UiState>`.

---

## Mock Data

All transportation and accessibility information is **fictional demo data** based on Kolkata metro and railway stations. No real-time data is used. The application works entirely offline.

See [docs/MOCK_DATA.md](docs/MOCK_DATA.md) for details.

---

## Setup

### Prerequisites

- Android Studio Ladybug or later
- JDK 17
- Android SDK (platform 35)

### Clone

```bash
git clone https://github.com/peter14l/waysense-android.git
cd waysense-android
```

Open in Android Studio and sync Gradle.

---

## Running

### Local (Android Studio)

1. Open project in Android Studio
2. Sync Gradle
3. Run on emulator or device (API 26+)

### CI/CD (GitHub Actions)

All builds run in GitHub Actions. No local Gradle builds are executed.

- **Push to `main`**: validation build + artifact upload
- **Tag `v*`**: signed release build + GitHub Release with APKs

---

## GitHub Actions

See [docs/RELEASE_PROCESS.md](docs/RELEASE_PROCESS.md) for the full release workflow.

### Workflow

1. Checkout code
2. Setup JDK 17
3. Restore Gradle cache
4. Reconstruct signing keystore from GitHub Secrets
5. Run lint / static checks
6. Build signed APKs
7. Split by ABI (arm64-v8a, armeabi-v7a, x86_64)
8. Upload artifacts
9. Create GitHub Release (on tag)

### APK Installation

Download the appropriate APK from [GitHub Releases](https://github.com/peter14l/waysense-android/releases):

- `WaySense-arm64-v8a.apk` — Most modern devices
- `WaySense-armeabi-v7a.apk` — Older 32-bit devices
- `WaySense-x86_64.apk` — Emulators

Enable "Install from unknown sources" on your device if prompted.

---

## Security

- No API keys, passwords, or secrets are committed
- Signing keystore is stored as a base64-encoded GitHub Secret
- `.gitignore` excludes `*.jks`, `*.keystore`, `local.properties`, `.env`
- No network access is required for the demo

---

## Future Roadmap

See [docs/ROADMAP.md](docs/ROADMAP.md)

- Real-time transit API integration
- Actual GPS-based navigation
- Google Maps integration
- Machine learning for station recognition
- Multi-language support
- Voice command navigation
- Community-contributed accessibility data

---

## Known Limitations

- All data is mock/demo data
- No real-time transit information
- No actual GPS navigation
- Emergency features are simulated
- Map is a simplified Compose illustration, not a real map
- Onboarding completion is not persisted across launches

---

## Contributing

This is an academic prototype. Contributions are welcome for learning purposes.

---

## License

MIT License. See [LICENSE](LICENSE).

---

## Project Status

**Status: Academic prototype / demo**

This application does not provide real-time transportation information. All station data, accessibility features, and routing information are fictional and for demonstration purposes only.
