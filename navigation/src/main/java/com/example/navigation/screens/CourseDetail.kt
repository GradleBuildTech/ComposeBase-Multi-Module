package com.example.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.core.navigation.AppDecorator
import com.example.navigation.utils.ArgsScreen
import com.example.navigation.utils.DestinationRoute

object CourseDetail : ArgsScreen<String> {
    override val route = "${AppDecorator.COURSE_DETAIL}/{${AppDecorator.COURSE_ID_ARGUMENT}}"
    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(AppDecorator.COURSE_ID_ARGUMENT) {type = NavType.StringType}
        )

    override fun objectParser(entry: NavBackStackEntry): String {
        return entry.arguments?.getString(AppDecorator.COURSE_ID_ARGUMENT) ?: ""
    }

    override fun destination(arg: String): DestinationRoute {
        return "$route/{$arg}"
    }
}