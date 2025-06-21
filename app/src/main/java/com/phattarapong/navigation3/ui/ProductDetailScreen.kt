package com.phattarapong.navigation3.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ProductDetailScreen(id: String, navigateEditProduct: (id: String) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Product Detail Screen"
        )

        Text(
            text = "Product id: $id"
        )

        Button(
            onClick = {
                navigateEditProduct("1234")
            }
        ) {
            Text(text = "Go to Product Edit")
        }
    }
}