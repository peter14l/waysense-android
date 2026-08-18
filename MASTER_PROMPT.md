# WAYSense - Master Engineering Prompt

## Accessible Public Transport Navigation Assistant for Visually Impaired Commuters

You are an autonomous senior Android engineering + UI/UX + accessibility team.

Build a polished, production-quality **Android demo application** called **WaySense**.

WaySense is an accessibility-first public transport navigation assistant designed primarily for **visually impaired commuters**.

This is a **Design Thinking / academic prototype**, not a production transportation service. The application must therefore use realistic mock data and simulated interactions instead of implementing real transportation APIs, live GPS, real routing, authentication backends, or production infrastructure.

The goal is to produce a visually polished, technically credible Android prototype that demonstrates how technology could help a visually impaired person independently navigate public transportation.

---

## ENVIRONMENT

- Working directory: `D:\WAYsense`
- Git: installed (2.54.0)
- Java: OpenJDK 17 (Temurin)
- Android SDK: `C:\AndroidSdk` (platforms: android-34, android-36; build-tools: 34.0.0, 35.0.0, 36.0.0)
- GitHub CLI: `gh` (2.94.0), authenticated as `peter14l`
- OS: Windows 11
- Shell: PowerShell 7+

---

## CRITICAL CONSTRAINTS

1. **DO NOT run any local Gradle build.** No `./gradlew build`, `./gradlew assembleDebug`, `./gradlew assembleRelease`, `./gradlew test`, or equivalent. All builds happen in GitHub Actions.
2. **DO NOT commit keystore, passwords, secrets, API keys, tokens, or `local.properties`** into Git.
3. **DO NOT implement real backend, GPS, maps API, live transport APIs, authentication, or network-dependent features.**
4. **DO use** Kotlin, Jetpack Compose, Material 3, AndroidX, Navigation Compose, Coroutines where useful.
5. **DO NOT use** Flutter, React Native, Java (for UI), XML layouts, Firebase, Supabase, Node.js, Python, or web wrappers.

---

## 1. CORE OBJECTIVE

Build a native Android application demonstrating this conceptual journey:

1. User opens WaySense
2. User completes accessibility-focused onboarding
3. User reaches main dashboard
4. User searches for a destination
5. User views simulated accessible public-transport route
6. User inspects station accessibility information
7. User enters simulated navigation mode
8. App communicates via accessible UI, TalkBack-compatible semantics, large touch targets, vibration, strong visual hierarchy
9. User inspects journey status
10. User simulates transport disruptions
11. User accesses emergency/help
12. User modifies accessibility preferences

Everything is mocked. No real APIs. No network required.

---

## 2. TECHNOLOGY STACK

### Use
- Kotlin
- Android SDK (target API 34, compile SDK 36)
- Jetpack Compose (BOM)
- Material 3 (Material You)
- Material 3 Expressive APIs where available in stable versions
- AndroidX libraries
- Kotlin Coroutines
- Navigation Compose
- Android accessibility APIs

### Do Not Use
- Flutter, React Native, Java (UI), XML layouts (except where platform integration demands)
- Compose Multiplatform, KMP, Swift
- Firebase, Supabase, Node.js, Python, web wrappers
- Google Maps SDK (mock the map in Compose)

### Build System
- Gradle Kotlin DSL
- Android Gradle Plugin 8.7+ (latest compatible with your toolchain)
- Kotlin 2.0+ (latest stable compatible)
- Centralized dependency versions via `gradle/libs.versions.toml`

---

## 3. PROJECT ARCHITECTURE

```
app/
  src/
    main/
      java/com/waysense/
        ui/
          navigation/        # Nav graph, route definitions
          screens/           # Screen composables
          components/        # Reusable UI components
          theme/             # Material 3 theme (color, type, shapes)
        data/
          model/             # Domain data classes
          mock/              # Mock data providers
          repository/        # Repository interfaces + mock impls
        accessibility/       # Accessibility utilities, semantics helpers
        utils/               # General utilities
      res/
        values/
        drawable/
        raw/                 # Any audio assets if needed
```

### Principles
- Immutable UI state
- StateFlow for state management
- ViewModel per screen
- Unidirectional data flow
- Composable screen components
- Reusable design-system components
- Clear separation: UI / state / data / navigation / accessibility

---

## 4. APPLICATION IDENTITY

- **Name**: WaySense
- **Package**: `com.waysense.app`
- **Tagline**: "Navigate independently."
- **Secondary**: "Accessible journeys, one step at a time."
- **Feel**: calm, trustworthy, modern, accessible, reassuring, precise, minimal, premium
- **Not**: childish, overly medical, disability-management

---

## 5. VISUAL DESIGN (Material 3 Expressive)

- Dynamic-feeling color relationships
- Expressive typography
- Generous spacing
- Rounded surfaces
- Meaningful elevation
- Strong visual hierarchy
- Large interactive components
- Subtle motion
- Accessible contrast
- Clear iconography
- Restrained gradients

### Avoid
- Excessive glassmorphism/gradients
- Tiny text, dense dashboards
- Decorative noise
- Low-contrast text
- Icons without labels

---

## 6. COLOR SYSTEM

### Light Theme
| Role | Hex |
|------|-----|
| Primary | `#3157D5` |
| On Primary | `#FFFFFF` |
| Primary Container | `#DDE2FF` |
| On Primary Container | `#07164D` |
| Secondary | `#4E5F92` |
| Secondary Container | `#DEE5FF` |
| Tertiary | `#5E5A7D` |
| Background | `#FAF8FF` |
| Surface | `#FAF8FF` |
| Error | `#BA1A1A` |

### Dark Theme
Generate complementary dark variants. If Material 3 dynamic color or tonal palettes produce better results, prefer those.

Ensure WCAG-oriented contrast at all times.

Must remain usable with: high contrast, large font scaling, TalkBack, reduced motion.

---

## 7. TYPOGRAPHY

Deliberate hierarchy using Material 3 roles:
- **Display** — sparingly
- **Headline** — major navigation instructions
- **Title** — sections
- **Body** — supporting information
- **Label** — controls

Navigation instructions must visually dominate all secondary information.

---

## 8. ACCESSIBILITY (PRIMARY FEATURE)

Accessibility is NOT a polish pass. The entire app is designed around it.

### Target users
- Fully blind / partially sighted
- TalkBack users
- Enlarged text users
- High-contrast settings
- Voice interaction
- Vibration feedback

### Requirements
- Every interactive element needs appropriate semantics
- `contentDescription` on all buttons, navigation items, images
- Semantic grouping via `Modifier.semantics`
- `stateDescription`, `role`, `heading` semantics where appropriate
- Traversal ordering for logical TalkBack navigation
- No redundant descriptions where visible text suffices
- No icon-only controls without labels

### Examples
```
Button: contentDescription = "Start accessible journey"
Nav item: contentDescription = "Home"
Map area: contentDescription = "Map showing your current route from Park Street to Esplanade"
Instruction: contentDescription = "Turn left in 20 metres"
```

---

## 9. TOUCH TARGETS

- Minimum practical touch target: ~48dp
- Navigation controls: 56dp+
- No tiny icon-only buttons unless justified
- Icon-only buttons MUST have `contentDescription`

---

## 10. FONT SCALING

UI must remain usable at:
- Default font size
- 1.3x
- 1.5x
- 2.0x

No hardcoded heights causing text clipping. Prefer `wrapContent`, adaptive layouts, flexible spacing.

---

## 11. SCREENS

### Onboarding (4 screens)
1. Logo + "Navigate independently." + "Get started"
2. "How do you navigate?" — multi-select: TalkBack, Large text, High contrast, Voice guidance, Vibration guidance
3. "Choose your guidance" — Voice, Vibration, Visual (default: Voice + Vibration)
4. "You're ready." + "Enter WaySense"

### Main Navigation
Material 3 bottom navigation bar:
- Home
- Journeys
- Accessibility
- Profile

Must be TalkBack-compatible, clearly labeled, 56dp+, visually distinct, no color-only selection indicators.

### Home
- Greeting: "Good evening."
- "Where are you going?" + search field
- Quick destinations: Home, College, Work, Recent
- "Accessible nearby" cards with distance, accessibility features, transport type

### Search
- Full search: text input, clear, search icon, accessible label, keyboard nav
- Mock results with distance and type
- Selection opens route planning

### Route Planning
- Destination, origin, route options
- Cards: Metro (24 min, 450m walk, 1 transfer, Excellent) vs Bus (31 min, 250m walk, 0 transfers, Good)
- Highlighted "Most accessible" route

### Map / Route Overview
- Mock map in Compose (simplified roads, stations, position, destination, route line)
- Always provide list-based "Route steps" alternative
- Visually impaired user must understand journey without the map

### Station Accessibility Details
- Station name, accessibility score (4.6/5)
- Available features (checkmarks), unavailable features (crosses)
- "Last verified 2 days ago" (mock)

### Active Journey (CENTERPIECE)
- Large instruction: "Walk straight"
- Next instruction: "Turn left in 20 metres"
- Journey progress: Walking 450m → Metro 18min → Destination Esplanade
- Controls: Repeat instruction, Pause guidance, End journey, Help
- Repeat instruction button is critical for accessibility

### Voice Guidance (Mock)
- "Play instruction" button
- Simulates spoken instruction
- If Android TTS used, must be optional and graceful fallback
- Not a dependency for the rest of the app

### Vibration Guidance (Mock)
- Short vibration = information
- Two short = upcoming turn
- Long = important warning
- Respect system settings, provide disable toggle

### Disruption Simulation
- "Journey update" alert
- "Elevator at Park Street Metro is temporarily unavailable."
- Alternative route suggestion
- "View alternative" button

### Journeys
- Active journey, recent journeys, saved destinations
- Tappable for summary

### Accessibility Settings
- Voice guidance ON/OFF
- Vibration guidance ON/OFF
- High contrast ON/OFF
- Large text system default
- Simplified interface ON/OFF
- Announce station names ON/OFF
- Repeat important alerts ON/OFF
- Accessibility profile: TalkBack detected

### Profile
- User: "Alex", preferences, saved places, history, About, Privacy, Feedback, Version

### Help / Emergency
- Repeat instruction, Where am I, Find nearest accessible exit, Contact station assistance, End journey
- Emergency is explicitly demo. No automatic real emergency calls.

### Empty States
- "No journeys yet." / "No saved places yet." with supportive copy

### Error States
- Search failed, route unavailable, accessibility info unavailable, disruption, map unavailable
- Never raw exceptions

### Loading States
- Material 3 loading indicators where they improve the narrative
- No unnecessary artificial loading

---

## 12. DESIGN SYSTEM COMPONENTS

Create reusable composables:
- `WaySensePrimaryButton`
- `WaySenseSecondaryButton`
- `WaySenseSearchBar`
- `WaySenseDestinationCard`
- `WaySenseRouteCard`
- `WaySenseAccessibilityBadge`
- `WaySenseNavigationInstruction`
- `WaySenseStatusCard`
- `WaySenseStationCard`
- `WaySenseSectionHeader`
- `WaySenseIconButton`
- `WaySenseBottomSheet`
- `WaySenseEmptyState`

Centralize: dimensions, typography, shapes, color scheme, spacing, component styling.

---

## 13. MOTION

- Subtle Compose animations: onboarding transitions, route card selection, instruction changes, bottom-sheet appearance, state changes
- NO excessive parallax, constant movement, distracting animations, flashing
- Respect reduced-motion where practical

---

## 14. MOCK DATA

- Dedicated mock data layer
- Locations: Kolkata
- Stations: Park Street, Esplanade, Sealdah, Howrah, Salt Lake Sector V
- Fictional accessibility metadata
- Display "Demo data" where appropriate
- All data works offline

---

## 15. LANGUAGE

- "Accessible journey", not "help disabled people"
- "Visually impaired commuters", not "special users"
- "Accessibility preferences", not "disability settings"
- "Step-free route", "Audio guidance", "Independent navigation"

---

## 16. UX DETAILS

- Bottom sheets where appropriate
- Cards for grouped info
- Chips sparingly
- Large buttons for primary actions
- Clear section headers
- No long paragraphs
- Priority order for visually impaired: 1) Current action, 2) Next action, 3) Distance, 4) Warning, 5) Supporting info

---

## 17. NO REAL BACKEND

Must work entirely offline with local mock data. No API keys, no Maps keys, no Firebase, no auth, no network. Architecture should make adding real APIs possible later.

---

## 18. README.md

Include:
- Project overview, problem statement, Design Thinking context
- Target users, core hypothesis
- Features list
- Screenshots placeholders
- Accessibility philosophy
- Tech stack, architecture, project structure
- Mock-data explanation
- Setup, running, GitHub Actions, release process
- APK installation
- Security considerations
- Future roadmap
- Known limitations
- Contributing, License
- Status: "Academic prototype / demo"

---

## 19. DOCUMENTATION (docs/)

- `DESIGN_THINKING.md` — Empathize, Define, Ideate, Prototype, Test (with problem statement)
- `ARCHITECTURE.md` — Kotlin/Compose architecture explanation
- `ACCESSIBILITY.md` — TalkBack, semantic labels, touch targets, font scaling, contrast, navigation, vibration, voice
- `MOCK_DATA.md` — Clearly states data is fictional/demo
- `RELEASE_PROCESS.md` — GitHub Actions signing and releases
- `ROADMAP.md` — Future possibilities

---

## 20. GITHUB ACTIONS (.github/workflows/android.yml)

### Trigger Strategy
- Push to `main`: validate + build + upload artifacts
- Tag `v*`: build release + sign + create GitHub Release + attach APKs

### Workflow Steps
1. Checkout
2. Setup JDK 17
3. Setup Android SDK (if needed)
4. Configure Gradle
5. Restore Gradle cache
6. Reconstruct signing keystore from GitHub Secrets
7. Run lint / static checks
8. Build signed APKs
9. Split APKs by ABI (arm64-v8a, armeabi-v7a, x86_64)
10. Upload artifacts
11. Create/update GitHub Release (on tag)

### Signing
- Generate keystore ONCE locally
- Base64 encode it
- Store via `gh secret set`:
  - `KEYSTORE_BASE64`
  - `KEYSTORE_PASSWORD`
  - `KEY_ALIAS`
  - `KEY_PASSWORD`
- Workflow reconstructs `waysense-release.jks` temporarily
- Delete after build where appropriate
- NEVER commit keystore or secrets

### Artifacts
- `WaySense-arm64-v8a.apk`
- `WaySense-armmeabi-v7a.apk`
- `WaySense-x86_64.apk`

### Release
- Semantic versioning (v0.1.0, v0.1.1, v0.2.0, v1.0.0)
- Include: version, changes, known limitations, installation, demo disclaimer
- Do NOT create hundreds of releases accidentally

### CI Quality
- Fail clearly on Gradle, Kotlin, lint, signing, keystore, or APK failures
- Use `set -e` in shell scripts
- No `|| true` to hide failures

---

## 21. GIT REPOSITORY

- Public GitHub repo: `waysense-android`
- Description: "Accessibility-first Android public transport navigation prototype for visually impaired commuters."
- Topics: android, kotlin, jetpack-compose, material3, accessibility, visually-impaired, public-transport, navigation, design-thinking, student-project
- Proper `.gitignore`: `*.jks`, `*.keystore`, `local.properties`, `.env`, `*.pem`, `*.p12`, build outputs
- Meaningful commit messages:
  - `feat: create accessible onboarding flow`
  - `feat: add route planning prototype`
  - `feat: implement accessibility settings`
  - `docs: add project documentation`
- MIT License

---

## 22. ACCESSIBILITY QA CHECKLIST

Before declaring complete, inspect ALL source code for:
- Missing `contentDescription`
- Missing semantics
- Insufficient touch targets
- Text clipping
- Poor contrast
- Inaccessible dialogs/bottom sheets
- Confusing navigation order
- Redundant TalkBack announcements
- Icon-only controls without labels
- State changes not announced

---

## 23. FINAL VALIDATION

Inspect WITHOUT running local builds:
- Project compilation configuration
- Gradle configuration
- Kotlin source correctness
- Compose UI correctness
- Navigation graph
- Accessibility semantics
- README
- Documentation
- GitHub workflow
- Signing configuration
- `.gitignore`
- Repository metadata

Rely on GitHub Actions for actual compilation verification.

---

## 24. WORK EXECUTION PHASES

| Phase | Description |
|-------|-------------|
| 1 | Inspect environment and repository |
| 2 | Initialize Android project (Gradle, manifests, dependencies) |
| 3 | Create design system (theme, colors, typography, components) |
| 4 | Create navigation architecture |
| 5 | Create mock data layer |
| 6 | Build onboarding screens |
| 7 | Build Home + Search |
| 8 | Build route planning |
| 9 | Build map and route steps |
| 10 | Build active journey |
| 11 | Build disruption simulation |
| 12 | Build journeys, profile, settings, help |
| 13 | Accessibility source-code review |
| 14 | Create documentation |
| 15 | Create GitHub Actions workflow |
| 16 | Generate signing configuration |
| 17 | Configure GitHub secrets via gh |
| 18 | Push repository |
| 19 | Trigger CI/CD |
| 20 | Inspect GitHub Actions results |

**CRITICAL: Do NOT perform local Gradle builds at any phase.**

---

## 25. AGENT BEHAVIOR

- Do not ask unnecessary questions
- Make reasonable engineering decisions
- If a dependency/version is incompatible, choose a compatible modern alternative
- If Material 3 Expressive APIs are unavailable in the chosen stable version, use closest stable Material 3 implementation
- Do not use unstable APIs unnecessarily
- Prefer stable Android APIs
- Do not add dependencies merely because they are fashionable
- Keep the project maintainable

---

## 26. FINAL DELIVERABLE

The GitHub repository must contain:

- Polished Kotlin Android application
- Coherent accessible UX
- Material 3 Expressive-inspired design system
- Onboarding, main navigation, search
- Accessible route planning
- Mock map
- Station accessibility information
- Active navigation simulation
- Disruption handling
- Accessibility settings
- Journey history, profile, help
- Mock data layer
- Comprehensive README
- Architecture, Design Thinking, Accessibility, Mock Data, Release, Roadmap documentation
- GitHub Actions CI/CD
- Signed ABI-split APK generation
- GitHub Releases
- Proper public GitHub repository
- No secrets committed
- No local builds executed

The result must look like a serious early-stage product prototype that could evolve into a real accessibility-focused transportation platform.
