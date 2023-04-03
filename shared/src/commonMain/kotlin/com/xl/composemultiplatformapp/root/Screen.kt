@file:OptIn(ExperimentalResourceApi::class)

package com.xl.composemultiplatformapp.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.component.setupKtorComponents
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.rememberAsyncImagePainter
import com.xl.composemultiplatformapp.Platform
import com.xl.composemultiplatformapp.data.BannerBean
import com.xl.composemultiplatformapp.getPlatform
import com.xl.composemultiplatformapp.model.MainViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import com.seiko.imageloader.component.decoder.Decoder

@Composable
internal fun KMMView(device: String) {

    val bannerBeans = mutableStateListOf<BannerBean>()
    MainViewModel.getBanner {
        bannerBeans.addAll(it.data)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {

//            Image(
//                painter = painterResource("icon_look_devices.png"), ""
//            )
//            LazyColumn(Modifier.fillMaxWidth().height(200.dp).background(Color.Cyan)) {
//                itemsIndexed(bannerBeans) { index, item ->
//                    Text(item.title)
//                }
//            }

            CompositionLocalProvider(
                LocalImageLoader provides getPlatform().getImageLoader(),
            ) {
                Box(modifier = Modifier.fillMaxSize().background(Color.Cyan)) {
                    val url =
                        "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png"
                    val painter = rememberAsyncImagePainter(url)
                    println("status:" + painter.requestState.toString())
                    Image(painter, null)
                }
            }


        }
    }
}

@Composable
fun Test() {
    Text("这是一段测试文本")
}


//@Composable
//fun Content() {
//    CompositionLocalProvider(
//        LocalImageLoader provides getPlatform().getImageLoader(),
//    ) {
//        Box(modifier = Modifier.fillMaxSize().background(Color.Cyan)) {
//            val url = "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png"
//            val painter = rememberAsyncImagePainter(url)
//            println("status:"+painter.requestState.toString())
//            Image(painter, null)
//        }
//    }
//
//    Text("测试测试测试")
//}

