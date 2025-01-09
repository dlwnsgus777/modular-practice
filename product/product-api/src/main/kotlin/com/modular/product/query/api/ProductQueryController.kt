package com.modular.product.query.api

import com.modular.product.query.api.dto.ProductListQueryResponseV1
import com.modular.product.query.domain.repository.ReadOnlyProductRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품")
@RestController
@RequestMapping("/api/v1/products")
class ProductQueryController(
    private val readOnlyProductRepository: ReadOnlyProductRepository
) {

    @Operation(summary = "상품 목록 조회")
    @GetMapping
    fun getProductList(@RequestParam page: Int, @RequestParam pageSize: Int): ResponseEntity<ProductListQueryResponseV1> {
        val pageable = PageRequest.of(page, pageSize)
        val result = readOnlyProductRepository.findAll(pageable)
        return ResponseEntity.ok(ProductListQueryResponseV1.from(result))
    }

}