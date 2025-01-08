package com.modular.product.query.api.dto

import com.modular.product.query.domain.ReadOnlyProduct

data class ProductQueryResponse(
    val id: Long,
    val productName: String,
    val price: Int,
    val imageUrl: String
) {
    companion object {
        fun from(product: ReadOnlyProduct): ProductQueryResponse {
            return ProductQueryResponse(
                id = product.id,
                productName = product.productName,
                price = product.price,
                imageUrl = product.imageUrl
            )
        }
    }
}