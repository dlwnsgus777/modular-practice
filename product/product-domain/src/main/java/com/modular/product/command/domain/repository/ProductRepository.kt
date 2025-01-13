package com.modular.product.command.domain.repository

import com.modular.product.command.domain.Product

interface ProductRepository {
    fun save(product: Product): Product
    fun findById(productId: Long): Product?
}