package com.example.mydemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mydemo.cart.composables.CartViewModel
import com.example.mydemo.cart.ui.CartScreen
import com.example.mydemo.cart.ui.ConfirmationScreen
import com.example.mydemo.cart.ui.DateTimePickerScreen
import com.example.mydemo.common.composables.theme.DemoTheme
import com.example.mydemo.home.ui.HomeScreen
import com.example.mydemo.home.ui.TermsAndConditionsScreen
import com.example.mydemo.navigation.NavBar
import com.example.mydemo.shops.ui.CategoryScreen
import com.example.mydemo.shops.ui.ProductDetailScreen
import com.example.mydemo.shops.ui.ProductListScreen
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

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DemoApp() {
    DemoTheme {
        val navController = rememberNavController()
        val cartViewModel: CartViewModel = viewModel()

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

                composable(route = DemoScreens.Cart.name) {
                    CartScreen(navController = navController, cartViewModel = cartViewModel)
                }

                composable(route = "categoryScreen/{shopName}") { backStackEntry ->
                    val shopName = backStackEntry.arguments?.getString("shopName") ?: "Onbekend"
                    CategoryScreen(navController = navController, shopName = shopName)
                }

                composable(route = "productListScreen/{shopName}/{category}") { backStackEntry ->
                    val shopName = backStackEntry.arguments?.getString("shopName") ?: "Onbekend"
                    val category = backStackEntry.arguments?.getString("category") ?: "Onbekend"

                    ProductListScreen(
                        navController = navController,
                        shopName = shopName,
                        category = category
                    )
                }

                composable(route = "productDetailScreen/{shopName}/{category}/{productName}") { backStackEntry ->
                    val shopName = backStackEntry.arguments?.getString("shopName") ?: "Onbekend"
                    val category = backStackEntry.arguments?.getString("category") ?: "Onbekend"
                    val productName =
                        backStackEntry.arguments?.getString("productName") ?: "Onbekend"

                    ProductDetailScreen(
                        navController = navController,
                        shopName = shopName,
                        category = category,
                        productName = productName,
                        cartViewModel = cartViewModel
                    )
                }

                composable(route = "dateTimePickerScreen") {
                    DateTimePickerScreen(navController = navController)
                }

                composable(route = "confirmationScreen") {
                    ConfirmationScreen(navController = navController, cartViewModel = cartViewModel)
                }

                composable(route = DemoScreens.Terms.name) {
                    TermsAndConditionsScreen()
                }
            }
        }
    }
}