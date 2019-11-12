rootProject.name = "sample-mpp-android-app"

gradleWrapper("5.6.4", Wrapper.DistributionType.ALL)

pluginManagement {
    plugins {
        "1.3.60-eap-76"(
            kotlin("multiplatform"),
            kotlin("android.extensions"),
            kotlin("plugin.serialization")
        )
        "3.5.2"(
            id("com.android.application"),
            id("com.android.library")
        )
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application",
                "com.android.library"
                -> useModule("com.android.tools.build:gradle:${target.version ?: requested.version}")
            }
        }
    }
}

defaultRepositories {
    // // change the variable value to download a specific build from TeamCity
    // // (currently broken: https://youtrack.jetbrains.com/issue/KT-34246)
    // val customBuildId = 2478816
    // maven("https://teamcity.jetbrains.com/guestAuth/app/rest/builds/id:$customBuildId/artifacts/content/maven")

    maven("https://kotlin.bintray.com/kotlin-dev")
    maven("https://kotlin.bintray.com/kotlin-eap")
    maven("https://kotlin.bintray.com/kotlinx")
    google()
    mavenCentral()
    jcenter()
}

enableFeaturePreview("GRADLE_METADATA")
