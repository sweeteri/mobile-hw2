package com.sweeteri.stepikclient.presentation.search

import com.sweeteri.stepikclient.presentation.common.model.CourseUiModel
import com.sweeteri.stepikclient.presentation.common.state.BaseListState

data class SearchUiState(
    val query: String = "",
    val listState: BaseListState<CourseUiModel> = BaseListState()
)