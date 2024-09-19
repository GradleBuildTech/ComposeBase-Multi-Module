package com.example.auth.data.api.model.response.token

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime
import java.util.Date

@JsonClass(generateAdapter = true)
data class ChildToModel @RequiresApi(Build.VERSION_CODES.O) constructor(
    val token: String?,
    var expires: String?
)