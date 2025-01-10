package com.modular.product.command.executor

import com.modular.fixture.product.ProductFixture
import com.modular.product.command.api.dto.ProductSaveRequestV1
import com.modular.product.command.api.dto.ProductUpdateRequestV1
import com.modular.product.command.domain.repository.ProductRepository
import com.modular.product.command.infra.JpaProductRepository
import com.modular.support.IntegrationTestController
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.Test

class ProductUpdateExecutorTest: IntegrationTestController() {
    @Autowired
    lateinit var productUpdateExecutor: ProductUpdateExecutor

    @Autowired
    lateinit var jpaProductRepository: JpaProductRepository

    @Test
    @DisplayName("상품 수정")
    fun saveProduct01() {
        // given
        val product = ProductFixture.aProduct(productName = "상품1")
        jpaProductRepository.save(product)
        jpaProductRepository.flush()

        val request = ProductUpdateRequestV1(
            productName = "상품명",
        )

        // when
        productUpdateExecutor.execute(product.id!!, request)

        // then
        val result = jpaProductRepository.findById(product.id!!).orElseThrow()
        assertSoftly {
            it.assertThat(result.productName).isEqualTo("상품명")
            it.assertThat(result.price).isEqualTo(1000)
            it.assertThat(result.imageUrl).isEqualTo("http://image.com")
        }
    }
}