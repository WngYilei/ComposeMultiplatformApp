@file:OptIn(ExperimentalResourceApi::class)

package com.xl.composemultiplatformapp.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
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
import com.xl.composemultiplatformapp.root.tab.HomeTab
import com.xl.composemultiplatformapp.root.tab.LikeTab
import com.xl.composemultiplatformapp.root.tab.MainTab

@Composable
internal fun KMMView(device: String) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            TabNavigator(HomeTab) {
                Scaffold(
                    content = {
                        CurrentTab()
                    },
                    bottomBar = {
                        BottomNavigation {
                            TabNavigationItem(HomeTab)
                            TabNavigationItem(LikeTab)
                            TabNavigationItem(MainTab)
                        }
                    }
                )
            }

        }
    }
}

@Composable
internal fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}


@Composable
internal fun Content(url: String, modifier: Modifier = Modifier) {
    CompositionLocalProvider(
        LocalImageLoader provides getPlatform().getImageLoader(),
    ) {
        Box(modifier) {
            val painter = rememberAsyncImagePainter(url)
            Image(painter, null)
        }
    }
}



