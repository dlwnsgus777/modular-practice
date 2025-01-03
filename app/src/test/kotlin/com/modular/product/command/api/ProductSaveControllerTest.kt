package com.modular.product.command.api

import com.modular.product.command.api.dto.ProductSaveRequestV1
import com.modular.support.IntegrationTestController
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ProductSaveControllerTest : IntegrationTestController() {

    @Test
    @DisplayName("상품 저장")
    fun saveProduct01() {
        // given
        val request = ProductSaveRequestV1(
            productName = "상품명",
            price = 1000,
            imageUrl = "http://image.com",
        )

        // when
        val resultActions: ResultActions = mockMvc.perform(
            post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andDo { print() }

        // then
        resultActions.andExpectAll(
            status().isOk,
        )
    }
}