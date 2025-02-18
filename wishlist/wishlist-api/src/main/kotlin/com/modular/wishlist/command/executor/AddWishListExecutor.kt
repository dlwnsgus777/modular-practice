package com.modular.wishlist.command.executor

import com.modular.wishlist.command.domain.service.WishlistService
import org.springframework.stereotype.Component

@Component
class AddWishListExecutor(
    private val wishlistService: WishlistService
) {
    fun execute(memberId: Long, productId: Long) {
        wishlistService.addWishlist(memberId, productId)
    }
}