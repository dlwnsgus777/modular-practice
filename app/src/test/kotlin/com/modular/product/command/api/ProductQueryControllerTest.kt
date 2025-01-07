package com.modular.product.command.api

import com.modular.support.IntegrationTestController
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ProductQueryControllerTest : IntegrationTestController() {

    @Test
    @DisplayName("상품 목록 조회")
    fun getProductList01() {
        // given
        val page = 1
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