package com.modular.product.command.api

import com.modular.fixture.product.ProductFixture
import com.modular.product.command.api.dto.ProductSaveRequestV1
import com.modular.product.command.api.dto.ProductUpdateRequestV1
import com.modular.product.command.infra.JpaProductRepository
import com.modular.support.IntegrationTestController
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ProductUpdateControllerTest : IntegrationTestController() {
    @Autowired
    private lateinit var jpaProductRepository: JpaProductRepository

    @Test
    @DisplayName("상품 수정")
    fun updateProduct01() {
        // given
        val product01 = ProductFixture.aProduct(productName = "상품1")
        jpaProductRepository.save(product01)
        jpaProductRepository.flush()

        val request = ProductUpdateRequestV1(
            productName = "상품명",
        )



        // when
        val resultActions: ResultActions = mockMvc.perform(
            post("/api/v1/products/{productId}", product01.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andDo { print() }

        // then
        val product = jpaProductRepository.findById(product01.id!!).orElseThrow()

         resultActions.andExpectAll(
            status().isOk,
        )

        assertSoftly {
            it.assertThat(product.productName).isEqualTo("상품명")
            it.assertThat(product.price).isEqualTo(product01.price)
        }

    }
}