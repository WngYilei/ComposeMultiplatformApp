package com.xl.composemultiplatformapp
import kotlin.coroutines.*
import kotlinx.coroutines.*
import platform.darwin.*
/**
 * @Author : wyl
 * @Date : 2023/3/1
 * Desc :
 */
internal actual val ApplicationDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

internal class NsQueueDispatcher(
    private val dispatchQueue: dispatch_queue_t
) : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}