package com.modular.product.command.api.dto

data class ProductSaveRequestV1(
    val productName: String,
    val price: Int,
    val imageUrl: String
) {

}
