package com.phattarapong.navigation3.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phattarapong.navigation3.domain.model.Product
import com.phattarapong.navigation3.domain.usecases.GetProductByIdUseCase
import com.phattarapong.navigation3.domain.usecases.SaveProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val saveProductUseCase: SaveProductUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<EditProductUiState>(EditProductUiState.Loading)
    val uiState: StateFlow<EditProductUiState> = _uiState.asStateFlow()
    
    private val _productName = MutableStateFlow("")
    val productName = _productName.asStateFlow()
    
    private val _productDescription = MutableStateFlow("")
    val productDescription = _productDescription.asStateFlow()
    
    private val _productPrice = MutableStateFlow("")
    val productPrice = _productPrice.asStateFlow()
    
    private val _productImageUrl = MutableStateFlow("")
    val productImageUrl = _productImageUrl.asStateFlow()
    
    private var currentProductId: String? = null
    
    fun loadProduct(id: String) {
        viewModelScope.launch {
            _uiState.value = EditProductUiState.Loading
            try {
                val product = getProductByIdUseCase(id)
                if (product != null) {
                    currentProductId = product.id
                    _productName.value = product.name
                    _productDescription.value = product.description
                    _productPrice.value = product.price.toString()
                    _productImageUrl.value = product.imageUrl
                    _uiState.value = EditProductUiState.Editing
                } else {
                    _uiState.value = EditProductUiState.Error("Product not found")
                }
            } catch (e: Exception) {
                _uiState.value = EditProductUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
    
    fun createNewProduct() {
        currentProductId = null
        _productName.value = ""
        _productDescription.value = ""
        _productPrice.value = ""
        _productImageUrl.value = ""
        _uiState.value = EditProductUiState.Editing
    }
    
    fun updateProductName(name: String) {
        _productName.value = name
    }
    
    fun updateProductDescription(description: String) {
        _productDescription.value = description
    }
    
    fun updateProductPrice(price: String) {
        _productPrice.value = price
    }
    
    fun updateProductImageUrl(imageUrl: String) {
        _productImageUrl.value = imageUrl
    }
    
    fun saveProduct() {
        val name = _productName.value
        val description = _productDescription.value
        val priceText = _productPrice.value
        val imageUrl = _productImageUrl.value
        
        if (name.isBlank() || description.isBlank() || priceText.isBlank()) {
            _uiState.value = EditProductUiState.Error("All fields are required")
            return
        }
        
        val price = try {
            priceText.toDouble()
        } catch (e: NumberFormatException) {
            _uiState.value = EditProductUiState.Error("Invalid price format")
            return
        }
        
        viewModelScope.launch {
            try {
                _uiState.value = EditProductUiState.Saving
                
                val product = Product(
                    id = currentProductId ?: UUID.randomUUID().toString(),
                    name = name,
                    description = description,
                    price = price,
                    imageUrl = imageUrl.ifBlank { "https://picsum.photos/id/1/200" }
                )
                
                saveProductUseCase(product)
                _uiState.value = EditProductUiState.Saved
            } catch (e: Exception) {
                _uiState.value = EditProductUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class EditProductUiState {
    data object Loading : EditProductUiState()
    data object Editing : EditProductUiState()
    data object Saving : EditProductUiState()
    data object Saved : EditProductUiState()
    data class Error(val message: String) : EditProductUiState()
}
