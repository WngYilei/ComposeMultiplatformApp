package com.xl.composemultiplatformapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform