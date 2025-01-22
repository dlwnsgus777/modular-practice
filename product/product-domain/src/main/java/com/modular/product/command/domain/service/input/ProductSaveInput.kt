package com.modular.product.command.domain.service.input

import com.modular.product.command.domain.Product

data class ProductSaveInput(
    val productName: String,
    val price: Int,
    val imageUrl: String
) {
    fun toEntity(parsingProductName: String): Product {
        return Product(parsingProductName, price, imageUrl, false)
    }
}