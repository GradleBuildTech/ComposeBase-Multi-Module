package com.example.navigation.screens

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.core.navigation.AppDecorator
import com.example.navigation.utils.ArgsScreen
import com.example.navigation.utils.DestinationRoute

object TutorDetail : ArgsScreen<String> {
    override val route = "${AppDecorator.TUTOR_DETAIL}/{${AppDecorator.TUTOR_ID_ARGUMENT}}"
    override val arguments = listOf(
        navArgument(AppDecorator.TUTOR_ID_ARGUMENT) {type = NavType.StringType}
    )

    override fun objectParser(entry: NavBackStackEntry): String {
        return entry.arguments?.getString(AppDecorator.TUTOR_ID_ARGUMENT) ?: ""
    }

    override fun destination(arg: String): DestinationRoute {
        return "$route/{$arg}"
    }
}