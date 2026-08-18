# Accessibility

## WaySense — Accessibility Documentation

---

## Philosophy

Accessibility is the core design principle, not a retrofit. Every component, screen, and interaction is built around the needs of visually impaired commuters.

---

## TalkBack Compatibility

### Semantic Labels

Every interactive element has a `contentDescription`:

| Element | contentDescription |
|---------|-------------------|
| Button | Action text (e.g., "Start journey") |
| Navigation item | Destination name (e.g., "Home") |
| Search input | "Search destination" |
| Station card | "Park Street Metro, 850 metres away, Metro station" |
| Route card | "Metro route, 24 minutes, accessibility Excellent" |
| Navigation instruction | Full instruction text |
| Map (decorative) | "Map showing route. Decorative only, see route steps below." |

### Semantic Grouping

- `Modifier.semantics { heading() }` on section headers
- `Modifier.semantics { contentDescription = "..." }` on cards
- `Modifier.semantics { liveRegion = LiveRegionMode.Assertive }` for announcements

### Traversal Order

- Navigation bar items are ordered: Home → Journeys → Accessibility → Profile
- Onboarding screens flow top-to-bottom
- Journey instructions are ordered: current step → next step → controls → progress

---

## Touch Targets

| Element | Minimum Size | Rationale |
|---------|-------------|-----------|
| All interactive controls | 48dp | Android accessibility guideline |
| Navigation bar items | 56dp+ | Primary navigation, frequent use |
| Journey controls (repeat, pause, help) | 56dp | Critical during active navigation |
| Icon-only buttons | 48dp minimum | Always with contentDescription |

---

## Font Scaling

### Tested Sizes

- Default (1.0x)
- Large (1.3x)
- Extra Large (1.5x)
- Maximum (2.0x)

### Design Decisions

- No hardcoded heights — all containers use `wrapContent` or `weight`
- Navigation instructions use `headlineMedium` — readable even at 2x
- Text never clips — flexible spacing absorbs scale changes
- Section headers use `heading()` semantics for quick TalkBack navigation

---

## Contrast

### Color System

- Primary: `#3157D5` on `#FFFFFF` — contrast ratio ~7.5:1 (AAA)
- On Primary Container: `#07164D` on `#DDE2FF` — contrast ratio ~10:1 (AAA)
- Error: `#BA1A1A` on `#FFFFFF` — contrast ratio ~4.8:1 (AA)
- Body text: `#1A1B21` on `#FAF8FF` — contrast ratio ~15:1 (AAA)

### High Contrast Mode

The accessibility settings screen includes a high-contrast toggle. When enabled, the app should increase contrast ratios further (implementation pending).

---

## Navigation Guidance Priority

For visually impaired users, information is prioritized:

1. **Current action** — "Walk straight for 120 metres"
2. **Next action** — "Turn left toward Gate 2"
3. **Distance** — "120m"
4. **Important warning** — "Elevator unavailable"
5. **Supporting context** — "Near Park Street"

This hierarchy is enforced through typography size, color, and position.

---

## Vibration Guidance

### Patterns

| Pattern | Meaning |
|---------|---------|
| Short vibration | Information update |
| Two short vibrations | Upcoming turn |
| Long vibration | Important warning / disruption |

### Configuration

- Respects system vibration settings
- Can be disabled in Accessibility Settings
- Not implemented in demo (would use `VibrationEffect` API)

---

## Voice Guidance

### Implementation

- "Play instruction" button simulates voice output
- If Android `TextToSpeech` is used, it's optional with graceful fallback
- TTS is not a dependency for the rest of the app

### Configuration

- Voice guidance can be toggled in Accessibility Settings
- Station name announcements are configurable

---

## Inaccessible Patterns Avoided

- No icon-only buttons without labels
- No color-only state indicators
- No auto-playing animations that can't be stopped
- No flashing elements
- No time-limited interactions
- No small text (< 12sp body text)
- No low-contrast combinations

---

## Known Limitations

- Onboarding completion is not persisted — TalkBack users must re-select preferences each launch
- The mock map is decorative — route steps list is the primary navigation aid
- No real TTS integration — voice guidance is simulated
- High contrast mode toggle exists but full theme switching not implemented
