package com.modular.product.query.api.dto

import com.modular.product.query.domain.ReadOnlyProduct

data class ProductInfoQueryResponseV1(
    val product: ProductQueryResponse
) {
    companion object {
        fun from(result: ReadOnlyProduct): ProductInfoQueryResponseV1 {
            return ProductInfoQueryResponseV1(
                product = ProductQueryResponse.from(result)
            )
        }
    }
}