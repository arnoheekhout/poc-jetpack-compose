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
import androidx.navigation.compose.rememberNavController
import com.example.android_2425_gent10.ui.components.NavBar
import com.example.mydemo.cart.composables.CartViewModel
import com.example.mydemo.cart.ui.CartScreen
import com.example.mydemo.common.composables.theme.DemoTheme
import com.example.mydemo.home.ui.HomeScreen
import com.example.mydemo.shops.ui.CategoryScreen
import com.example.mydemo.shops.ui.ProductDetailScreen
import com.example.mydemo.shops.ui.ProductListScreen
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
    Shop(title = R.string.shop),
    Cart(title = R.string.cart)
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

//                composable(route = "cartScreen/") { backStackEntry ->}
            }
        }
    }
}