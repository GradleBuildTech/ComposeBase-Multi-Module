package com.example.feat.home.components

import com.example.feat.home.entity.EBookEntity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeListEBooks(
    eBooks: List<EBookEntity> = listOf(
        EBookEntity(
            id = "1",
            name = "John Doe",
            imageUrl = "https://i.pravatar.cc/300",
            level = "Beginner",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n"
        ),
        EBookEntity(
            id = "1",
            name = "John Doe",
            imageUrl = "https://i.pravatar.cc/300",
            level = "Beginner",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                    "\n"
        ),
    ),
    onClickListEBooks: () -> Unit,
    onClickItemEBook: (EBookEntity) -> Unit,
){
    HeadingTitle(title = "\uD83D\uDCD6 Free EBooks") {
        onClickListEBooks()
    }
    Spacer(modifier = Modifier.height(10.dp))
    Box(modifier = Modifier.fillMaxWidth()){
        LazyRow {
            items(eBooks.size + 1) { index ->
                if(index == eBooks.size)
                    Spacer(modifier = Modifier) else
                    Box(
                        modifier = Modifier.padding(
                            if (index == 0) 10.dp else 0.dp,
                            0.dp,
                            10.dp,
                            0.dp,
                        )
                    ) {
                        ItemEBook(eBooks[index]) {
                            onClickItemEBook(it)
                        }
                    }
            }
        }
    }
}