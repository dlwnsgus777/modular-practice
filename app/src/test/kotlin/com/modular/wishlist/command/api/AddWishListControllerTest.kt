package com.modular.wishlist.command.api

import com.modular.fixture.member.MemberFixture
import com.modular.fixture.product.ProductFixture
import com.modular.member.command.domain.repository.MemberRepository
import com.modular.product.command.domain.repository.ProductRepository
import com.modular.support.IntegrationTestController
import com.modular.support.SecurityTestSupporter
import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.api.dto.AddWishListRequestV1
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.assertj.core.api.Assertions.assertThat
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
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var securityTestSupporter: SecurityTestSupporter

    @Autowired
    private lateinit var wishlistRepository: WishlistRepository

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Test
    @DisplayName("위시리스트 추가")
    fun addWishlist01() {
        // given
        val password = "password"
        val member = MemberFixture.aMember(password = password)
        val saveMember = memberRepository.save(member)
        entityManager.flush()

        val product = ProductFixture.aProduct(productName = "상품1")
        productRepository.save(product)

        val request = AddWishListRequestV1(
            productId = product.id!!
        )

        val accessToken = securityTestSupporter.createTestAccessToken(saveMember.id!!, saveMember.email)

        // when
        val resultActions: ResultActions = mockMvc.perform(
            post("/api/v1/wishlist/add-product")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer $accessToken") // JWT 추가
                .content(mapper.writeValueAsString(request))
        ).andDo { MockMvcResultHandlers.print() }


        // then
        resultActions.andExpectAll(
            status().isOk,
        )

        val result = wishlistRepository.findAllByMemberId(saveMember.id!!)
        assertThat(result).isNotEmpty
        assertThat(result.size).isEqualTo(1)
    }
}