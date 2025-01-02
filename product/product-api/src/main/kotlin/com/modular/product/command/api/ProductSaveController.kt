package com.modular.product.command.api

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품")
@RestController
@RequestMapping("/api/v1/products")
class ProductSaveController {

    @GetMapping("/test")
    fun test(): String {
        return "test"
    }
}