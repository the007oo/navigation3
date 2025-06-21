package com.phattarapong.navigation3.data.repository

import com.phattarapong.navigation3.data.model.ProductDto
import com.phattarapong.navigation3.domain.model.Product
import com.phattarapong.navigation3.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeProductRepositoryImpl @Inject constructor() : ProductRepository {
    
    private val productsFlow = MutableStateFlow<List<ProductDto>>(generateMockProducts())
    
    override fun getProducts(): Flow<List<Product>> {
        return productsFlow.map { productDtos ->
            productDtos.map { it.toDomain() }
        }
    }
    
    override suspend fun getProductById(id: String): Product? {
        return productsFlow.value.find { it.id == id }?.toDomain()
    }
    
    override suspend fun saveProduct(product: Product) {
        val currentProducts = productsFlow.value.toMutableList()
        val index = currentProducts.indexOfFirst { it.id == product.id }
        
        val productDto = ProductDto(
            id = product.id,
            name = product.name,
            description = product.description,
            price = product.price,
            imageUrl = product.imageUrl
        )
        
        if (index != -1) {
            currentProducts[index] = productDto
        } else {
            currentProducts.add(productDto)
        }
        
        productsFlow.value = currentProducts
    }
    
    override suspend fun deleteProduct(id: String) {
        val currentProducts = productsFlow.value.toMutableList()
        currentProducts.removeIf { it.id == id }
        productsFlow.value = currentProducts
    }
    
    private fun generateMockProducts(): List<ProductDto> {
        return listOf(
            ProductDto(
                id = "1",
                name = "Smartphone X",
                description = "Latest smartphone with advanced features",
                price = 999.99,
                imageUrl = "https://picsum.photos/id/1/200"
            ),
            ProductDto(
                id = "2",
                name = "Laptop Pro",
                description = "High-performance laptop for professionals",
                price = 1499.99,
                imageUrl = "https://picsum.photos/id/2/200"
            ),
            ProductDto(
                id = "3",
                name = "Wireless Earbuds",
                description = "Premium sound quality with noise cancellation",
                price = 199.99,
                imageUrl = "https://picsum.photos/id/3/200"
            ),
            ProductDto(
                id = "4",
                name = "Smart Watch",
                description = "Track your fitness and stay connected",
                price = 299.99,
                imageUrl = "https://picsum.photos/id/4/200"
            ),
            ProductDto(
                id = "5",
                name = "Tablet Ultra",
                description = "Portable entertainment and productivity device",
                price = 699.99,
                imageUrl = "https://picsum.photos/id/5/200"
            )
        )
    }
}
