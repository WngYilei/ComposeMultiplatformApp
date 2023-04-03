package com.xl.composemultiplatformapp

import androidx.compose.ui.window.Application
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.setupKtorComponents
import com.xl.composemultiplatformapp.root.KMMView
import platform.UIKit.UIDevice
import platform.UIKit.UIViewController
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import com.seiko.imageloader.component.decoder.SkiaImageDecoder
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
//            val httpRequestData = client.get("https://www.wanandroid.com/banner/json")
//            val data = httpRequestData.body<ResponseBean>()
//            result.invoke(data.data.toString())

            val httpRequestData = client.post("https://www.wanandroid.com/user/register") {
                contentType(ContentType.Application.Json)
                parameter("username", "wangyilei")
                parameter("password", "xiaolei521")
                parameter("repassword", "xiaolei521")
            }

            val data = httpRequestData.body<String>()
            result.invoke(data)
        }
    }

    override fun getClient() = client
    override fun getImageLoader() = ImageLoader() {
        components {
            setupKtorComponents {
                client
            }
            add(SkiaImageDecoder.Factory())
        }
    }
}

actual fun getPlatform(): Platform = IOSPlatform()


@Suppress("FunctionName", "unused") // Used in iOS
fun KMMViewController(): UIViewController = Application("ComposeMultiplatformApp") {
    KMMView(UIDevice.currentDevice.systemName())
}
