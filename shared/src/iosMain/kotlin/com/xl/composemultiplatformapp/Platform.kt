package com.xl.composemultiplatformapp

import androidx.compose.ui.window.Application
import com.xl.composemultiplatformapp.root.KMMView
import platform.UIKit.UIDevice
import platform.UIKit.UIViewController

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


@Suppress("FunctionName", "unused") // Used in iOS
fun KMMViewController(): UIViewController = Application("ComposeMultiplatformApp") {
    KMMView(UIDevice.currentDevice.systemName())
}
