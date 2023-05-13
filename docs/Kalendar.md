## Kalendar Documentation

<div align="start">
  <img src="../img/kalendar-firey.png" alt="firey" width="30%" />
  <img src="../img/oceanic.png" alt="oceanic" width="30%" />
</div>

The Kalendar function is a composable function in the Kalendar library that allows you to create a
customizable calendar component in your Android app using Jetpack Compose.

```kotlin
@Composable
fun Kalendar(
    currentDay: LocalDate?,
    kalendarType: KalendarType,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    events: KalendarEvents = KalendarEvents(),
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
)
```

#### Parameters

- `currentDay`: The current selected day in the calendar. Pass null if no specific day is selected.
- `kalendarType`: The type of calendar to display. It can be one of KalendarType.Oceanic or
  KalendarType.Firey.
- `modifier`: Optional modifier for styling or positioning the calendar component.
- `showLabel`: Determines whether to show the labels for days and months in the calendar.
- `events`: The collection of calendar events to be displayed in the calendar.
- `kalendarHeaderTextKonfig`: Configuration for styling the header text in the calendar.
- `kalendarColors`: Configuration for customizing the colors of the calendar components.
- `kalendarDayKonfig`: Configuration for customizing the appearance of individual day cells in the
  calendar.
- `daySelectionMode`: The mode for selecting days in the calendar. It can be DaySelectionMode.Single
  or DaySelectionMode.Range.
- `dayContent`: Optional composable function for customizing the content of individual day cells in
  the calendar.
- `headerContent`: Optional composable function for customizing the content of the header in the
  calendar.
- `onDayClick`: Callback function triggered when a day is clicked in the calendar.
- `onRangeSelected`: Callback function triggered when a range of days is selected in the calendar.
- `onErrorRangeSelected`: Callback function triggered when an invalid range of days is selected in the
  calendar.

Here,

### KalendarEvents
The `KalendarEvents` data class represents a collection of KalendarEvent objects. It is used to store and manage a list of events for a specific calendar.

#### Properties`
`events` : A list of KalendarEvent objects representing the events associated with the calendar. By default, it is initialized as an empty list.

### KalendarTextKonfig
The KalendarTextKonfig data class represents the configuration options for the text appearance in the Kalendar library.

#### Properties
`kalendarTextColor`: The color of the text in the calendar. It is of type Color.
`kalendarTextSize`: The size of the text in the calendar. It is of type TextUnit.


### KalendarDayKonfig
The KalendarDayKonfig data class represents the configuration options for the appearance of a day in the Kalendar library.

#### Properties
`size`: The size of the day view. It is of type Dp.
`textSize`: The size of the text representing the day. It is of type TextUnit.
`textColor`: The color of the text representing the day. It is of type Color.
`selectedTextColor`: The color of the text representing the selected day. It is of type Color.
`borderColor`: The color of the border surrounding the day. It is of type Color and is set to `Color.Black` by default.



### Usage

The Kalendar function is responsible for rendering the appropriate calendar based on the specified
kalendarType. It dynamically selects either KalendarOceanic or KalendarFirey composable based on the
type.

Here's an example usage of the Kalendar function:

```kotlin
Kalendar(
  currentDay = today,
    kalendarType = KalendarType.Firey,
    modifier = Modifier,
    showLabel = true,
    events = KalendarEvents(),
    kalendarHeaderTextKonfig = null,
    kalendarColors = KalendarColors.default(),
    kalendarDayKonfig = KalendarDayKonfig.default(),
    daySelectionMode = DaySelectionMode.Single,
    dayContent = null,
    headerContent = null,
    onDayClick = { selectedDay, events ->
        // Handle day click event
    },
    onRangeSelected = { selectedRange, events ->
        // Handle range selection event
    },
    onErrorRangeSelected = { error ->
        // Handle error
    })
```


In the example above, we create a `Kalendar` component with the following configurations:
- `currentDay` is set to the current date.
- `kalendarType` is set to `KalendarType.Firey` to display a month view.
- We pass the `modifier` as `Modifier` to apply any additional styling or positioning.
- `showLabel` is set to `true` to display the labels for days and months.
- `events` is set to an empty `KalendarEvents` object.
- `kalendarHeaderTextKonfig` is set to `null` to use the default header text styling.
- `kalendarColors` is set to the default color configuration.
- `kalendarDayKonfig` is set to the default day cell configuration.
- `daySelectionMode` is set to `DaySelectionMode.Single` to allow selecting a single day.
- `dayContent` is set to `null` to use the default day cell content.
- `headerContent` is set to `null` to use the default header content.
- `onDayClick` is a callback function that handles the day click event and receives the selected day and associated events as parameters.
- `onRangeSelected` is a callback function that handles the range selection event and receives the selected range and associated events as parameters.
- `onErrorRangeSelected` is a callback function that handles the event when an invalid range selection is made.

Feel free to adjust the configurations based on your specific requirements.

Please note that this is just an example, and you can customize the `Kalendar` component based on your needs by modifying the provided parameters and callbacks.

Make sure to import the necessary dependencies and use the latest version of the Kalendar library in your project.

For getting started, refer to the [Kalendar documentation](https://github.com/himanshoe/Kalendar).

