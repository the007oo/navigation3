package com.phattarapong.navigation3.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun EditProfileScreen(id: String) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Edit Product Screen"
        )

        Text(
            text = "Product id: $id"
        )
    }
}