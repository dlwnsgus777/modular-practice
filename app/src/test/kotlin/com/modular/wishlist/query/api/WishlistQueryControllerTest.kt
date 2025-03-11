package com.modular.wishlist.query.api

import com.modular.fixture.member.MemberFixture
import com.modular.fixture.product.ProductFixture
import com.modular.member.command.domain.repository.MemberRepository
import com.modular.product.command.domain.repository.ProductRepository
import com.modular.support.IntegrationTestController
import com.modular.support.SecurityTestSupporter
import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.domain.Wishlist
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class WishlistQueryControllerTest: IntegrationTestController() {
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
    @DisplayName("위시리스트 조회")
    fun getWishlists() {
        // given
        val password = "password"
        val member = MemberFixture.aMember(password = password)
        val saveMember = memberRepository.save(member)

        val product = ProductFixture.aProduct(productName = "상품1")
        productRepository.save(product)

        val wishlist = Wishlist(saveMember.id!!, product.id!!)
        wishlistRepository.save(wishlist)

        entityManager.flush()

        val accessToken = securityTestSupporter.createTestAccessToken(saveMember.id!!, saveMember.email)

        val page = 0
        val pageSize = 10

        // when
        val resultActions: ResultActions = mockMvc.perform(
            get("/api/v1/wishlist")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer $accessToken") // JWT 추가
                .param("page", page.toString())
                .param("pageSize", pageSize.toString())
        ).andDo { MockMvcResultHandlers.print() }

        // then
        resultActions.andExpectAll(
            status().isOk,
            jsonPath("$.wishlists").exists(),
            jsonPath("$.wishlists").isNotEmpty(),
            jsonPath("$.wishlists[0].productName").value("상품1"),
        )
    }
}