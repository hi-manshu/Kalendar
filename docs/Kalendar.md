## Kalendar

This is a calendar library that gives integration for either Week/Month view.

## Introduction

With Compose getting the attention, it was about time to have its own Calendar. Kalendar is all  
about it with the customization and design.

## Implementation

### Step: 01

To add Kalendar, add this dependency,

```gradle  
dependencies {  
  .....
  implementation("com.himanshoe:kalendar:1.1.0")
}  
```  

### Step: 02 (Usage)
To integrate Kalendar use the composable,

```kotlin  
 Kalendar(kalendarType = KalendarType.Oceanic)  
```  
Preview:
[Oceainc](art/Oceanic.png)

where `Oceanic` gives the WeekView of the current week or you can use,

```kotlin  
 Kalendar(kalendarType = KalendarType.Firey)  
```  
that will give you the Month view of the current month
Preview:
[Oceainc](art/Kalendar-Firey.png)

### Step: 03 (Customisation)
With Kalendar at your disposal you have customization at your tip to make it suited based on your need.

`Kalendar` composable has lot of optional parameters that you can use. Like,
> Setting up Events
```kotlin
kalendarEvents: List<KalendarEvent> = emptyList(),
```
Here, `KalendarEvent` can be used to pass events to mark specific days. `KalendarEvent` looks like,
```kotlin
data class KalendarEvent(  
 val date: LocalDate,  
 val eventName: String,  
 val eventDescription: String? = null,  
)
```


> Setting up KalendarThemeColors
```kotlin
kalendarThemeColors: List<KalendarThemeColor> = KalendarColors.defaultColors(),
```
Here, `KalendarThemeColor` list can be used to pass the basic theming colors that will be common for all the Views.
PS: I would say check the `defaultColors` once as well!

`KalendarThemeColor` looks like,
```kotlin
data class KalendarThemeColor(  
 val backgroundColor: Color,  
 val dayBackgroundColor: Color,  
 val headerTextColor: Color,  
)
```

Alternatively,
if you dont want each month having its own color set, you can use,
```kotlin
kalendarThemeColor: KalendarThemeColor = //Your color,
```

> Individual Date Click Listener
```kotlin
onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
```
You can use this lambda to get values of the specific day that is in format of,
```kotlin
class KalendarDay(val localDate: LocalDate)
```
and the list of events for that particular day.

> Setting up Colors for Individual Day
```kotlin
kalendarDayColors: KalendarDayColors = KalendarDayDefaultColors.defaultColors()
```
This will have list of two colors,
* First one for default color
* Second one for the color it will come when it is selected.

> Navigate to Date
``` kotlin
 takeMeToDate: LocalDate?
 ```
It will make the date the first day to be displayed in the view.
