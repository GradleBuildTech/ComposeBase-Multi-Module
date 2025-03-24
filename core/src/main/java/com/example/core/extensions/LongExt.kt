package com.example.core.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.convertToTimeString(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(Date(this))
}

fun Long.convertToDate() : Date = Date(this)