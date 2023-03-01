package com.xl.composemultiplatformapp

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.native.concurrent.SharedImmutable

interface Platform {
    val name: String
    fun getData(result:(msg:String)->Unit)
}
@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher
expect fun getPlatform(): Platform