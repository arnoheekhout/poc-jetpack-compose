package com.example.mydemo.common.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .size(32.dp)
            .clickable { onBack() }

    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun PreviewBackButton() {
    BackButton(onBack = { /*nothing happens, its a preview*/  })
}