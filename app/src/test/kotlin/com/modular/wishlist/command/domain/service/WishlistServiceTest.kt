package com.modular.wishlist.command.domain.service

import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.infra.FakeWishlistRepository
import com.modular.wishlist.command.domain.Wishlist
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class WishlistServiceTest {

    lateinit var wishlistService: WishlistService

    @Autowired
    lateinit var wishlistRepository: WishlistRepository

    @BeforeEach
    fun setUp() {
        wishlistService = WishlistService(wishlistRepository)
    }

    @Test
    @DisplayName("위시리스트 추가")
    fun addWishlist01() {
        // given
        val memberId = 1L
        val productId = 1L

        val wishlist = Wishlist(
            memberId = memberId,
            productId = productId
        )

        // when
        val result = wishlistService.addWishlist(memberId, productId)

        // then
        val savedWishlist = wishlistRepository.findById(result.id!!)
        assertThat(savedWishlist).isNotNull
        assertThat(savedWishlist!!.memberId).isEqualTo(memberId)
    }

}