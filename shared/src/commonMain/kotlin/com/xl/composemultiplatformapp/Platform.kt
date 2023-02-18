package com.xl.composemultiplatformapp

interface Platform {
    val name: String

    fun getData(result:(msg:String)->Unit)
}

expect fun getPlatform(): Platform