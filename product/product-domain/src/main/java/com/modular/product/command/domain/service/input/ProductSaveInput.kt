package com.modular.product.command.domain.service.input

data class ProductSaveInput(
    val productName: String,
    val price: Int,
    val imageUrl: String
) {
}