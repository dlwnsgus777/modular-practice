package com.modular.product.command.executor

import com.modular.product.command.api.dto.ProductSaveRequestV1
import com.modular.support.IntegrationTestController
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class ProductSaveExecutorTest: IntegrationTestController() {

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
        val result = ProductSaveExecutor().execute(request)

        // then
        assertNotNull(result.id)
        assertEquals(request.productName, result.productName)
        assertEquals(request.imageUrl, result.imageUrl)
    }
}