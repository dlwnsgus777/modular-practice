package com.modular.product.command.domain.service

import com.modular.product.command.domain.repository.ProductRepository
import com.modular.product.command.domain.service.input.ProductSaveInput
import com.modular.product.command.domain.service.input.ProductUpdateInput
import com.modular.product.command.domain.service.output.ProductSaveOutput
import com.modular.product.command.infra.FakeProductRepository
import com.modular.product.command.infra.JpaProductRepository
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test

@Transactional
@SpringBootTest
class ProductServiceTest {
    lateinit var productService: ProductService

    @Autowired
    lateinit var repository: ProductRepository
    lateinit var productNamePolicy: ProductNamePolicy

    @BeforeEach
    fun setUp() {
        productNamePolicy = DefaultProductNamePolicy(profanityFilter = { p -> p })
        productService = ProductService(repository, productNamePolicy)
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

    @Test
    @DisplayName("상품 삭제")
    fun deleteProduct01() {
        // given
        val productName = "상품1"
        val price = 1000
        val imageUrl = "http://image.com"
        val saveInput = ProductSaveInput(productName, price, imageUrl)
        val savedProduct: ProductSaveOutput = productService.save(saveInput)

        // when
        productService.delete(savedProduct.id)

        // then
        val result = repository.findByIdAndIsDelete(savedProduct.id, false)
        assertSoftly {
            it.assertThat(result).isNull()
        }
    }
}