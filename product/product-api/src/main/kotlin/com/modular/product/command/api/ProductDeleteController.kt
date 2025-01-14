package com.modular.product.command.api

import com.modular.product.command.executor.ProductDeleteExecutor
import com.modular.product.command.executor.ProductUpdateExecutor
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품")
@RestController
@RequestMapping("/api/v1/products")
class ProductDeleteController(
    private val productDeleteExecutor: ProductDeleteExecutor,
) {

    @Operation(summary = "상품 삭제")
    @DeleteMapping("/{productId}")
    fun deleteProduct(
        @PathVariable productId: Long,
    ): ResponseEntity<Unit> {
        productDeleteExecutor.execute(productId)
        return ResponseEntity.ok().build()
    }

}