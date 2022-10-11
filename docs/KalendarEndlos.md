
## Kalendar

This is a calendar library that gives integration for the year View starting with Current month. It uses pagination3 under the hood.

## Introduction

With Compose getting the attention, it was about time to have its own Calendar. Kalendar is all    
about it with the customization and design.

## Implementation

### Step: 01

To add Kalendar, add this dependency,

```gradle 
dependencies {    
  .....  
 implementation("com.himanshoe:kalendar-endlos:1.0.0")
 } 
 ```  

### Step: 02 (Usage)
To integrate Kalendar use the composable,

```kotlin    
Kalendar() 
``` 
Preview:  
<img src= "/art/endlos.png" data-canonical-src="/art/endlos.png" width="200" height="400" />

### Step: 03 (Customisation)
With Kalendar at your disposal you have customization at your tip to make it suited based on your need.

`Kalendar` composable has lot of optional parameters that you can use. Like,

> KalendarHeader

If you want to configure the KalendarHeader, use:
```kotlin
kalendarHeaderConfig : KalendarHeaderConfig
```

> Setting up Events
```kotlin  
kalendarEvents: List<KalendarEvent> = emptyList(),  
```  
Here, `KalendarEvent` can be used to pass events to mark specific days. `KalendarEvent` looks like,
```kotlin  
data class KalendarEvent(    
 val date: LocalDate,    
 val eventName: String,    
 val eventDescription: String? = null, )  
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
 val headerTextColor: Color, )  
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
 
