package com.xl.composemultiplatformapp.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xl.composemultiplatformapp.data.BannerBean
import com.xl.composemultiplatformapp.Greeting
import com.xl.composemultiplatformapp.model.MainViewModel

@Composable
internal fun KMMView(device: String) {

    val bannerBeans = mutableStateListOf<BannerBean>()
    MainViewModel.getBanner {
        bannerBeans.addAll(it.data)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            LazyColumn(Modifier.fillMaxWidth().height(200.dp).background(Color.Cyan)) {
                itemsIndexed(bannerBeans) { index, item ->
                    Text(item.title)
                }
            }
        }
    }

}