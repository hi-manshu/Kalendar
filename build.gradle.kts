// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Deps.Gradle.androidGradlePlugin)
        classpath(Deps.Gradle.kotlinGradlePlugin)
        classpath(Deps.Gradle.vanniktechGradlePlugin)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

allprojects {
    pluginManager.withPlugin(Plugins.vanniktechPublish) {
        extensions.getByType(com.vanniktech.maven.publish.MavenPublishPluginExtension::class.java)
            .apply {
                sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
            }
    }
}
