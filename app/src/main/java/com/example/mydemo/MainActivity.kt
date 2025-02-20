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
import androidx.navigation.compose.rememberNavController
import com.example.android_2425_gent10.ui.components.NavBar
import com.example.mydemo.common.composables.theme.DemoTheme
import com.example.mydemo.home.ui.HomeScreen
import com.example.mydemo.home.ui.TermsAndConditionsComponent
import com.example.mydemo.home.ui.TermsAndConditionsScreen
import com.example.mydemo.shops.ui.ShopScreen
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


                composable(route = DemoScreens.User.name) {
                    UserScreen(onNextButtonClicked = {
                        navController.navigate(DemoScreens.User)
                    })
                }

                composable(route = DemoScreens.Shop.name) {
                    ShopScreen(navController = navController)
                }

                composable(route = DemoScreens.Terms.name) {
                    TermsAndConditionsScreen(navController = navController)
                }
            }
        }
    }
}