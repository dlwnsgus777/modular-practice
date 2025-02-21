package com.modular.wishlist.command.domain.service

import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.domain.Wishlist
import org.springframework.stereotype.Service

@Service
class WishlistService(
    private val wishlistRepository: WishlistRepository
) {
    fun addWishlist(memberId: Long, productId: Long): Wishlist {
        val wishlist = Wishlist(
            memberId = memberId,
            productId = productId
        )

        return wishlistRepository.save(wishlist)
    }
}