package com.modular.product.command.domain.service

import com.modular.product.command.domain.service.input.ProductSaveInput
import com.modular.product.command.domain.service.output.ProductSaveOutput
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductServiceTest {
    lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        productService = ProductService()
    }

    @Test
    @DisplayName("상품 저장")
    fun saveProduct01() {
        // given
        val productName = "상품1"
        val price = 1000
        val imageUrl = "http://image.com"
        val input = ProductSaveInput(productName, price, imageUrl)

        // when
        val savedProduct: ProductSaveOutput = productService.save(input)

        // then
        assertEquals(productName, savedProduct.productName)
        assertEquals(price, savedProduct.price)
        assertEquals(imageUrl, savedProduct.imageUrl)
    }
}