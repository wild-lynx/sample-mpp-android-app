# Sample MPP Android app
A sample Android application using Kotlin Multiplatform Gradle plugin. 

Already has: 
* System info implemented via `expect`/`actual` mechanism;
* An activity with the device header;
* Common code service loading some data from some open API ([Open Weather API](https://openweathermap.org/api));
* Usage of serialization and coroutines;
* unit-tests coverage for Android-specific code.

# Building
To use the app, create your own Open Weather API key.
In `commonMain/kotlin` package, edit `ApiKey.kt.dummy` placeholder: remove `.dummy` extension, copy-paste your API key into the file.
