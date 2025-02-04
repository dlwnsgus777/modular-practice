package com.modular.wishlist.command.api

import com.modular.fixture.product.ProductFixture
import com.modular.product.command.domain.repository.ProductRepository
import com.modular.support.IntegrationTestController
import com.modular.wishlist.command.api.dto.AddWishListRequestV1
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AddWishListControllerTest: IntegrationTestController() {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    @DisplayName("위시리스트 추가")
    fun addWishlist01() {
        // given
        val product = ProductFixture.aProduct(productName = "상품1")
        productRepository.save(product)

        val request = AddWishListRequestV1(
            productId = product.id!!
        )

        // when
        val resultActions: ResultActions = mockMvc.perform(
            post("/api/v1/wishlist/add-product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andDo { MockMvcResultHandlers.print() }


        // then
        resultActions.andExpectAll(
            status().isOk,
        )
    }
}