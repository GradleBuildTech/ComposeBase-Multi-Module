package com.example.feat.document.components

import android.os.CountDownTimer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun Timer(
    millisInFuture: Long,
    header: String,
) {
    val currentTime = System.currentTimeMillis() / 1000

    if (currentTime > millisInFuture) {
        return
    }

    val timeData = remember {
        mutableLongStateOf(millisInFuture - currentTime)
    }

    val countDownTimer = remember {
        object : CountDownTimer(millisInFuture - currentTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeData.value = millisUntilFinished
            }

            override fun onFinish() {
                timeData.value = 0
            }
        }
    }

    DisposableEffect(key1 = header) {
        countDownTimer.start()
        onDispose {
            countDownTimer.cancel()
        }
    }

    val hours = timeData.value / 1000 / 3600
    val minutes = timeData.value / 1000 % 3600 / 60
    val seconds = timeData.value / 1000 % 60

    TimeRichText(header = header, hours = hours, minutes = minutes, seconds = seconds)
}
