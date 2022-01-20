### Kalendar - An Elementary Compose Calendar.

![Kalendar](art/kalendar.png)

This is a calendar to integrate Calendar with Custom design in your jetpack compose project.
_Made with ❤️ for Android Developers by Himanshu_

[![Maven Central](https://img.shields.io/maven-central/v/com.himanshoe/kalendar)](https://search.maven.org/artifact/com.himanshoe/kalendar)
[![Github Followers](https://img.shields.io/github/followers/hi-manshu?label=Follow&style=social)](https://github.com/hi-manshu)
[![Twitter Follow](https://img.shields.io/twitter/follow/hi_man_shoe?label=Follow&style=social)](https://twitter.com/hi_man_shoe)

## Introduction

With Compose getting the attention, it was about time to have its own Calendar. Kalendar is all
about it with the customization and design.

## Implementation

### Gradle setup

In `build.gradle` of app module, include this dependency

```gradle
dependencies {
  implementation("com.himanshoe:kalendar:0.0.1-alpha1")
}
```

### Usage

#### 1. Setup the Kalendar

```kotlin
    Kalendar(kalendarType = KalendarType.Firey(), onCurrentDayClick = {
    //handle the date click listener                                                       
}, errorMessage = {
    //Handle the error if any
})
```

#### Here, you can also pass optional params:

- **KalendarKonfig** : It lets you set the configuration for Kalendar.
- **KalendarStyle** : It lets you setup the design of Kalendar.
- **LocalDate** : the by default selection where the KalendarSelector is placed.

### 2. Pass KalendarType

You need to pass the KalendarType with its value in the constructor. It has two values `Firey`
and `Ocenic`. Firey repesents the MonthView like:

![Kalendar](art/firey-kalendar.png)

and Ocenic represents the Week View.

![Kalendar](art/oceanic-kalendar.png)

### 3. KalendarKonfig

By default it takes the initial values added to it.

```kotlin
data class KalendarKonfig(
    val yearRange: YearRange = YearRange(),
)
```

where,

```kotlin
YearRange takes value min -max for years in Int
```

### 4. KalendarStyle

You can customize you calendar by editing the params in KalendarStyle which looks like,

```kotlin
data class KalendarStyle(
    val kalendarBackgroundColor: Color? = null,
    val kalendarColor: Color? = null,
    val kalendarSelector: KalendarSelector = KalendarSelector.Circle(),
    val hasRadius: Boolean = true,
    val elevation: Dp = if (hasRadius) Grid.One else 0.dp,
    val shape: Shape = ButtomCurvedShape,
)
```

Here, if you have to design and change the selector shape and color you need to configure the
**KalendarSelector** to the Shape. The options here are, `Circle`, `Rounded`, `Dot` and `Square`.

### 5. KalendarSelector
```kotlin
sealed class KalendarSelector(
     val shape: Shape,
     val selectedColor: Color,
     val defaultColor: Color,
     val todayColor: Color,
     val selectedTextColor: Color,
     val defaultTextColor: Color,
) 
```
Here the **KalendarSelector** has 4 different shapes with default colors. If you want to set your custom color you can pass the shape and your desired color set like,
```kotlin
KalendarSelector.Circle(
    // Colors set based on above variables
)
```
