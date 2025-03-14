## Kalendar Type:

- **Oceanic**: A majestic spell that conjures the MonthView..
- **Firey**: A fiery enchantment that reveals the WeekView.
- **Solaris**: A charm that lets you swipe through the calendar in MonthView, as if by magic.
- **Aerial**: A spell that grants you the power to swipe through the calendar in WeekView, with the
  flick of a wand.

## KalendarEvent

Represents an individual event tied to a specific date.

```kotlin
data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null
)
```

### Properties:

- **date** (`LocalDate`): The date of the event.
- **eventName** (`String`): The name of the event.
- **eventDescription** (`String?`): An optional description of the event (default: `null`).

---

## KalendarEvents

A collection of multiple calendar [events](#kalendarEvent).
```kotlin

data class KalendarEvents(
    val eventList: List<KalendarEvent> = emptyList()
)
```

### Properties:

- **eventList** (`List<KalendarEvent>`): A list of events (default: an empty list).

