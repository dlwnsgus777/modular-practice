package com.modular.wishlist.query.executor

import org.springframework.stereotype.Component

@Component
class GetWishlistExecutor {
    fun execute(memberId: Long, page: Int, pageSize: Int): List<WishlistOutput> {
        TODO("Not yet implemented")
    }
}


class WishlistOutput(
    val wishlistId: Long,
    val productId: Long,
    val productName: String
)