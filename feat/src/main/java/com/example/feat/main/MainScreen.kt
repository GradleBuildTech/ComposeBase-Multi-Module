package com.example.feat.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feat.document.controller.DocumentViewModel
import com.example.feat.home.controller.HomeViewModel
import com.example.feat.main.controller.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    documentViewModel: DocumentViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    buildBody: @Composable (DocumentViewModel, HomeViewModel) -> Unit = { _, _ -> }
) {
    buildBody(documentViewModel, homeViewModel)
}
