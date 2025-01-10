package com.modular.product.command.domain.service.output

import com.modular.product.command.domain.Product

data class ProductUpdateOutput(
    val id: Long,
    val productName: String,
    val price: Int,
    val imageUrl: String
) {
    companion object {
        fun from(product: Product): ProductUpdateOutput {
            return ProductUpdateOutput(product.id!!, product.productName, product.price, product.imageUrl)
        }
    }
}