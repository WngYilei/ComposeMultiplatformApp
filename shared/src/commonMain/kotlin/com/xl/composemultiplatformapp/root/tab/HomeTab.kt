package com.xl.composemultiplatformapp.root.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.xl.composemultiplatformapp.data.BannerBean
import com.xl.composemultiplatformapp.data.DataX
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
        MainViewModel.getEssay()
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
    val essayBeans by remember {
        derivedStateOf { mutableStateListOf<DataX>() }
    }

    val essayData = data.value as EssayBean

    essayBeans.addAll(essayData.data.datas)

    Box(modifier = Modifier.fillMaxSize().background(Color.Gray), contentAlignment = Alignment.Center) {
        Column {
            LazyColumn(Modifier.fillMaxWidth()) {
                itemsIndexed(essayBeans) { _, item ->
                    EssayItem(item)
                }
            }
        }
    }
}

@Composable
internal fun EssayItem(dataX: DataX) {
    Box(contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 10.dp)
        ) {
            Column(  modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(dataX.author, fontSize = 12.sp,color = Color.Gray)
                    Text(dataX.niceShareDate, fontSize = 12.sp, modifier = Modifier.align(alignment = Alignment.TopEnd),color = Color.Gray)
                }
                Text(dataX.title, fontSize = 16.sp, color = Color.Black)
                Text(dataX.superChapterName, fontSize = 12.sp, modifier = Modifier.padding(top = 40.dp), color = Color.Gray)
            }
        }
    }
}
