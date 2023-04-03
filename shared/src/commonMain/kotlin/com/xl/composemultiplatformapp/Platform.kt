package com.xl.composemultiplatformapp

import com.seiko.imageloader.ImageLoader
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.native.concurrent.SharedImmutable

interface Platform {
    val name: String
    fun getData(result: (msg: String) -> Unit)
    fun getClient(): HttpClient
    fun getImageLoader(): ImageLoader
}

@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher
expect fun getPlatform(): Platform