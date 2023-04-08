package com.xl.composemultiplatformapp

import android.util.Log
import androidx.compose.runtime.Composable
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.setupKtorComponents
import com.xl.composemultiplatformapp.root.KMMView
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

import com.seiko.imageloader.component.decoder.BitmapFactoryDecoder

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
        }
    }

    override fun getData(result: (msg: String) -> Unit) {

        MainScope().launch(ApplicationDispatcher) {
            val httpRequestData = client.post("https://www.wanandroid.com/user/register") {
                contentType(ContentType.Application.Json)
                parameter("username", "xxx")
                parameter("password", "xxx")
                parameter("repassword", "xxx")
            }
            val data = httpRequestData.body<String>()


            Log.e("TAG", "getData: $data")
            result.invoke(data)

        }
    }


    override fun getClient() = client


    override fun getImageLoader() = ImageLoader() {
        components {
            setupKtorComponents {
                client
            }
            XUtils.app?.let {
                add(BitmapFactoryDecoder.Factory(it, 4096))
            }
        }
    }

}


actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
fun KmmViwe() {
    KMMView("Android")
}



