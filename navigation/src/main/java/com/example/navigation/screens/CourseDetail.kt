package com.example.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.navArgument
import com.example.core.navigation.AppDecorator
import com.example.navigation.utils.ArgsScreen
import com.example.navigation.utils.DestinationRoute

object CourseDetail : ArgsScreen<String> {
    override val route = AppDecorator.COURSE_DETAIL
    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(AppDecorator.COURSE_ID_ARGUMENT) {
                nullable = false
                defaultValue = ""
            }
        )

    override fun objectParser(entry: NavBackStackEntry): String {
        return entry.arguments?.getString(AppDecorator.COURSE_ID_ARGUMENT) ?: ""
    }

    override fun destination(arg: String): DestinationRoute {
        return "$route/{$arg}"
    }
}