package com.example.feat.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.components.BottomSheet
import com.example.core.components.NotFound
import com.example.core.components.RefreshableList
import com.example.core.components.SkeletonList
import com.example.core.components.textField.SearchTextField
import com.example.core.lib.constants.DesignSystem
import com.example.feat.R
import com.example.feat.search.components.ContentCategoriesBottomSheet
import com.example.feat.search.components.ItemCourseSearch
import com.example.feat.search.controller.SearchUiEvent
import com.example.feat.search.controller.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by searchViewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val searchController = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val isBottomSheetVisible = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White.copy(alpha = 0.5f)),
        topBar = {
            SearchScreenHeader()
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(
                    searchController = searchController,
                    scope = scope,
                    isBottomSheetVisible = isBottomSheetVisible,
                    sheetState = sheetState,
                    filterApplied = uiState.selectedContentCategory != null,
                ) {
                    searchViewModel.onEvent(SearchUiEvent.OnSearchSubmitted(it))
                }

                when {
                    uiState.isLoading -> {
                        SkeletonList(
                            width = screenWidth.toDouble(),
                            height = 150.0,
                        )
                    }
                    uiState.courses.isNotEmpty() -> {
                        RefreshableList(
                            size = uiState.courses.size,
                            onRefresh = {
                                searchViewModel.onEvent(SearchUiEvent.OnRefresh)
                            },
                            item = { index ->
                                ItemCourseSearch(
                                    course = uiState.courses[index],
                                    modifier = Modifier,
                                )
                            },
                        )
                    }
                    else -> {
                        NotFound(null)
                    }
                }
            }
            BottomSheet(
                isBottomSheetVisible = isBottomSheetVisible.value,
                sheetState = sheetState,
                shape = RoundedCornerShape(DesignSystem.BOTTOM_SHEET_CORNER_RADIUS),
                onDismiss = {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { isBottomSheetVisible.value = false }
                }
            ) {
                ContentCategoriesBottomSheet(
                    contentCategories = uiState.contentCategories,
                    onCategorySelected = {
                        searchViewModel.onEvent(SearchUiEvent.OnSelectedContentCategory(it))
                    },
                    onApplyFilter = {
                        scope.launch { sheetState.hide() }
                            .invokeOnCompletion { isBottomSheetVisible.value = false }
                        searchViewModel.onEvent(SearchUiEvent.OnApplyFilter)
                    }
                )
            }
        }
    }
}

@Composable
fun SearchScreenHeader() {
    Row(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painterResource(R.drawable.ic_logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(30.dp),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = " Course",
                style = DesignSystem.TITLE_LARGE_STYLE.copy(
                    color = colorResource(R.color.primaryColor),
                    fontSize = 24.sp,
                ),
            )
        }
        Text(
            text = "View e-book", style = DesignSystem.TITLE_SMALL_STYLE.copy(
                fontWeight = FontWeight.W600
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchController: MutableState<String>,
    scope: CoroutineScope,
    isBottomSheetVisible: MutableState<Boolean>,
    sheetState: SheetState,
    filterApplied: Boolean = false,
    onSearchChanged: (String) -> Unit = {}
) {
    val iconFilterSelectedColor = colorResource(id = R.color.primaryColor)
    val iconFilterUnSelectedColor = colorResource(id = R.color.black)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchTextField(
            modifier = Modifier.weight(1f),
            searchController = searchController.value,
            onSearchChange = {
                searchController.value = it
                onSearchChanged(it)
            },
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .size(56.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(
                        0.4F
                    ),
                    shape = RoundedCornerShape(DesignSystem.BUTTON_CORNER_RADIUS)
                )
                .clickable {
                    scope.launch {
                        isBottomSheetVisible.value = true
                        sheetState.expand()
                    }
                }
        ) {
            Icon(
                painterResource(id = com.example.core.R.drawable.ic_filter),
                contentDescription = "Filter",
                tint = if (filterApplied) remember {
                    iconFilterSelectedColor
                } else remember {
                    iconFilterUnSelectedColor
                },
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}