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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mydemo.common.composables.PicassoImage
import com.example.mydemo.common.previews.FakeNavController
import com.example.mydemo.helpers.decodeBase64ToBitmap
import com.example.mydemo.shops.composables.ShopViewModel


@Composable
fun ShopScreen(navController: NavController, viewModel: ShopViewModel = viewModel()) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val bakersList = viewModel.filteredBakers.collectAsState().value
    val showBakers = searchQuery.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // logo bakeronline base64 string omzetten naar picture.
        val base64ImageData =
            "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"
        val bitmap = decodeBase64ToBitmap(base64ImageData)

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
                                    navController.navigate("shopDetail/${baker.name}")
                                }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = baker.name,
                                    style = MaterialTheme.typography.displayMedium
                                )
                                Text(
                                    text = "📍 ${baker.location}",
                                    style = MaterialTheme.typography.displaySmall
                                )
                            }
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