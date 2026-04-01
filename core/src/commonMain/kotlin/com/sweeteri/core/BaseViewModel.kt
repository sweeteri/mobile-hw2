package com.sweeteri.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Intent>(
    initialState: State
) : ViewModel() {

    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    abstract fun processIntent(intent: Intent)

    protected fun updateState(reducer: (State) -> State) {
        _state.update(reducer)
    }
    protected fun sendEvent(event: UiEvent) {
        viewModelScope.launch { _events.emit(event) }
    }
}
sealed interface UiEvent {
    data class OpenCourseDetail(val courseId: String) : UiEvent
}