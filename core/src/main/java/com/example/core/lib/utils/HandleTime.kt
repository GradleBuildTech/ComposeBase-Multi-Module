package com.example.core.lib.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object HandleTime{
    fun getMMMMEEEd(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM HH:mm", Locale.ENGLISH)
        val formatted = current.format(formatter)

        return formatted
    }
}