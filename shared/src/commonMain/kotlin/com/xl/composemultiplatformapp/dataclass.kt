package com.xl.composemultiplatformapp
import kotlinx.serialization.*
/**
 * @Author : wyl
 * @Date : 2023/2/18
 * Desc :
 */
@Serializable
data class ResponseBean(
    val `data`: List<BannerBean>,
    val errorCode: Int,
    val errorMsg: String
)

@Serializable
data class BannerBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)