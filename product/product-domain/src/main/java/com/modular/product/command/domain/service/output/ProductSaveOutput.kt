package com.modular.product.command.domain.service.output

data class ProductSaveOutput(
    val id: Long,
    val productName: String,
    val price: Int,
    val imageUrl: String
) {
}