package com.sweeteri.stepikclient.presentation.main

import com.sweeteri.stepikclient.presentation.common.model.CourseUiModel
import com.sweeteri.core.BaseListState

data class MainUiState(
    val listState: BaseListState<CourseUiModel> = BaseListState()
)
