## Kalendar Endlos Documentation

<div align="start">
  <img src="../img/endlos.png" alt="endlos" width="30%" />
</div>


The Kalendar function is a composable function in the Kalendar library that allows you to create a
customizable calendar component in your Android app using Jetpack Compose. 

```kotlin
@Composable
fun Kalendar(
  modifier: Modifier = Modifier,
  showLabel: Boolean = true,
  pagingController: KalendarPagingController = rememberKalendarPagingController(),
  kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
  kalendarColors: KalendarColors = KalendarColors.default(),
  events: KalendarEvents = KalendarEvents(),
  kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
  contentPadding: PaddingValues = PaddingValues(8.dp),
  monthContentPadding: PaddingValues = PaddingValues(4.dp),
  dayContent: (@Composable (LocalDate) -> Unit)? = null,
  weekValueContent: (@Composable () -> Unit)? = null,
  headerContent: (@Composable (Month, Int) -> Unit)? = null,
  daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
  onDayClicked: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
  onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
  onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
)
```

#### Parameters

- `currentDay`: The current selected day in the calendar. Pass null if no specific day is selected.
- `kalendarType`: The type of calendar to display. It can be one of KalendarType.Oceanic or
  KalendarType.Firey.
- `pagingController`: Paging controller for managing the navigation and scrolling of the calendar.
- `modifier`: Optional modifier for styling or positioning the calendar component.
- `showLabel`: Determines whether to show the labels for days and months in the calendar.
- `events`: The collection of calendar events to be displayed in the calendar.
- `kalendarHeaderTextKonfig`: Configuration for styling the header text in the calendar.
- `kalendarColors`: Configuration for customizing the colors of the calendar components.
- `kalendarDayKonfig`: Configuration for customizing the appearance of individual day cells in the
  calendar.
- `weekValueContent`: Composable function for customizing the appearance of the week value displayed in the header.
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

### Usage

The Kalendar function is responsible for rendering the appropriate calendar based on the specified
kalendarType. It dynamically selects either KalendarOceanic or KalendarFirey composable based on the
type.

Here's an example usage of the Kalendar function:

```kotlin
Kalendar(
  modifier = Modifier.fillMaxSize(),
  showLabel = true,
  pagingController = rememberKalendarPagingController(),
  kalendarHeaderTextKonfig = KalendarTextKonfig(color = Color.Black),
  kalendarColors = KalendarColors(
    primary = Color.Blue,
    primaryVariant = Color.DarkBlue,
    secondary = Color.Gray,
    background = Color.White
  ),
  events = KalendarEvents(),
  kalendarDayKonfig = KalendarDayKonfig(
    textSize = 16.sp,
    selectedBackgroundColor = Color.Yellow
  ),
  contentPadding = PaddingValues(16.dp),
  monthContentPadding = PaddingValues(8.dp),
  dayContent = { date ->
    Text(
      text = date.dayOfMonth.toString(),
      fontSize = 16.sp,
      color = Color.Black
    )
  },
  headerContent = { month, year ->
    Text(
      text = "${month.name}, $year",
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
      color = Color.Black
    )
  },
  daySelectionMode = DaySelectionMode.Range,
  onDayClicked = { date, events ->
    // Handle day click event
  },
  onRangeSelected = { range, events ->
    // Handle range selection event
  },
  onErrorRangeSelected = { error ->
    // Handle range selection error
  }
)

```


In this example, we create a Kalendar component with various customization options. 
- We set the `modifier` to Modifier.fillMaxSize() to make the calendar occupy the available space. 
- The `showLabel` parameter is set to true to display the labels for days of the week.
- We provide a `pagingController` obtained using the `rememberKalendarPagingController()` function to manage the navigation and scrolling of the calendar. 
- The `kalendarHeaderTextKonfig` allows us to customize the appearance of the header text.
- We customize the colors of the calendar using the `kalendarColors` parameter. 
- The `events` parameter contains a map of dates and their corresponding events to be displayed on the calendar.
- We configure the appearance of individual days using the `kalendarDayKonfig` parameter. In this example, we set the `textSize` to 16.sp and the `selectedBackgroundColor` to Color.Yellow.
- The `contentPadding` and `monthContentPadding` parameters define the padding values for the calendar content and each month, respectively.
- We customize the appearance of each day using the `dayContent` composable function, which displays the day of the month as text.
- The `headerContent` composable function is used to customize the appearance of the calendar header, which displays the month and year.
- The `daySelectionMode` is set to `DaySelectionMode.Range` to enable range selection of days in the calendar.
- We provide callback functions for handling user interactions. The `onDayClicked` callback is triggered when a day is clicked, providing the clicked date and the list of events on that date. The `onRangeSelected` callback is triggered when a range of days is selected, providing the selected range and the list of events within that range. The `onErrorRangeSelected` callback is triggered when an error occurs during range selection.

Please note that this is just an example, and you can customize the `Kalendar` component based on your needs by modifying the provided parameters and callbacks.

Make sure to import the necessary dependencies and use the latest version of the Kalendar library in your project.

For getting started, refer to the [Kalendar documentation](https://github.com/himanshoe/Kalendar).

