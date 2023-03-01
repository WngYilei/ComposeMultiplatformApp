package com.xl.composemultiplatformapp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @Author : wyl
 * @Date : 2023/3/1
 * Desc :
 */

internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Main