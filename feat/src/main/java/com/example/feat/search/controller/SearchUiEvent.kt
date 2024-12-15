package com.example.feat.search.controller

import com.example.domain.entity.ContentCategoryEntity

sealed class SearchUiEvent {
    class OnSelectedContentCategory(val contentCategory: ContentCategoryEntity) : SearchUiEvent()
    data object OnApplyFilter : SearchUiEvent()
    class OnSearchSubmitted(val searchText: String) : SearchUiEvent()
    data object OnRefresh : SearchUiEvent()
    data object OnBottomSheetDismissed : SearchUiEvent()
}