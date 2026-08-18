# Design Thinking

## WaySense — Accessible Public Transport Navigation

---

## 1. Empathize

### Who are we designing for?

Visually impaired commuters who use public transportation daily. They may be:

- Fully blind (relying entirely on screen readers and audio)
- Partially sighted (benefiting from high contrast and large text)
- Using assistive technology (TalkBack, voice guidance, vibration)

### What challenges do they face?

- **Station complexity**: Large stations with multiple gates, levels, and exits
- **Accessibility gaps**: Elevators out of service, broken tactile paving, missing signage
- **Route uncertainty**: Not knowing which route is most accessible before starting
- **Disruption blindness**: No way to get real-time alerts about accessibility changes
- **Dependency**: Having to ask others for directions or assistance

### Key insights from research:

1. Visually impaired commuters plan routes differently — accessibility is the primary filter, not speed
2. Step-by-step verbal guidance is more valuable than a map
3. Disruptions to accessibility features (broken elevators) cause the most stress
4. Confidence comes from knowing what to expect before and during a journey

---

## 2. Define

### Primary Problem Statement

**How might we help visually impaired commuters navigate public transport independently by providing accessible, step-by-step journey guidance with real-time disruption awareness?**

### Design Principles

1. **Accessibility first**: Every decision starts with the most impaired user
2. **Calm technology**: Reduce anxiety, don't add to it
3. **Clear hierarchy**: Current action > Next action > Distance > Warning > Context
4. **Graceful degradation**: Every feature works without sight

### Success Criteria

- User can complete a journey from search to destination without visual assistance
- User receives clear, large-text navigation instructions
- User is alerted to disruptions and given alternatives
- User can configure the app to match their specific accessibility needs

---

## 3. Ideate

### Concept Directions

1. **Turn-by-turn voice navigation** for station interiors
2. **Accessibility scoring** for stations and routes
3. **Proactive disruption alerts** with alternative suggestions
4. **Configurable guidance modes** (voice, vibration, visual)
5. **Simplified interface mode** reducing cognitive load

### Key Features Identified

| Feature | Priority | Rationale |
|---------|----------|-----------|
| Step-by-step navigation | P0 | Core value proposition |
| Station accessibility info | P0 | Enables informed decisions |
| Route accessibility rating | P0 | Primary decision factor |
| Disruption alerts with alternatives | P0 | Prevents stranded users |
| Configurable guidance | P1 | Personalization |
| Journey history | P2 | Convenience |
| Emergency assistance | P1 | Safety net |

---

## 4. Prototype

### WaySense Prototype

An Android application built with:

- **Kotlin + Jetpack Compose** for modern, accessible UI
- **Material 3** for consistent, high-contrast design
- **Mock data** simulating Kolkata metro and railway stations
- **Simulated navigation** demonstrating the complete user journey

### Prototype Scope

- Onboarding with accessibility preference selection
- Home screen with search and nearby stations
- Route planning with accessibility ratings
- Active journey with step-by-step instructions
- Disruption simulation with alternative routing
- Accessibility settings panel
- Help/emergency screen

### What's NOT in scope (by design)

- Real GPS or mapping
- Live transit API integration
- Backend authentication
- Production infrastructure

---

## 5. Test

### Usability Considerations

- Can a TalkBack user navigate from search to journey start?
- Are touch targets large enough for motor-impaired users?
- Does the typography hierarchy make navigation instructions obvious?
- Can the app be used at 2x font scaling without text clipping?
- Are disruptions communicated clearly and promptly?

### Validation Approach

- Source-code accessibility audit (content descriptions, semantics, touch targets)
- Contrast ratio verification
- Font scaling stress testing
- TalkBack navigation flow testing

### Known Limitations to Address

- Onboarding completion not persisted
- No real TTS integration (mocked)
- Map is decorative, not functional
- Emergency features are simulated only
