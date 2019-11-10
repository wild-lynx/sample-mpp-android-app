pluginManagement {
    repositories {
        maven { url 'https://dl.bintray.com/kotlin/kotlin-dev' }
        maven { url 'https://dl.bintray.com/kotlin/kotlin-eap' }

        // a repository to download specific Kotlin Gradle plugin artifacts directly form TeamCity
        maven {
            url "https://teamcity.jetbrains.com/guestAuth/app/rest/builds/id:$custom_build_id/artifacts/content/maven"
        }

        maven { url "https://kotlin.bintray.com/kotlinx" }

        mavenCentral()

        maven { url 'https://plugins.gradle.org/m2/' }
    }
}
rootProject.name = 'sample-mpp-android-app'
//enableFeaturePreview('GRADLE_METADATA')

include ':app'