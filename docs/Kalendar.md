# Kalendar

A calendar library *Week* or *Month* views.

## Introduction

Kalendar is a Calendar component designed to integrate directly with Jetpack Compose. Kalendar is all about customization and design.

## Implementation

### 1: Add Dependency

To add Kalendar, add [this dependency](https://search.maven.org/artifact/com.himanshoe/kalendar),

```gradle  
dependencies {  
  .....
  implementation("com.himanshoe:kalendar:1.1.0")
}  
```  

### 2: Usage

Add the `Kalendar` composable to your code:

```kotlin  
Kalendar(kalendarType = KalendarType.Oceanic)  
```  

Preview:
<img src= "/art/oceanic.png" data-canonical-src="/art/oceanic.png" width="200" height="400" />

In this example `Oceanic` sets the *Week* view, or

```kotlin  
Kalendar(kalendarType = KalendarType.Firey)  
```  

will set the `Month` view

Preview:
<img src= "/art/kalendar-firey.png" data-canonical-src="/art/kalendar-firey.png" width="200" height="400" />

### 3: Customization

It is simple to customize Kalendar to suit your needs. There are many optional parameters you can use.

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

Here, `KalendarThemeColor` list is used to pass the common theming colors that will be used for all the Views.

Note: Check the `defaultColors` used

> `KalendarThemeColor` looks like,

```kotlin
data class KalendarThemeColor(  
 val backgroundColor: Color,  
 val dayBackgroundColor: Color,  
 val headerTextColor: Color,  
)
```

Alternatively, if you dont want each month having its own color set, you can use:

```kotlin
kalendarThemeColor: KalendarThemeColor = <your color>,
```

> Individual Date Click Listener

```kotlin
onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
```

> Use this lambda to get values of the specific day

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
