package com.example.myapplication.categories

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType.EnumType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.myapplication.apiAwareGetSerializable

fun NavGraphBuilder.conversionRoutes(navController: NavController) {
    navigation(startDestination = "LENGTH", route = "/convert") {
        composable(
            "/convert/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = EnumType(type = ConversionCategory::class.java)
                },
            ),
        ) { backStackEntry ->
            val category = backStackEntry.arguments
                ?.apiAwareGetSerializable<ConversionCategory>(
                    "category",
                )
            println("Converting $category...")
        }
    }
}
