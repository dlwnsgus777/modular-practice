package com.modular.product.command.api.dto

import com.modular.product.command.domain.service.input.ProductSaveInput

data class ProductSaveRequestV1(
    val productName: String,
    val price: Int,
    val imageUrl: String
) {
    fun toInput(): ProductSaveInput {
        return ProductSaveInput(
            productName = productName,
            price = price,
            imageUrl = imageUrl
        )
    }
}
