package com.modular.product.command.api

import com.modular.product.command.api.dto.ProductSaveRequestV1
import com.modular.product.command.api.dto.ProductSaveResponseV1
import com.modular.product.command.executor.ProductSaveExecutor
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품")
@RestController
@RequestMapping("/api/v1/products")
class ProductSaveController(
    private val productSaveExecutor: ProductSaveExecutor,
) {

    @PostMapping("")
    fun saveProduct(@RequestBody request: ProductSaveRequestV1): ResponseEntity<ProductSaveResponseV1> {
        val result: ProductSaveResponseV1 = productSaveExecutor.execute(request)
        return ResponseEntity.ok(result)
    }
}