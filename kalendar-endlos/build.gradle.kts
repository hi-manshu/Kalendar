plugins {
    id(Plugins.library)
    id(Plugins.kotlinAndroid)
}

android {
    compileSdk = ModuleExtension.compileSdkVersion

    defaultConfig {
        minSdk = ModuleExtension.DefaultConfigs.minSdkVersion
        targetSdk = ModuleExtension.DefaultConfigs.targetSdkVersion

        testInstrumentationRunner = ModuleExtension.DefaultConfigs.testInstrumentationRunner
        consumerProguardFiles(ModuleExtension.DefaultConfigs.defaultConsumerProguardFiles)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ModuleExtension.DefaultConfigs.defaultProguardOptimizeFileName),
                ModuleExtension.DefaultConfigs.proGuardRules
            )
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
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.uiToolingPreview)
    implementation(Deps.Compose.activity)

    debugApi(Deps.Compose.uiTooling)
    implementation(Deps.Compose.pagination)

    testImplementation(Deps.Test.jUnit)
    androidTestImplementation(Deps.AndroidTest.jUnitExtensions)
    androidTestImplementation(Deps.AndroidTest.espressoCore)
    androidTestApi(Deps.AndroidTest.uiTestJunit)

    api(Deps.DateTime.date)
}

plugins.apply(Plugins.vanniktechPublish)
