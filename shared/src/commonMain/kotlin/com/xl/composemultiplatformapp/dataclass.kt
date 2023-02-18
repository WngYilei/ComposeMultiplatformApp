package com.xl.composemultiplatformapp

/**
 * @Author : wyl
 * @Date : 2023/2/18
 * Desc :
 */
@kotlinx.serialization.Serializable
data class BannerBean(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
)

@kotlinx.serialization.Serializable
data class Data(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)