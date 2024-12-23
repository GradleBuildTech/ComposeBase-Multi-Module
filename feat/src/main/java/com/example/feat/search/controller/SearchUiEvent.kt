package com.example.feat.search.controller

import com.example.domain.entity.ContentCategoryEntity

sealed class SearchUiEvent {
    class OnSelectedContentCategory(val contentCategory: ContentCategoryEntity) : SearchUiEvent()
    class OnSearchSubmitted(val searchText: String) : SearchUiEvent()
    class OnClickCourseItem(val courseId: String) : SearchUiEvent()
    data object OnApplyFilter : SearchUiEvent()
    data object OnRefresh : SearchUiEvent()
    data object OnBottomSheetDismissed : SearchUiEvent()
    data object OnBackPreviousScreen : SearchUiEvent()
}