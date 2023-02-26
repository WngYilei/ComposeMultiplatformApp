plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
}


kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }


    val args = listOf(
        "-linker-option", "-framework", "-linker-option", "Metal",
        "-linker-option", "-framework", "-linker-option", "CoreText",
        "-linker-option", "-framework", "-linker-option", "CoreGraphics"
    )


    iosX64("uikitX64") {
        binaries {
            executable {
                entryPoint = "main"
                freeCompilerArgs = freeCompilerArgs + args
            }
        }
    }
    iosArm64("uikitArm64") {
        binaries {
            executable {
                entryPoint = "main"
                freeCompilerArgs = freeCompilerArgs + args
                freeCompilerArgs = freeCompilerArgs + "-Xdisable-phases=VerifyBitcode"
            }
        }
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }




    sourceSets {
        val commonMain by getting{

            dependencies {
                with(compose) {
                    implementation(ui)
                    implementation(foundation)
                    implementation(material)
                    implementation(runtime)
                }
                implementation("org.jetbrains.compose.components:components-resources:1.4.0-alpha01-dev942")
                implementation(libs.ktor.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.client.negotiation)
                implementation(libs.ktor.client.json)
            }

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3-native-mt")
                implementation(libs.ktor.client.ios)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}


android {
    namespace = "com.xl.composemultiplatformapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDir("src/commonMain/resources")
}
