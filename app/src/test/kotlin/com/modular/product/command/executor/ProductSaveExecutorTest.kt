package com.modular.product.command.executor

import com.modular.product.command.api.dto.ProductSaveRequestV1
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test

@Transactional
@SpringBootTest
class ProductSaveExecutorTest {
    @Autowired
    lateinit var productSaveExecutor: ProductSaveExecutor

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
        val result = productSaveExecutor.execute(request)

        // then
        assertNotNull(result.id)
        assertEquals(request.productName, result.productName)
        assertEquals(request.imageUrl, result.imageUrl)
    }
}