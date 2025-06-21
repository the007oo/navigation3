package com.phattarapong.navigation3.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

object Destination {
    @Serializable
    data object ProductList : NavKey

    @Serializable
    data class ProductDetail(val id: String) : NavKey

    @Serializable
    data class EditProduct(val id: String) : NavKey
}