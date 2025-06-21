package com.phattarapong.navigation3.domain.repository

import com.phattarapong.navigation3.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<List<Product>>
    suspend fun getProductById(id: String): Product?
    suspend fun saveProduct(product: Product)
    suspend fun deleteProduct(id: String)
}
