package com.xl.composemultiplatformapp.root.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

/**
 * @Author : wyl
 * @Date : 2023/4/8
 * Desc :
 */
internal object MainTab : Tab {
    @Composable
    override fun Content() {
        Box(contentAlignment = Alignment.Center) {

            Button(onClick = {

            },){
                Text("按钮")
            }
        }
    }

    override val options: TabOptions
        @Composable get() {
            val title = "个人"
            val icon = rememberVectorPainter(Icons.Default.Person)

            return remember {
                TabOptions(
                    index = 0u, title = title, icon = icon
                )
            }
        }
}