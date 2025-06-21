package com.phattarapong.navigation3.domain.usecases

import com.phattarapong.navigation3.domain.model.Product
import com.phattarapong.navigation3.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return productRepository.getProducts()
    }
}
