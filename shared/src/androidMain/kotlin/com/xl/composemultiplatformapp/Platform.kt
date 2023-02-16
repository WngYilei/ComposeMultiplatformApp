package com.xl.composemultiplatformapp

import androidx.compose.runtime.Composable
import com.xl.composemultiplatformapp.root.KMMView

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
fun KmmViwe() {
    KMMView("Android")
}