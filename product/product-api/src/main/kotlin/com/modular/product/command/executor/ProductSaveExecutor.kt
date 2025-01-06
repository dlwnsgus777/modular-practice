package com.modular.product.command.executor

import com.modular.product.command.api.dto.ProductSaveRequestV1
import com.modular.product.command.api.dto.ProductSaveResponseV1
import com.modular.product.command.domain.service.ProductService
import org.springframework.stereotype.Component

@Component
class ProductSaveExecutor(
    private val productService: ProductService,
) {

    fun execute(request: ProductSaveRequestV1): ProductSaveResponseV1 {
        val result = productService.save(request.toInput())
        return ProductSaveResponseV1(
            id = result.id,
            productName = result.productName,
            price = result.price,
            imageUrl = result.imageUrl,
        )
    }

}
