package com.example.feat.main.controller

import com.example.core.lib.constants.Constants
import com.example.core.lib.constants.DrawableConst

data class MainState(
    val currentIndex: Int = 0,
    val tabs: List<String> = listOf(
        Constants.HOME_TAB,
        Constants.DOCUMENT_TAB,
        Constants.SEARCH_TAB,
        Constants.CALENDAR_TAB,
        Constants.PROFILE_TAB,
    ),
    val icons: List<Int> = listOf(
        DrawableConst.HOME_ICON,
        DrawableConst.DOCUMENT_ICON,
        DrawableConst.SEARCH_ICON,
        DrawableConst.CALENDAR_ICON,
        DrawableConst.PROFILE_ICON,
    )

)