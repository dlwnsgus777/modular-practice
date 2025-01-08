package com.modular.product.query.api

import com.modular.fixture.product.ProductFixture
import com.modular.product.command.infra.JpaProductRepository
import com.modular.support.IntegrationTestController
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ProductQueryControllerTest : IntegrationTestController() {
    @Autowired
    private lateinit var jpaProductRepository: JpaProductRepository

    @Test
    @DisplayName("상품 목록 조회")
    fun getProductList01() {
        // given
        val product01 = ProductFixture.aProduct(productName = "상품1")
        val product02 = ProductFixture.aProduct(productName = "상품2")

        jpaProductRepository.saveAll(listOf(product01, product02))
        jpaProductRepository.flush()

        val page = 0
        val pageSize = 10

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/v1/products")
                .param("page", page.toString())
                .param("pageSize", pageSize.toString())
        ).andDo { print() }

        // then
         resultActions.andExpectAll(
            status().isOk,
            jsonPath("$.productList").exists(),
        )
    }
}