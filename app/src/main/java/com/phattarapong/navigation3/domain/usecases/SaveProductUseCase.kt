package com.phattarapong.navigation3.domain.usecases

import com.phattarapong.navigation3.domain.model.Product
import com.phattarapong.navigation3.domain.repository.ProductRepository
import javax.inject.Inject

class SaveProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        productRepository.saveProduct(product)
    }
}
