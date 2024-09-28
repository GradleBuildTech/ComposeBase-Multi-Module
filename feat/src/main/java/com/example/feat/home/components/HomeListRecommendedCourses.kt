package com.example.feat.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feat.home.entity.CourseEntity

@Composable
fun HomeListRecommendedCourses(
    courses: List<CourseEntity> = listOf(
        CourseEntity(
            id = "1",
            name = "Course 1",
            description = "Course 1 description",
            imageUrl = "https://i.pravatar.cc/300",
            level = "Beginner",
        ),
        CourseEntity(
            id = "1",
            name = "Course 1",
            description = "Course 1 description",
            imageUrl = "https://i.pravatar.cc/300",
            level = "Beginner",
        ),
        CourseEntity(
            id = "1",
            name = "Course 1",
            description = "Course 1 description",
            imageUrl = "https://i.pravatar.cc/300",
            level = "Beginner",
        )
    ),
    onClickListRecommendedCourses: () -> Unit,
    onClickItemRecommendedCourse: (CourseEntity) -> Unit,
){
    HeadingTitle(title = "\uD83D\uDCBB Recommend courses") {
        onClickListRecommendedCourses()
    }
    Spacer(modifier = Modifier.height(10.dp) )
    Box(modifier = Modifier.fillMaxWidth()){
        LazyRow {
            items(courses.size + 1) { index ->
                if(index == courses.size)
                    Spacer(modifier = Modifier) else
                    Box(
                        modifier = Modifier.padding(
                            if (index == 0) 10.dp else 0.dp,
                            0.dp,
                            10.dp,
                            0.dp,
                        )
                    ) {
                        ItemCourse(course = courses[index]) {
                            onClickItemRecommendedCourse(it)
                        }
                    }
            }
        }
    }
}

