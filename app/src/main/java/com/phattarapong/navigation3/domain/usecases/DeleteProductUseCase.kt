package com.phattarapong.navigation3.domain.usecases

import com.phattarapong.navigation3.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: String) {
        productRepository.deleteProduct(id)
    }
}
