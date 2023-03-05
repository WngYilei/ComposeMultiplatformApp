package com.xl.composemultiplatformapp

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }


    fun getData(result: (msg: String) -> Unit) {
        MainScope().launch(ApplicationDispatcher) {
//            val httpRequestData = client.get("https://www.wanandroid.com/banner/json")
//            val data = httpRequestData.body<ResponseBean>()
//            result.invoke(data.toString())

            val httpRequestData = platform.getClient().post("https://www.wanandroid.com/user/register") {
                    contentType(ContentType.Application.Json)
                    parameter("username", "wangyilei")
                    parameter("password", "xiaolei521")
                    parameter("repassword", "xiaolei521")
                }

            val data = httpRequestData.body<String>()
            result.invoke(data)
        }
    }
}
