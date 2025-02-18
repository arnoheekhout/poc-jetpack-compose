package com.example.mydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.example.android_2425_gent10.ui.components.NavBar
import com.example.mydemo.ui.home.HomeScreen
import com.example.mydemo.ui.theme.AppTheme
import com.example.mydemo.ui.users.UserScreen

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
    Shops(title = R.string.shops),
    Cart(title = R.string.cart)
}

@Composable
fun DemoApp() {
    AppTheme {
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
                    HomeScreen(
                        onNextButtonClicked = {
                            navController.navigate(DemoScreens.Home)
                        })
                }


                composable(route = DemoScreens.User.name) {
                    UserScreen(onNextButtonClicked = {
                        navController.navigate(DemoScreens.User)
                    })
                }

            }
        }
    }
}
