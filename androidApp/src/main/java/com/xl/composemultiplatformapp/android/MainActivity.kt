package com.xl.composemultiplatformapp.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.xl.composemultiplatformapp.BannerBean
import com.xl.composemultiplatformapp.Greeting
import com.xl.composemultiplatformapp.KmmViwe
import com.xl.composemultiplatformapp.ResponseBean
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*

import io.ktor.client.plugins.contentnegotiation.*

import io.ktor.client.request.*
import kotlinx.coroutines.launch


import io.ktor.serialization.kotlinx.json.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {

//                    KmmViwe()

                }
            }
        }
    }


}





