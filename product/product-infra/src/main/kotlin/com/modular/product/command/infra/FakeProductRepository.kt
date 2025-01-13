package com.modular.product.command.infra

import com.modular.product.command.domain.Product
import com.modular.product.command.domain.repository.ProductRepository

class FakeProductRepository : ProductRepository {
    private val datas = mutableListOf<Product>()

    override fun save(product: Product): Product {
        if (product.id != null) {
            datas.removeIf { it.id == product.id }
        }
        datas.add(product)
        product.id = datas.size.toLong()
        return product
    }

    override fun findById(productId: Long): Product? {
        return datas.find { it.id == productId }
    }

}