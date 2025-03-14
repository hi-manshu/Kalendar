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

## KalendarKonfig

Configuration options for the calendar’s behavior and style

```kotlin
data class KalendarKonfig(
    val kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    val kalendarHeaderKonfig: KalendarHeaderKonfig = KalendarHeaderKonfig.default(),
    val kalendarDayLabelKonfig: KalendarDayLabelKonfig = KalendarDayLabelKonfig.default(),
    val backgroundColor: KalendarColor = KalendarColor.Solid(Color.White),
)

```

### Properties:

- **kalendarDayKonfig** (`[KalendarDayKonfig](#kalendarDayKonfig)`): A config to update day style of
  calendar.
- **kalendarHeaderKonfig** (`[KalendarHeaderKonfig](#kalendarHeaderKonfig)`): A config to update day
  style of calendar.
- **kalendarDayLabelKonfig** (`[KalendarDayLabelKonfig](#kalendarDayLabelKonfig)`): A config to
  update day's label style of calendar.
- **backgroundColor** (`[KalendarColor](#kalendarColor)`): A color of
  type [KalendarColor](#kalendarColor)]

## KalendarDayKonfig

Configuration options for the calendar’s day behavior and style

```kotlin
data class KalendarDayKonfig(
    val size: Dp,
    val selectedTextColor: KalendarColor,
    val borderColor: KalendarColor,
    val indicatorColor: KalendarColor,
    val textStyle: TextStyle,
    val selectedBackgroundColor: KalendarColor,
)

```

### Properties:

- **size** (Dp): The size of the day element.
- **selectedTextColor** ([KalendarColor](#kalendarColor)): The color of the text when a day is
  selected.
- **borderColor** ([KalendarColor](#kalendarColor)): The color of the border around the day element.
- **indicatorColor** ([KalendarColor](#kalendarColor)): The color of the indicator for events.
- **textStyle** (TextStyle): The style of the text for the day element.
- **selectedBackgroundColor** ([KalendarColor](#kalendarColor)): The background color when a day is
  selected.

## KalendarHeaderKonfig

Configuration options for the calendar’s Header View behavior and style that represents Month name

```kotlin
data class KalendarHeaderKonfig(
    val textStyle: TextStyle,
    val centerAligned: Boolean
) 
```

### Properties:
- **textStyle** (TextStyle): The style of the text for the day element.
- **centerAligned** (Boolean): Aligns the header in center or no.

## KalendarDayLabelKonfig

Configuration options for the calendar’s day label behavior and style

```kotlin
data class KalendarDayLabelKonfig(
    val textStyle: TextStyle,
    val textCharCount: Int,
    val centerAligned: Boolean
)
```
### Properties:
- **textStyle** (TextStyle): The style of the text for the day element.
- **textCharCount** (Int): Lets you set the char count in day label.
- **centerAligned** (Boolean): Aligns the header in center or no.

## KalendarColor

Representation of a color in the Kalendar library. It can be a solid color or a gradient.