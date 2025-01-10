package com.modular.product.command.api.dto

data class ProductUpdateRequestV1(
    val productName: String? = null,
    val price: Int? = null,
    val imageUrl: String? = null
) {
}
