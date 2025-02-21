package com.example.mydemo.shops.ui

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mydemo.R
import com.example.mydemo.common.previews.FakeNavController
import com.example.mydemo.helpers.decodeBase64ToBitmap
import com.example.mydemo.helpers.readBase64FromRaw
import com.example.mydemo.shops.composables.ShopViewModel


@Composable
fun ShopScreen(navController: NavController, viewModel: ShopViewModel = viewModel()) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val bakersList = viewModel.filteredBakers.collectAsState().value
    val showBakers = searchQuery.isNotEmpty()

    val context = LocalContext.current
    val base64String = remember { context.readBase64FromRaw(R.raw.logo_base64) }
    val bitmap = remember { base64String?.let { decodeBase64ToBitmap(it) } }

        // Display the image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Shop Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // searchbar
            TextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                label = { Text("Search by name or city") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    ),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onTertiary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.onTertiary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onTertiary,
                    focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ListOf Bakers
            if (showBakers) {
                LazyColumn {
                    items(bakersList) { baker ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    navController.navigate("categoryScreen/${baker.name}")
                                }
                                .border(2.dp, MaterialTheme.colorScheme.onTertiary,
                                    RoundedCornerShape(8.dp)),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = baker.name,
                                    style = MaterialTheme.typography.displayMedium
                                )
                                Text(
                                    text = "${baker.address} ${baker.location}",
                                    style = MaterialTheme.typography.displaySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun ShopScreenPreview() {
    val fakeViewModel = ShopViewModel()
    val fakeContext = Application()
    ShopScreen(navController = FakeNavController(fakeContext), viewModel = fakeViewModel)
}