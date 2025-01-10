package com.modular.product.command.api

import com.modular.product.command.api.dto.ProductSaveRequestV1
import com.modular.product.command.executor.ProductSaveExecutor
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품")
@RestController
@RequestMapping("/api/v1/products")
class ProductUpdateController(
    private val productSaveExecutor: ProductSaveExecutor,
) {

    @Operation(summary = "상품 수정")
    @PostMapping("/{productId}")
    fun saveProduct(
        @PathVariable productId: Long,
        @RequestBody request: ProductSaveRequestV1
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }

}