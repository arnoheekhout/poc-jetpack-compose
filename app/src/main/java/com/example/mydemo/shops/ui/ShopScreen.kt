package com.example.mydemo.shops.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mydemo.common.composables.PicassoImage
import com.example.mydemo.shops.composables.ShopViewModel
import androidx.compose.runtime.getValue
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import com.example.mydemo.common.previews.FakeNavController


@Composable
fun ShopScreen(navController: NavController, viewModel: ShopViewModel = viewModel()) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val bakersList = viewModel.filteredBakers.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // logo bakeronline
        PicassoImage(
            url = "https://www.google.com/imgres?q=bakeronline%20logo&imgurl=https%3A%2F%2Flatribunedesboulangerspatissiers.fr%2Fwp-content%2Fuploads%2F2022%2F06%2F59-puratos-1.gif&imgrefurl=https%3A%2F%2Flatribunedesboulangerspatissiers.fr%2Fpuratos-rapidle-59%2F59-puratos-1%2F&docid=_bsP4v59uD61GM&tbnid=ZOwVmpMRRjylWM&vet=12ahUKEwjx-KuQ-syLAxWTm_0HHSiBAvMQM3oECGsQAA..i&w=960&h=142&hcb=2&ved=2ahUKEwjx-KuQ-syLAxWTm_0HHSiBAvMQM3oECGsQAA",
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // searchbar
        TextField(
            value = searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = { Text("Zoek op plaats of bakkerij...") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ListOf Bakers
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
                        Text(text = baker.name, style = MaterialTheme.typography.displayMedium)
                        Text(text = "📍 ${baker.location}", style = MaterialTheme.typography.displaySmall)
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
    ShopScreen(navController = FakeNavController(), viewModel = fakeViewModel)
}
