package com.xl.composemultiplatformapp

import android.util.Log
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
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.json.JSONObject

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun getData(result: (msg: String) -> Unit) {
        MainScope().launch(ApplicationDispatcher) {
//            val httpRequestData = client.get("https://www.wanandroid.com/banner/json")
//            val data = httpRequestData.body<ResponseBean>()
//            result.invoke(data.toString())


            val jsonObject = JSONObject()
            jsonObject.put("username", "wangyilei")
            jsonObject.put("password", "xiaolei521")
            jsonObject.put("repassword", "xiaolei521")

            val httpRequestData = client.post("https://www.wanandroid.com/user/register") {
                contentType(ContentType.Application.Json)
                setBody(jsonObject.toString())
            }

            val data = httpRequestData.body<String>()
            Log.e("TAG", "getData: $data")
            result.invoke(data)

        }
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
fun KmmViwe() {
    KMMView("Android")
}