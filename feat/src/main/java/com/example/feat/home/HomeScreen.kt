package com.example.feat.home

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.lib.constants.DesignSystem
import com.example.feat.R
import com.example.feat.home.components.HomeBanner
import com.example.feat.home.components.HomeListEBooks
import com.example.feat.home.components.HomeListRecommendedCourses
import com.example.feat.home.components.HomeListTutors
import com.example.feat.home.controller.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
) {
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
            HomeListTutors(
                onClickListTutors = { /*TODO*/ },
                onClickItemTutor = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.height(20.dp))
            HomeListRecommendedCourses(
                onClickListRecommendedCourses = { /*TODO*/ },
                onClickItemRecommendedCourse = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.height(20.dp))
            HomeListEBooks(
                onClickListEBooks = { /*TODO*/ },
                onClickItemEBook = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}