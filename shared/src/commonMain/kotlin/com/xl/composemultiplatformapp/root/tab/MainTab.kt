package com.xl.composemultiplatformapp.root.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.xl.composemultiplatformapp.root.MyImage

/**
 * @Author : wyl
 * @Date : 2023/4/8
 * Desc :
 */
internal object MainTab : Tab {
    @Composable
    override fun Content() {
        MeScreen()
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


@Composable
internal fun MeScreen() {
    Column {
        Box(modifier = Modifier.fillMaxWidth().height(300.dp).background(Color(0xFF95ECCC)), contentAlignment = Alignment.Center) {
            MyImage("https://wyl-image.oss-cn-hangzhou.aliyuncs.com/%E6%97%A9%E4%B8%8A-%E6%98%8E%E5%AA%9A.png", modifier = Modifier.size(100.dp))

        }
    }


}