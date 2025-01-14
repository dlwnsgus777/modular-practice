package com.modular.product.command.executor

import com.modular.fixture.product.ProductFixture
import com.modular.product.command.api.dto.ProductUpdateRequestV1
import com.modular.product.command.domain.repository.ProductRepository
import com.modular.support.IntegrationTestController
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.Test

class ProductDeleteExecutorTest: IntegrationTestController() {
    @Autowired
    lateinit var productDeleteExecutor: ProductDeleteExecutor

    @Autowired
    lateinit var productRepository: ProductRepository

    @PersistenceContext
    private lateinit var entityManager: EntityManager


    @Test
    @DisplayName("상품 수정")
    fun saveProduct01() {
        // given
        val product = ProductFixture.aProduct(productName = "상품1")
        productRepository.save(product)
        entityManager.flush()

        // when
        productDeleteExecutor.execute(product.id!!)

        // then
        val result = productRepository.findByIdAndIsDelete(product.id!!, false)
        assertSoftly {
            it.assertThat(result).isNull()
        }
    }
}