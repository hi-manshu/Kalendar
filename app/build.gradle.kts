plugins {
    id(Plugins.application)
    kotlin(Plugins.android)
}

android {
    compileSdk = ModuleExtension.compileSdkVersion

    defaultConfig {
        applicationId = ModuleExtension.App.applicationId
        minSdk = ModuleExtension.DefaultConfigs.minSdkVersion
        targetSdk = ModuleExtension.DefaultConfigs.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = ModuleExtension.DefaultConfigs.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ModuleExtension.DefaultConfigs.defaultProguardOptimizeFileName),
                ModuleExtension.DefaultConfigs.proGuardRules
            )
        }
        create("staging") {
            initWith(getByName("debug"))
            matchingFallbacks.add("debug")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = ModuleExtension.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":kalendar"))
    implementation(Deps.DateTime.date)
    implementation(project(mapOf("path" to ":kalendar-endlos")))
    implementation("androidx.compose.material3:material3:1.0.0-alpha15")
    implementation("androidx.navigation:navigation-compose:2.5.1")
    // jetpack compose
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.0") // <- this dependency is required
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.activity)
    implementation(Deps.Jetpack.Core.ktx)
    implementation(Deps.Android.materialDesign)
    testImplementation(Deps.Test.jUnit)
    androidTestImplementation(Deps.AndroidTest.jUnitExtensions)
    androidTestImplementation(Deps.AndroidTest.espressoCore)
}
