package com.modular.product.command.domain.service.input

data class ProductUpdateInput(
    var productName: String? = null,
    val price: Int? = null,
    val imageUrl: String? = null
) {
}