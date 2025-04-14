package com.modular.wishlist.query.api

import com.modular.fixture.member.MemberFixture
import com.modular.fixture.product.ProductFixture
import com.modular.member.command.domain.repository.MemberRepository
import com.modular.product.command.domain.repository.ProductRepository
import com.modular.support.IntegrationTestController
import com.modular.support.SecurityTestSupporter
import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.domain.Wishlist
import com.modular.wishlist.query.api.dto.GetWishlistResponse
import com.modular.wishlist.query.executor.GetWishlistExecutor
import com.modular.wishlist.query.executor.WishlistOutput
import com.ninjasquad.springmockk.MockkBean
import io.mockk.MockKAnnotations
import io.mockk.every
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class WishlistQueryControllerTest : IntegrationTestController() {
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var securityTestSupporter: SecurityTestSupporter

    @Autowired
    private lateinit var wishlistRepository: WishlistRepository

    @MockkBean
    private lateinit var getWishlistExecutor: GetWishlistExecutor

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    @DisplayName("위시리스트 조회")
    fun getWishlists() {
        // given
        val password = "password"
        val member = MemberFixture.aMember(password = password)
        val saveMember = memberRepository.save(member)

        val product = ProductFixture.aProduct(productName = "상품5")
        productRepository.save(product)

        val wishlist = Wishlist(saveMember.id!!, product.id!!)
        wishlistRepository.save(wishlist)

        entityManager.flush()

        val accessToken = securityTestSupporter.createTestAccessToken(
            saveMember.id!!, saveMember.email
        )

        val content = listOf(
            WishlistOutput(
                wishlistId = wishlist.id!!,
                productId = product.id!!,
                productName = product.productName,
            )
        )
        val page = 0
        val pageSize = 10
        val pageImpl = PageImpl(
            content,
            PageRequest.of(page, pageSize),
            content.size.toLong()
        )

        every {
            getWishlistExecutor.execute(
                saveMember.id!!,
                page,
                pageSize
            )
        } returns pageImpl


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
            jsonPath("$.wishlist").exists(),
            jsonPath("$.wishlist").isNotEmpty(),
            jsonPath("$.wishlist[0].productName").value("상품5"),
        )
    }
}
