package com.xl.composemultiplatformapp.model

import com.xl.composemultiplatformapp.ApplicationDispatcher
import com.xl.composemultiplatformapp.Platform
import com.xl.composemultiplatformapp.data.EssayBean
import com.xl.composemultiplatformapp.data.ResponseBean
import com.xl.composemultiplatformapp.getPlatform
import com.xl.composemultiplatformapp.net.HttpUrl
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.replay
import kotlinx.coroutines.launch


/**
 * @Author : wyl
 * @Date : 2023/3/5
 * Desc :
 */
object MainViewModel : ReduxViewModel() {
    private val platform: Platform = getPlatform()

    fun getBanner(result: (bean: ResponseBean) -> Unit) {
        MainScope().launch {
            val httpRequestData = platform.getClient().get(HttpUrl.banner)
            val data = httpRequestData.body<ResponseBean>()
            result.invoke(data)
        }
    }


    fun getEssay() {
        MainScope().launch {
            val httpRequestData = platform.getClient().get(HttpUrl.essay)
            val data = httpRequestData.body<EssayBean>()
            setState(data)
        }
    }

}