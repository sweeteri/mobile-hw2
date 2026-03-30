package com.sweeteri.stepikclient.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State, Intent>(
    initialState: State
) : ViewModel() {

    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    abstract fun processIntent(intent: Intent)

    protected fun updateState(reducer: (State) -> State) {
        _state.update(reducer)
    }
}