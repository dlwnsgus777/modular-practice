package com.modular.product.command.domain.repository

import com.modular.product.command.domain.Product

interface ProductRepository {
    fun save(product: Product): Product
    fun findByIdAndIsDelete(productId: Long, isDelete: Boolean): Product?
}