package com.modular.product.command.api.dto

data class ProductSaveResponseV1(
    val id: Long,
    val productName: String,
    val price: Int,
    val imageUrl: String
) {

}
