package com.example.feat.tutorDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.components.Skeleton

@Composable
fun TutorDetailSkeleton(
    modifier: Modifier,
    screenWidth: Double
) {

    Column(
        modifier = modifier.padding(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Skeleton(width = 80.0, height = 80.0, cornerRadius = 100.0)
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                for (i in 0..2) {
                    Skeleton(height = 20.0,cornerRadius = 8.0)
                    if (i != 2) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            for(i in 0..1){
                Skeleton(modifier = Modifier.weight(1F), height = 52.0, cornerRadius = 8.0)
                if (i != 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Skeleton(height = 200.0,  width = screenWidth ,cornerRadius = 8.0)
        Spacer(modifier = Modifier.height(12.dp))
        Skeleton(height = 32.0,  width = screenWidth * 0.1 ,cornerRadius = 8.0)
        Spacer(modifier = Modifier.height(8.dp))
        Skeleton(height = 100.0,  width = screenWidth,cornerRadius = 8.0)
    }

}