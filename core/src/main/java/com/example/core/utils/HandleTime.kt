package com.example.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object HandleTime{
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMMMMEEEd(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM HH:mm", Locale.ENGLISH)
        val formatted = current.format(formatter)

        return formatted
    }
}