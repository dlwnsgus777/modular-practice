package com.modular.product.command.executor

import com.modular.product.command.api.dto.ProductUpdateRequestV1
import com.modular.product.command.domain.service.ProductService
import org.springframework.stereotype.Component

@Component
class ProductUpdateExecutor(
    private val productService: ProductService
) {

    fun execute(productId: Long, request: ProductUpdateRequestV1) {
        productService.update(productId, request.toInput())
    }
}
