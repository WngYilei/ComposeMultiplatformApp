package com.xl.composemultiplatformapp.root.tab

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.xl.composemultiplatformapp.data.EssayBean
import com.xl.composemultiplatformapp.model.MainViewModel

/**
 * @Author : wyl
 * @Date : 2023/4/8
 * Desc :
 */
internal object HomeTab : Tab {


    @Composable
    override fun Content() {
        HomeScreen()
    }

    override val options: TabOptions
        @Composable get() {
            val title = "主页"
            val icon = rememberVectorPainter(Icons.Default.Home)
            return remember {
                TabOptions(
                    index = 0u, title = title, icon = icon
                )
            }
        }
}


@Composable
internal fun HomeScreen() {
    val data = MainViewModel.state.collectAsState("")
    if (data.value !is EssayBean) return




}
