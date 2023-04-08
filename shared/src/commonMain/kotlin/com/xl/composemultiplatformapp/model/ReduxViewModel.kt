package com.xl.composemultiplatformapp.model

/**
 * @Author : wyl
 * @Date : 2023/3/5
 * Desc :
 */
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

open class ReduxViewModel {
    private val _state = MutableSharedFlow<Any>()
    val state: SharedFlow<Any>
        get() = _state

    protected val pendingActions = Channel<Any>(Channel.BUFFERED)

    private val viewModelJob = SupervisorJob()
    protected val workScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    protected suspend fun setState(state: Any) {
        _state.emit(state)
    }
}
