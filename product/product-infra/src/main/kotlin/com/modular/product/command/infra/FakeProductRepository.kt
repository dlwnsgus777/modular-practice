package com.modular.product.command.infra

import com.modular.product.command.domain.Product
import com.modular.product.command.domain.repository.ProductRepository

class FakeProductRepository: ProductRepository {
    private val datas = mutableListOf<Product>()

    override fun save(product: Product): Product {
        if (product.id != null) {
            throw IllegalArgumentException("상품 ID가 이미 존재합니다.")
        }
        datas.add(product)
        product.id = datas.size.toLong()
        return product
    }

}