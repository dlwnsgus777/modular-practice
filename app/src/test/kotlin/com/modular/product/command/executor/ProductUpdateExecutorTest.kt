package com.modular.product.command.executor

import com.modular.fixture.product.ProductFixture
import com.modular.product.command.api.dto.ProductUpdateRequestV1
import com.modular.product.command.domain.repository.ProductRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test

@Transactional
@SpringBootTest
class ProductUpdateExecutorTest {
    @Autowired
    lateinit var productUpdateExecutor: ProductUpdateExecutor

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

        val request = ProductUpdateRequestV1(
            productName = "상품명",
        )

        // when
        productUpdateExecutor.execute(product.id!!, request)

        // then
        val result = productRepository.findByIdAndIsDelete(product.id!!, false) ?: throw IllegalArgumentException("상품이 존재하지 않습니다.")
        assertSoftly {
            it.assertThat(result.productName).isEqualTo("상품명")
            it.assertThat(result.price).isEqualTo(1000)
            it.assertThat(result.imageUrl).isEqualTo("imageUrl")
        }
    }
}