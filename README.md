# Sample MPP Android app
A sample Android application using Kotlin Multiplatform Gradle plugin. 

Already has: 
* System info implemented via `expect`/`actual` mechanism;
* An activity with the device header;
* Common code service loading some data frome some open API ([Open Weather API|https://openweathermap.org/api]);
* Usage of serialization and coroutines;

To be implemented:
* unit-tests coverage for both Android-specific and common code.

#Building
To use the app, create your own Open Weather API key.
In `service` package of `commonMain`, create `weatherApiKey` string and copy-paste your API key there.