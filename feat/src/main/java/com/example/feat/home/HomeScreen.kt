package com.example.feat.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.lib.constants.Constants
import com.example.core.lib.constants.DesignSystem
import com.example.domain.entity.CourseEntity
import com.example.domain.entity.EBookEntity
import com.example.domain.entity.TutorEntity
import com.example.feat.R
import com.example.feat.home.components.HomeBanner
import com.example.feat.home.components.HorizontalItemView
import com.example.feat.home.components.ItemCourse
import com.example.feat.home.components.ItemCourseSkeleton
import com.example.feat.home.components.ItemEBook
import com.example.feat.home.components.ItemEBookSkeleton
import com.example.feat.home.components.ItemTutor
import com.example.feat.home.components.ItemTutorSkeleton
import com.example.feat.home.controller.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by homeViewModel.uiState.collectAsState()
    
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White.copy(alpha = 0.5f)),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 10.dp),
            ) {
                Image(
                    painterResource(R.drawable.ic_logo),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(30.dp),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = " letTutor",
                    style = DesignSystem.TITLE_LARGE_STYLE.copy(
                        color = colorResource(R.color.primaryColor),
                        fontSize = 24.sp,
                    ),
                )
            }
            Text(
                text = "🌟 Welcome to LetTutor",
                modifier = Modifier.padding(horizontal = 10.dp),
                style = DesignSystem.TITLE_MEDIUM_STYLE.copy(
                    color = colorResource(R.color.primaryColor),
                    fontWeight = FontWeight.Medium
                ),
            )
            HomeBanner()
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalItemView(
                listData = uiState.tutors,
                isLoading = uiState.isLoading,
                headingTitle = "\uD83D\uDC68\u200D\uD83C\uDFEB Top tutors",
                showMoreItem = { /*TODO*/ },
                limitItem = Constants.TUTOR_LIMIT_ITEM,
                itemSkeleton = {
                    ItemTutorSkeleton()
                },
                item = {
                    ItemTutor(tutor = it) {

                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalItemView(
                listData = uiState.recommendedCourses,
                isLoading = uiState.isLoading,
                headingTitle = "\uD83D\uDCBB Recommend courses",
                showMoreItem = { /*TODO*/ },
                limitItem = Constants.COURSE_LIMIT_ITEM,
                itemSkeleton = {
                    ItemCourseSkeleton()
                },
                item = {
                    ItemCourse(course = it) {

                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalItemView(
                listData = uiState.eBooks,
                isLoading = uiState.isLoading,
                headingTitle = "\uD83D\uDCD6 Free EBooks",
                showMoreItem = { /*TODO*/ },
                limitItem = Constants.EBOOK_LIMIT_ITEM,
                itemSkeleton = {
                    ItemEBookSkeleton()
                },
                item = {
                    ItemEBook(eBook = it) {

                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}