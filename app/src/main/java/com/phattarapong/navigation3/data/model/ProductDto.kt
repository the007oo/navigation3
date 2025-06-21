package com.phattarapong.navigation3.data.model

import com.phattarapong.navigation3.domain.model.Product

data class ProductDto(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            name = name,
            description = description,
            price = price,
            imageUrl = imageUrl
        )
    }
}
