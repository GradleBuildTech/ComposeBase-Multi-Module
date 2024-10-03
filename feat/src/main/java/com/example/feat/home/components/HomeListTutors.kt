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
import com.example.feat.home.entity.TutorEntity

@Composable
fun HomeListTutors(
    tutors: List<TutorEntity> = listOf(
        TutorEntity(
            id = "1",
            name = "John Doe",
            avatar = "https://i.pravatar.cc/300",
            bio = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n",
            level = "Beginner",
            country = "USA",
        ),
        TutorEntity(
            id = "1",
            name = "John Doe",
            avatar = "https://i.pravatar.cc/300",
            bio = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n",
            level = "Beginner",
            country = "USA",
        )
    ),
    onClickListTutors: () -> Unit,
    onClickItemTutor: (TutorEntity) -> Unit,
) {
    HeadingTitle(title = "\uD83D\uDC68\u200D\uD83C\uDFEB Top tutors") {
        onClickListTutors()
    }
    Spacer(modifier = Modifier.height(10.dp))
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyRow {
            items(tutors.size + 1) { index ->
                if (index == tutors.size)
                    Spacer(modifier = Modifier) else
                    Box(
                        modifier = Modifier.padding(
                            if (index == 0) 10.dp else 0.dp,
                            0.dp,
                            10.dp,
                            0.dp,
                        )
                    ) {
                        ItemTutor(tutors[index]) {
                            onClickItemTutor(it)
                        }
                    }
            }
        }
    }
}