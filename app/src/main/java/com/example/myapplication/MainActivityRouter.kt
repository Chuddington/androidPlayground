package com.example.myapplication

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.io.Serializable

@Composable
fun MainActivityRouter(
    navController: NavHostController,
    startDestination: String = "/index",
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("/index") {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                ConversionCategories(ConversionCategory.values(), navController = navController)
            }
        }
        composable(
            "/convert/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.EnumType(type = ConversionCategory::class.java)
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

@Suppress("UNCHECKED_CAST")
inline fun <reified T : Serializable> Bundle.apiAwareGetSerializable(key: String): T? =
    if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
        this.getSerializable(key, T::class.java)
    } else {
        this.getSerializable(key) as? T
    }
