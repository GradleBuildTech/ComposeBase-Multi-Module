package com.example.feat.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.utils.HandleTime
import com.example.feat.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeBanner(){
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp

    Box(
        modifier = Modifier
            .padding(10.dp) // padding outside box
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.primaryColor))
                .fillMaxWidth()
                .padding(15.dp) // padding inside box
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = HandleTime.getMMMMEEEd(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
            Spacer(modifier = Modifier.height(5.dp))
            listOf(
                "Free eBook material",
                "Free one to one with tutors",
                "Free courses with pdf material",
            ).forEach {
                Text(
                    text = "\uD83D\uDC3C  $it",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }
        }
        Image(
            painterResource(R.drawable.banner),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size((screenHeight * 0.15).dp)
                .align(Alignment.CenterEnd),
        )
    }
}