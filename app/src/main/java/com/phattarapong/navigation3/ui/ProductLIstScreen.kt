package com.phattarapong.navigation3.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ProductListScreen(navigateProductDetail: (id: String) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Product List Screen"
        )

        Button(
            onClick = {
                navigateProductDetail("1234")
            }
        ) {
            Text(text = "Go to Product Detail")
        }
    }
}