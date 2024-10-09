package com.example.feat.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.components.HeadingTitle
import com.example.core.components.HorizontalList
import com.example.core.lib.constants.Constants
import com.example.domain.entity.EBookEntity
import com.example.feat.home.controller.HomeViewModel

@Composable
fun HomeListEBooks(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClickListEBooks: () -> Unit,
    onClickItemEBook: (EBookEntity) -> Unit,
){
    val uiState by homeViewModel.uiState.collectAsState()
    val eBooks = uiState.eBooks
    val isLoadingEBooks = uiState.isLoadingEBooks

    HeadingTitle(title = "\uD83D\uDCD6 Free EBooks") {
        onClickListEBooks()
    }
    Spacer(modifier = Modifier.height(10.dp))
    Box(modifier = Modifier.fillMaxWidth()){
        if (isLoadingEBooks) {
            HorizontalList(size = Constants.EBOOK_LIMIT_ITEM) {
                ItemEBookSkeleton()
            }
        } else {
            HorizontalList(size = eBooks.size) { index ->
                ItemEBook(eBooks[index]) {
                    onClickItemEBook(it)
                }
            }
        }
    }
}