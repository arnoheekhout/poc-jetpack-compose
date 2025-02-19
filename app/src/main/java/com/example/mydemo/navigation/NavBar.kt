package com.example.android_2425_gent10.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavBar(
    navController: NavController
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val scope = rememberCoroutineScope()

    NavigationBar(containerColor = Color(0xFF42C5BE)) {
        val items = buildList {
            add(NavigationItem.Home)
            add(NavigationItem.User)
            add(NavigationItem.Shop)
            add(NavigationItem.Cart)
        }

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    }
}

sealed class NavigationItem(var title: String, var route: String, var icon: ImageVector) {
    object Home : NavigationItem("Home", "Home", Icons.Filled.Home)
    object User : NavigationItem("User", "User", Icons.Filled.DateRange)
    object Shop : NavigationItem("Shop", "Shop", Icons.Filled.LocationOn)
    object Cart : NavigationItem("Cart", "Cart", Icons.Filled.ShoppingCart)
}