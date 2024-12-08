package com.example.listtutor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listtutor.controller.ListTutorViewModel


@Composable
fun ListTutorScreen(
    listTutorViewModel: ListTutorViewModel = hiltViewModel(),
) {
    val uiState by listTutorViewModel.uiState.collectAsState()
}