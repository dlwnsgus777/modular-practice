package com.modular.product.command.domain.service

import com.modular.product.command.domain.service.input.ProductSaveInput
import com.modular.product.command.domain.service.input.ProductUpdateInput
import com.modular.product.command.domain.service.output.ProductSaveOutput
import com.modular.product.command.infra.FakeProductRepository
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test


class ProductServiceTest {
    lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        productService = ProductService(FakeProductRepository())
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

    @Test
    @DisplayName("상품 수정")
    fun updateProduct01() {
        // given
        val productName = "상품1"
        val price = 1000
        val imageUrl = "http://image.com"
        val saveInput = ProductSaveInput(productName, price, imageUrl)
        val savedProduct: ProductSaveOutput = productService.save(saveInput)

        val input = ProductUpdateInput("상품명")

        // when
        val updatedProduct = productService.update(savedProduct.id, input)

        // then
        assertSoftly {
            it.assertThat(updatedProduct.productName).isEqualTo("상품명")
            it.assertThat(updatedProduct.price).isEqualTo(savedProduct.price)
        }
    }
}