package com.example.core.extensions

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun String.toEEEEDDMMYYYY(): String {
    val inputDateTime = this

    // Parse the input string to ZonedDateTime
    val zonedDateTime = ZonedDateTime.parse(inputDateTime)

    // Define the desired output format
    val outputFormatter = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy")

    // Format the date to the desired output
    val formattedDate = zonedDateTime.format(outputFormatter)

    return formattedDate
}