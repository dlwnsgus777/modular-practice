package com.modular.product.query.infra

import com.modular.fixture.product.ProductFixture
import com.modular.product.command.infra.JpaProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest

@DataJpaTest
class JpaReadOnlyProductRepositoryTest {

    @Autowired
    private lateinit var jpaReadOnlyProductRepository: JpaReadOnlyProductRepository

    @Autowired
    private lateinit var jpaProductRepository: JpaProductRepository

    @Test
    @DisplayName("상품 목록 조회")
    fun findAll01() {
        // given
        val product01 = ProductFixture.aProduct(productName = "상품1")
        val product02 = ProductFixture.aProduct(productName = "상품2")
        val pageable = PageRequest.of(0, 1)

        jpaProductRepository.saveAll(listOf(product01, product02))

        jpaProductRepository.flush()

        // when
        val result = jpaReadOnlyProductRepository.findAll(pageable)

        // then
        assertThat(result.content.size).isEqualTo(1)
        assertThat(result)
            .extracting("productName")
            .contains("상품1")
    }
}