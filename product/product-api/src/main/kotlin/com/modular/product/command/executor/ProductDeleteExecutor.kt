package com.modular.product.command.executor

import com.modular.product.command.domain.service.ProductService
import org.springframework.stereotype.Component

@Component
class ProductDeleteExecutor(
    private val productService: ProductService
) {

    fun execute(productId: Long) {
        productService.delete(productId)
    }
}
