package com.modular.product.query.api.dto

import com.modular.product.query.domain.ReadOnlyProduct
import org.springframework.data.domain.Page

data class ProductListQueryResponseV1(
    val productList: List<ProductQueryResponse>,
    val totalElements: Long,
    val hasNext: Boolean,
) {
    companion object {
        fun from(result: Page<ReadOnlyProduct>): ProductListQueryResponseV1 {
            return ProductListQueryResponseV1(
                productList = result.content.map { ProductQueryResponse.from(it) },
                totalElements = result.totalElements,
                hasNext = result.hasNext()
            )
        }
    }
}