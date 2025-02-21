package com.modular.wishlist.command.domain.service

import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.domain.Wishlist
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class WishlistServiceTest {

    @Autowired
    lateinit var wishlistService: WishlistService

    @MockK
    lateinit var wishlistRepository: WishlistRepository

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
        wishlistService.addWishlist(memberId, productId)

        // then
        verify { wishlistRepository.save(wishlist) }
    }

}