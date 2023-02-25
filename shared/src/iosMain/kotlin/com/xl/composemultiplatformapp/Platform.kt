package com.xl.composemultiplatformapp

import androidx.compose.ui.window.Application
import com.xl.composemultiplatformapp.root.KMMView
import platform.UIKit.UIDevice
import platform.UIKit.UIViewController
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    private val client = HttpClient(Darwin) {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun getData(result: (msg: String) -> Unit) {
        MainScope().launch {
            val httpRequestData = client.get("https://www.wanandroid.com/banner/json")
            val data = httpRequestData.body<ResponseBean>()
            result.invoke(data.data.toString())
        }
    }
}

actual fun getPlatform(): Platform = IOSPlatform()


@Suppress("FunctionName", "unused") // Used in iOS
fun KMMViewController(): UIViewController = Application("ComposeMultiplatformApp") {
    KMMView(UIDevice.currentDevice.systemName())
}
