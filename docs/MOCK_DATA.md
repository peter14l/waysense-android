# Mock Data

## WaySense — Mock Data Documentation

---

## Important Notice

**All transportation and accessibility information in WaySense is fictional demo data.**

WaySense does not provide real-time transportation information. All station data, accessibility features, routing information, and disruption alerts are simulated for demonstration purposes.

---

## Data Sources

### Stations

WaySense includes 5 mock stations based on Kolkata's public transport network:

| Station | Type | Distance | Accessibility Score |
|---------|------|----------|-------------------|
| Park Street Metro | Metro | 850m | 4.6/5 |
| Esplanade Metro Station | Metro | 1.8km | 4.8/5 |
| Sealdah Station | Railway | 2.4km | 3.9/5 |
| Howrah Station | Railway | 4.2km | 3.5/5 |
| Salt Lake Sector V | Metro | 3.1km | 4.3/5 |

### Accessibility Features

Each station has fictional accessibility metadata:

- Step-free entrance
- Elevator
- Tactile paving
- Audio announcements
- Accessible restroom
- Braille signage

Some stations have "unavailable" features to demonstrate how the app communicates limitations.

### Routes

Routes between stations are simulated with:

- Transport mode (Metro, Bus, Walking, Mixed)
- Duration (fictional)
- Walking distance
- Number of transfers
- Accessibility rating (Excellent, Good, Fair)
- Step-by-step instructions

### Navigation Steps

Active journey navigation uses mock step-by-step instructions:

1. "Walk straight for 120 metres"
2. "Turn left toward Park Street Metro Gate 2"
3. "Enter Park Street Metro through Gate 2"
4. "Take Blue Line toward Dakshineswar"
5. "Exit at Esplanade via Exit 3 elevator"
6. "Walk to destination"

### Disruptions

A single mock disruption is included:

- "Elevator at Park Street Metro is temporarily unavailable"
- Alternative: "Use Gate 1 elevator instead"

---

## Using Real Data

To replace mock data with real data:

1. Replace mock data providers with API-backed repositories
2. Add network layer (Retrofit, Ktor)
3. Integrate with transit APIs (Google Directions, local transit authority APIs)
4. Integrate with mapping (Google Maps SDK)
5. Add real-time disruption feeds

The repository pattern makes this replacement straightforward without changing the UI layer.

---

## Verification Dates

Station accessibility data shows "Last verified: X days ago" — this is mock data and does not reflect actual verification.

---

## Disclaimer

This is an academic/demo prototype. Do not rely on WaySense for real navigation decisions.
