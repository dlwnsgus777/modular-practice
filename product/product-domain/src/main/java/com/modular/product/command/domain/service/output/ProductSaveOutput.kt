package com.modular.product.command.domain.service.output

import com.modular.product.command.domain.Product

data class ProductSaveOutput(
    val id: Long,
    val productName: String,
    val price: Int,
    val imageUrl: String
) {
    companion object {
        fun from(product: Product): ProductSaveOutput {
            return ProductSaveOutput(product.id!!, product.productName, product.price, product.imageUrl)
        }
    }
}