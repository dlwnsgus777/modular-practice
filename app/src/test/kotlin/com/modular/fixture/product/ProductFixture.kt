package com.modular.fixture.product

import com.modular.product.command.domain.Product

object ProductFixture {

    fun aProduct(
        productName: String = "test@test.com",
        price: Int = 1000,
        imageUrl: String = "imageUrl"
    ): Product {
        return Product(
            productName = productName,
            price = price,
            imageUrl = imageUrl
        )
    }
}