package com.xl.composemultiplatformapp.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.xl.composemultiplatformapp.Greeting
import com.xl.composemultiplatformapp.getPlatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun KMMView(device: String) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        val str = remember { mutableStateOf("Compose 跨端  $device  View  ${Greeting().greet()}") }

        Column {
            Text(str.value)

//            Image(
//                painter = painterResource("icon_look_devices.png"), ""
//            )

            Button(onClick = {
                Greeting().getData {
                    str.value = it
                }
            }) {
                Text("获取数据")
            }
        }
    }

}