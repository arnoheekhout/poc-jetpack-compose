package com.example.mydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mydemo.navigation.NavBar
import com.example.mydemo.common.composables.theme.DemoTheme
import com.example.mydemo.home.ui.HomeScreen
import com.example.mydemo.home.ui.TermsAndConditionsScreen
import com.example.mydemo.shops.ui.ShopScreen
import com.example.mydemo.users.ui.EditScreen
import com.example.mydemo.users.ui.EmailScreen
import com.example.mydemo.users.ui.UserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoApp()
        }
    }
}

enum class DemoScreens(@StringRes val title: Int) {
    Home(title = R.string.home),
    User(title = R.string.user),
    EditScreen(title = R.string.edituser),
    EmailScreen(title = R.string.emailuser),
    Shop(title = R.string.shops),
    Cart(title = R.string.cart),
    Terms(title = R.string.terms)
}

@Composable
fun DemoApp() {
    DemoTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                NavBar(navController = navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = DemoScreens.Home.name,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(route = DemoScreens.Home.name) {
                    HomeScreen(navController = navController)
                }

                navigation(
                    startDestination = DemoScreens.User.name, // Start destination of the nested graph
                    route = "user_tab" // Unique route for the nested graph
                ) {
                    // Main User Screen
                    composable(route = DemoScreens.User.name) {
                        UserScreen(
                            onEditButtonClicked = {
                                navController.navigate(DemoScreens.EditScreen.name)
                            },
                            onEmailButtonClicked = {
                                navController.navigate(DemoScreens.EmailScreen.name)
                            }
                        )
                    }

                    // Edit Screen (Nested)
                    composable(route = DemoScreens.EditScreen.name) {
                        EditScreen()
                    }

                    // Email Screen (Nested)
                    composable(route = DemoScreens.EmailScreen.name) {
                        EmailScreen()
                    }
                }

                composable(route = DemoScreens.Shop.name) {
                    ShopScreen(navController = navController)
                }

                composable(route = DemoScreens.Terms.name) {
                    TermsAndConditionsScreen()
                }
            }
        }
    }
}

