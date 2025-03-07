package com.modular.product.command.api

import com.modular.fixture.product.ProductFixture
import com.modular.product.command.domain.repository.ProductRepository
import com.modular.support.IntegrationTestController
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ProductDeleteControllerTest : IntegrationTestController() {
    @Autowired
    private lateinit var productRepository: ProductRepository

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Test
    @DisplayName("상품 삭제")
    fun deleteProduct01() {
        // given
        val product01 = ProductFixture.aProduct(productName = "상품1")
        productRepository.save(product01)
        entityManager.flush()

        // when
        val resultActions: ResultActions = mockMvc.perform(
            delete("/api/v1/products/{productId}", product01.id)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo { print() }

        // then
        val product = productRepository.findByIdAndIsDelete(product01.id!!, false)

        resultActions.andExpectAll(
            status().isOk,
        )

        assertSoftly {
            it.assertThat(product).isNull()
        }

    }
}