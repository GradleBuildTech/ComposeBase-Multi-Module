package com.example.feat.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feat.document.controller.DocumentViewModel
import com.example.feat.home.controller.HomeViewModel
import com.example.feat.main.controller.MainViewModel
import com.example.feat.search.controller.SearchViewModel

/// MainScreen is a composable that wraps the MainViewModel, DocumentViewModel, HomeViewModel, and SearchViewModel.
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    documentViewModel: DocumentViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel(),

    ///Define the buildBody lambda function as a parameter.
    buildBody: @Composable (DocumentViewModel, HomeViewModel, SearchViewModel) -> Unit = { _, _, _ -> }
) {
    buildBody(documentViewModel, homeViewModel, searchViewModel)
}
