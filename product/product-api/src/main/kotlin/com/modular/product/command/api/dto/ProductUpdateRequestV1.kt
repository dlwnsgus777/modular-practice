package com.modular.product.command.api.dto

import com.modular.product.command.domain.service.input.ProductUpdateInput

data class ProductUpdateRequestV1(
    val productName: String? = null,
    val price: Int? = null,
    val imageUrl: String? = null
) {
    fun toInput(): ProductUpdateInput {
        return ProductUpdateInput(productName, price, imageUrl)
    }
}
