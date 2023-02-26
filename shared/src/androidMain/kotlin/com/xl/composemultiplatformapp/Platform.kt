package com.xl.composemultiplatformapp

import androidx.compose.runtime.Composable
import com.xl.composemultiplatformapp.root.KMMView
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun getData(result: (msg: String) -> Unit) {
        MainScope().launch {
            val httpRequestData = client.get("https://www.wanandroid.com/banner/json")
            val data = httpRequestData.body<ResponseBean>()
            result.invoke(data.toString())
        }
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()
@Composable
fun KmmViwe() {
    KMMView("Android")
}