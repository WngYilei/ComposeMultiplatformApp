package com.xl.composemultiplatformapp.root.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.xl.composemultiplatformapp.data.DataX
import com.xl.composemultiplatformapp.data.EssayBean
import com.xl.composemultiplatformapp.data.ProjectDataX
import com.xl.composemultiplatformapp.data.ProjectResponse
import com.xl.composemultiplatformapp.model.MainViewModel
import com.xl.composemultiplatformapp.root.MyImage

/**
 * @Author : wyl
 * @Date : 2023/4/8
 * Desc :
 */
internal object LikeTab : Tab {
    @Composable
    override fun Content() {
        ProjectScreen()
        MainViewModel.getProject()
    }

    override val options: TabOptions
        @Composable get() {
            val title = "喜欢"
            val icon = rememberVectorPainter(Icons.Default.Star)

            return remember {
                TabOptions(
                    index = 0u, title = title, icon = icon
                )
            }
        }
}


@Composable
internal fun ProjectScreen() {
    val data = MainViewModel.state.collectAsState("")
    if (data.value !is ProjectResponse) return
    val projectBeans by remember {
        derivedStateOf { mutableStateListOf<ProjectDataX>() }
    }

    val projectData = data.value as ProjectResponse

    projectBeans.addAll(projectData.data.datas)

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Column {
            LazyColumn(Modifier.fillMaxWidth()) {
                itemsIndexed(projectBeans) { _, item ->
                    ProjectItem(item)
                }
            }
        }
    }
}

@Composable
internal fun ProjectItem(dataX: ProjectDataX) {
    Box(contentAlignment = Alignment.Center) {
        Card(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
            Column(
                modifier = Modifier.padding(
                    start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp
                )
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(dataX.author, fontSize = 12.sp, color = Color.Gray)
                    Text(
                        dataX.niceShareDate,
                        fontSize = 12.sp,
                        modifier = Modifier.align(alignment = Alignment.TopEnd),
                        color = Color.Gray
                    )
                }

                Row(modifier = Modifier.padding(top = 10.dp)) {
                    MyImage(dataX.envelopePic, modifier = Modifier.height(200.dp).width(100.dp))
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text(dataX.title, fontSize = 16.sp, color = Color.Black, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(dataX.desc, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(top = 10.dp))
                    }
                }

                Text(
                    dataX.superChapterName,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 40.dp),
                    color = Color.Gray
                )
            }
        }
    }
}
