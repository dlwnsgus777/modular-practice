package com.modular.product.query.domain.repository

import com.modular.fixture.product.ProductFixture
import com.modular.product.command.infra.JpaProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest

@DataJpaTest
class ReadOnlyProductRepositoryTest {

    @Autowired
    private lateinit var readOnlyProductRepository: ReadOnlyProductRepository

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
        val result = readOnlyProductRepository.findAll(pageable)

        // then
        assertThat(result.content.size).isEqualTo(1)
        assertThat(result)
            .extracting("productName")
            .contains("상품1")
    }

    @Test
    @DisplayName("상품 상세 조회")
    fun findById01() {
        // given
        val product01 = ProductFixture.aProduct(productName = "상품1")

        jpaProductRepository.save(product01)
        jpaProductRepository.flush()

        // when
        val result = readOnlyProductRepository.findById(product01.id!!) ?: throw IllegalArgumentException("상품이 존재하지 않습니다.")

        // then
        assertThat(result.productName).isEqualTo("상품1")
    }
}